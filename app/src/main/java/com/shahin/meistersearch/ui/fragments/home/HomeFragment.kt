package com.shahin.meistersearch.ui.fragments.home

import android.os.Bundle
import android.view.View
import com.shahin.meistersearch.R
import com.shahin.meistersearch.data.remote.models.response.search.items.TaskItem
import com.shahin.meistersearch.databinding.FragmentHomeBinding
import com.shahin.meistersearch.general.extensions.extractCleanMessage
import com.shahin.meistersearch.general.extensions.gone
import com.shahin.meistersearch.general.extensions.visible
import com.shahin.meistersearch.general.extensions.visibleOrGone
import com.shahin.meistersearch.network.NetworkResult
import com.shahin.meistersearch.ui.fragments.BaseFragment
import com.shahin.meistersearch.ui.fragments.home.adapter.TasksAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : BaseFragment<FragmentHomeBinding>(R.layout.fragment_home) {

    private val viewModel: HomeViewModel by viewModel()

    private val tasksAdapter = TasksAdapter { view, viewClickType ->
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupTaskList()

        viewModel.searchResult.observe(viewLifecycleOwner) {
            when (it) {
                is NetworkResult.Successful -> {
                    binding.listTasks.visible()
                    tasksAdapter.submitList(it.data?.searchResult?.tasks)
                }
                is NetworkResult.Error -> {
                    showEmptyView(
                        it.error.extractCleanMessage(requireContext())
                    )
                }
                is NetworkResult.NetworkError -> {
                    showEmptyView(
                        getString(R.string.error_text_no_network)
                    )
                }
            }
        }

        viewModel.search("buy")
    }

    private fun setupTaskList() {
        binding.listTasks.adapter = tasksAdapter
    }

    private fun showEmptyView(errorMessage: String?) {
        binding.listTasks.gone()
        binding.emptyView.root.visible()
        binding.emptyView.tvError.visibleOrGone(!errorMessage.isNullOrEmpty())
        binding.emptyView.tvError.text = errorMessage
    }
}