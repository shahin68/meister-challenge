package com.shahin.meistersearch.general.adapters

import android.view.View
import android.view.ViewGroup
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.RecyclerView
import com.shahin.meistersearch.R
import com.shahin.meistersearch.databinding.ItemLoadStateBinding
import com.shahin.meistersearch.general.extensions.inflate
import com.shahin.meistersearch.general.extensions.visibleOrGone

/**
 * Adapter for displaying a loading state on [PagingDataAdapter] footer or header
 */
class MyLoadStateAdapter :
    LoadStateAdapter<MyLoadStateAdapter.LoadStateViewHolder>() {

    override fun onBindViewHolder(
        holder: LoadStateViewHolder,
        loadState: LoadState
    ) {
        holder.bind(loadState)
    }

    inner class LoadStateViewHolder(
        itemView: View
    ) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemLoadStateBinding.bind(itemView)

        fun bind(loadState: LoadState) {
            if (loadState is LoadState.Error) {
                binding.messageTv.text = loadState.error.localizedMessage
            }

            binding.loading.visibleOrGone(loadState is LoadState.Loading)
            binding.messageTv.visibleOrGone(loadState is LoadState.Error)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): LoadStateViewHolder {
        return LoadStateViewHolder(
            parent.inflate(R.layout.item_load_state)
        )
    }
}