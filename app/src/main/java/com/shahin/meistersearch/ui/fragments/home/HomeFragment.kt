package com.shahin.meistersearch.ui.fragments.home

import android.os.Bundle
import android.view.View
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.shahin.meistersearch.R
import com.shahin.meistersearch.data.remote.models.body.FilterBody
import com.shahin.meistersearch.databinding.FragmentHomeBinding
import com.shahin.meistersearch.general.adapters.MyLoadStateAdapter
import com.shahin.meistersearch.general.extensions.onQueryFlow
import com.shahin.meistersearch.general.preferences.SEARCH_TRIGGER_DELAY
import com.shahin.meistersearch.general.views.ViewClickCallback
import com.shahin.meistersearch.ui.fragments.BaseFragment
import com.shahin.meistersearch.ui.fragments.home.adapter.FiltersAdapter
import com.shahin.meistersearch.ui.fragments.home.adapter.TasksAdapter
import com.shahin.meistersearch.ui.fragments.home.filter.Filter
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : BaseFragment<FragmentHomeBinding>(R.layout.fragment_home) {

    private val viewModel: HomeViewModel by viewModel()

    private var searchJob: Job? = null

    private val tasksAdapter = TasksAdapter { _, _, viewClickCallback ->
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

    private val filtersAdapter = FiltersAdapter { _, position, viewClickCallback ->
        when (viewClickCallback) {
            is ViewClickCallback.ToOpen -> {
                viewModel.changeFilters(
                    filter = viewClickCallback.data
                )

                scrollToFilter(viewClickCallback.data.filter, position)
            }
            is ViewClickCallback.ToRemove -> {
                // nothing for now
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupFilterList()

        setupTaskList()

        handleSearchFlow()

    }

    private fun setupFilterList() {
        binding.listFilters.apply {
            adapter = filtersAdapter
            itemAnimator = null
        }

        viewModel.selectedFilter.observe(viewLifecycleOwner) { selectedFilter ->
            filtersAdapter.submitList(
                viewModel.getFilters(selectedFilter)
            )

            /**
             * Scroll can be used on [FiltersAdapter] click to open callback
             * But wanted to practice scrolling to selected filter from here
             * because after screen orientation change this method will be also called
             */
            scrollToFilter(selectedFilter.filter, null)

            /**
             * if search view query is empty
             * lookup the latest query available from viewModel
             * useful for cases when we still have task items showing
             * but we clear searchview and want to change filter
             */
            var query = ""
            if (binding.searchView.query.isEmpty()) {
                val lastQuery = viewModel.lastQuery.value
                if (lastQuery.isNullOrEmpty()) {
                    return@observe
                } else {
                    query = lastQuery
                }
            } else {
                query = binding.searchView.query.toString().trim()
            }
            val selectedFilterStatus = selectedFilter.filter.status
            submitSearch(
                FilterBody(
                    text = query,
                    status = if (selectedFilterStatus != null)
                        listOf(selectedFilterStatus)
                    else
                        emptyList()
                )
            )
        }
    }

    private fun scrollToFilter(filter: Filter, position: Int?) {
        lifecycleScope.launch {
            // delay scrolling for a moment
            delay(500)
            if (isAdded && isVisible) {
                binding.listFilters.smoothScrollToPosition(
                    position ?: filter.ordinal
                )
            }
        }
    }

    private fun handleSearchFlow() {
        binding.searchView
            .onQueryFlow()
            .debounce(SEARCH_TRIGGER_DELAY)
            .filter { it.isNotEmpty() }
            .distinctUntilChanged()
            .onEach {
                val selectedFilterStatus = viewModel.selectedFilter.value?.filter?.status
                submitSearch(
                    FilterBody(
                        text = it.trim(),
                        status = if (selectedFilterStatus != null)
                            listOf(selectedFilterStatus)
                        else
                            emptyList()
                    )
                )
            }
            .launchIn(lifecycleScope)
    }

    private fun submitSearch(filterBody: FilterBody) {
        searchJob?.cancel()
        searchJob = lifecycleScope.launch {
            viewModel.searchPagingWithDb(
                filterBody
            ).collectLatest { pagingData ->
                if (isAdded) {
                    tasksAdapter.submitData(
                        pagingData
                    )
                }
            }
        }
    }

    private fun setupTaskList() {
        binding.listTasks.adapter = tasksAdapter.withLoadStateFooter(
            footer = MyLoadStateAdapter()
        )

        tasksAdapter.addLoadStateListener { combinedState ->
            if (isAdded) {
                when (val state = combinedState.append) {
                    is LoadState.NotLoading -> {
                        onLoading(showOrHide = false)
                        showEmptyView(
                            show = tasksAdapter.itemCount < 1,
                            if (binding.searchView.query.isEmpty())
                                null
                            else
                                getString(R.string.home_error_no_data)
                        )
                    }
                    is LoadState.Loading -> {
                        onLoading(showOrHide = !state.endOfPaginationReached)
                    }
                    is LoadState.Error -> {
                        onLoading(showOrHide = false)
                        showEmptyView(
                            show = true, state.error.localizedMessage
                        )
                    }
                }
                when (val state = combinedState.refresh) {
                    is LoadState.Loading -> onLoading(showOrHide = !state.endOfPaginationReached)
                }
            }
        }
    }

    private fun showEmptyView(show: Boolean, errorMessage: String?) {
        binding.showEmptyView = show
        lifecycleScope.launch {
            // adding delay to show error message in case user changes query faster
            delay(500)
            if (isAdded && isVisible) {
                binding.errorText = errorMessage
            }
        }
    }

    private fun onLoading(showOrHide: Boolean) {
        binding.showLoading = showOrHide
    }

    override fun onDestroy() {
        super.onDestroy()
        searchJob?.cancel()
    }
}