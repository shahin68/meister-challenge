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
                    .setTitle(viewClickCallback.data.name)
                    .setMessage(viewClickCallback.data.name)
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
                    viewModel.searchPaging(it.trim()).collectLatest { pagingData ->
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
                when (val state = combinedState.refresh) {
                    is LoadState.NotLoading -> {
                        onLoading(showLoading = false)
                    }
                    is LoadState.Loading -> {
                        onLoading(showLoading = true)
                    }
                    is LoadState.Error -> {
                        onLoading(showLoading = false)
                        showEmptyView(
                            show = true, state.error.localizedMessage
                        )
                    }
                }
                if (combinedState.append is LoadState.NotLoading && combinedState.append.endOfPaginationReached) {
                    showEmptyView(show = tasksAdapter.itemCount < 1, getString(R.string.home_error_no_data))
                }
            }
        }
    }

    private fun showEmptyView(show: Boolean, errorMessage: String?) {
        binding.emptyView.root.visibleOrGone(show)
        binding.emptyView.tvError.visibleOrGone(!errorMessage.isNullOrEmpty())
        binding.emptyView.tvError.text = errorMessage
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