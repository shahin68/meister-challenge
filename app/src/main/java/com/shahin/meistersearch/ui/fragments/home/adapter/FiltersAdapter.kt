package com.shahin.meistersearch.ui.fragments.home.adapter

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.shahin.meistersearch.R
import com.shahin.meistersearch.general.extensions.inflate
import com.shahin.meistersearch.general.views.ViewClickCallback
import com.shahin.meistersearch.ui.fragments.home.filter.FilterItem
import com.shahin.meistersearch.ui.fragments.home.viewholders.FilterViewHolder

class FiltersAdapter(
    private val clickCallback: (
        view: View,
        position: Int,
        viewClickCallback: ViewClickCallback<FilterItem>
    ) -> Unit
): ListAdapter<FilterItem, FilterViewHolder>(DiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilterViewHolder {
        return FilterViewHolder(
            parent.inflate(R.layout.item_filter),
            clickCallback
        )
    }

    override fun onBindViewHolder(holder: FilterViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    object DiffCallback : DiffUtil.ItemCallback<FilterItem>() {
        override fun areItemsTheSame(oldItem: FilterItem, newItem: FilterItem): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: FilterItem, newItem: FilterItem): Boolean {
            return oldItem.filter.name.equals(newItem.filter.name, ignoreCase = true)
        }
    }

    override fun submitList(list: List<FilterItem>?) {
        super.submitList(ArrayList(list))
    }
}