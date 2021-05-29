package com.shahin.meistersearch.ui.fragments.home.viewholders

import android.view.View
import com.shahin.meistersearch.data.remote.models.response.search.items.TaskItem
import com.shahin.meistersearch.databinding.ItemTaskBinding
import com.shahin.meistersearch.general.viewholders.ClickableViewHolder
import com.shahin.meistersearch.general.views.ViewClickCallback

class TaskViewHolder(
    itemView: View,
    clickCallback: (
        view: View,
        viewClickCallback: ViewClickCallback<TaskItem>
    ) -> Unit
) : ClickableViewHolder<TaskItem>(itemView, clickCallback) {
    private val binding = ItemTaskBinding.bind(itemView)
    override fun bind(item: TaskItem) {
        super.bind(item)
        binding.tvProjectName.text = item.name
        binding.tvTaskName.text = item.name
    }
}