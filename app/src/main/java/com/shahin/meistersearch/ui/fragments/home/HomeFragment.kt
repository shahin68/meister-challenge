package com.shahin.meistersearch.ui.fragments.home

import android.os.Bundle
import android.view.View
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.shahin.meistersearch.R
import com.shahin.meistersearch.databinding.FragmentHomeBinding
import com.shahin.meistersearch.general.adapters.MyLoadStateAdapter
import com.shahin.meistersearch.general.extensions.onQueryFlow
import com.shahin.meistersearch.general.extensions.visibleOrGone
import com.shahin.meistersearch.general.preferences.SEARCH_TRIGGER_DELAY
import com.shahin.meistersearch.general.views.ViewClickCallback
import com.shahin.meistersearch.ui.fragments.BaseFragment
import com.shahin.meistersearch.ui.fragments.home.adapter.TasksAdapter
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : BaseFragment<FragmentHomeBinding>(R.layout.fragment_home) {

    private val viewModel: HomeViewModel by viewModel()

    private var searchJob: Job? = null

    private val tasksAdapter = TasksAdapter { _, viewClickCallback ->
        when (viewClickCallback) {
            is ViewClickCallback.ToOpen -> {
                MaterialAlertDialogBuilder(requireContext())
                    .setTitle(viewClickCallback.data.taskName)
                    .setMessage(viewClickCallback.data.projectName)
                    .setPositiveButton(getString(R.string.generic_action_ok)) { dialog, which ->
                        dialog.dismiss()
                    }
                    .show()
            }
            is ViewClickCallback.ToRemove -> {
                // nothing for now
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupTaskList()

        handleSearchFlow()

    }

    private fun handleSearchFlow() {
        binding.searchView
            .onQueryFlow()
            .debounce(SEARCH_TRIGGER_DELAY)
            .filter { it.isNotEmpty() }
            .distinctUntilChanged()
            .onEach {
                searchJob?.cancel()
                searchJob = lifecycleScope.launch {
                    viewModel.searchPagingWithDb(it.trim()).collectLatest { pagingData ->
                        if (isAdded) {
                            tasksAdapter.submitData(
                                pagingData
                            )
                        }
                    }
                }
            }
            .launchIn(lifecycleScope)
    }

    private fun setupTaskList() {
        binding.listTasks.adapter = tasksAdapter.withLoadStateFooter(
            footer = MyLoadStateAdapter()
        )

        tasksAdapter.addLoadStateListener { combinedState ->
            if (isAdded) {
                when (val state = combinedState.append) {
                    is LoadState.NotLoading -> {
                        onLoading(showLoading = false)
                        showEmptyView(
                            show = tasksAdapter.itemCount < 1,
                            if (binding.searchView.query.isEmpty())
                                null
                            else
                                getString(R.string.home_error_no_data)
                        )
                    }
                    is LoadState.Loading -> {
                        onLoading(showLoading = !state.endOfPaginationReached)
                    }
                    is LoadState.Error -> {
                        onLoading(showLoading = false)
                        showEmptyView(
                            show = true, state.error.localizedMessage
                        )
                    }
                }
                when (val state = combinedState.refresh) {
                    is LoadState.Loading -> onLoading(showLoading = !state.endOfPaginationReached)
                }
            }
        }
    }

    private fun showEmptyView(show: Boolean, errorMessage: String?) {
        binding.emptyView.root.visibleOrGone(show)
        binding.emptyView.tvError.visibleOrGone(!errorMessage.isNullOrEmpty())
        lifecycleScope.launch {
            // adding delay to show error message in case user changes query faster
            delay(500)
            binding.emptyView.tvError.text = errorMessage
        }
    }

    private fun onLoading(showLoading: Boolean) = when {
        showLoading -> {
            binding.loading.show()
        }
        else -> {
            binding.loading.hide()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        searchJob?.cancel()
    }
}