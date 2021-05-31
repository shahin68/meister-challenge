package com.shahin.meistersearch.ui.fragments.home.viewholders

import android.view.View
import com.shahin.meistersearch.databinding.ItemFilterBinding
import com.shahin.meistersearch.general.viewholders.ClickableViewHolder
import com.shahin.meistersearch.general.views.ViewClickCallback
import com.shahin.meistersearch.ui.fragments.home.filter.FilterItem

class FilterViewHolder(
    private val binding: ItemFilterBinding,
    clickCallback: (
        view: View,
        position: Int,
        viewClickCallback: ViewClickCallback<FilterItem>
    ) -> Unit
) : ClickableViewHolder<FilterItem>(binding.root, clickCallback) {
    override fun bind(item: FilterItem) {
        super.bind(item)

        // Data Binding Implementation
        binding.filter = item


        // View Binding Implementation
        /*binding.btnFilter.text = item.filter.name

        *//**
         * Note:
         * We want the ones that are not selected to be Enabled
         * So we can Click and Select another Item
         *//*
        binding.btnFilter.isEnabled = !item.selected*/
    }
}