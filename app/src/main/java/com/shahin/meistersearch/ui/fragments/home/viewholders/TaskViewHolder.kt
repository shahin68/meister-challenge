package com.shahin.meistersearch.ui.fragments.home.viewholders

import android.view.View
import com.shahin.meistersearch.databinding.ItemTaskBinding
import com.shahin.meistersearch.general.viewholders.ClickableViewHolder
import com.shahin.meistersearch.general.views.ViewClickCallback
import com.shahin.meistersearch.ui.fragments.home.models.TaskItem

class TaskViewHolder(
    itemView: View,
    clickCallback: (
        view: View,
        position: Int,
        viewClickCallback: ViewClickCallback<TaskItem>
    ) -> Unit
) : ClickableViewHolder<TaskItem>(itemView, clickCallback) {
    private val binding = ItemTaskBinding.bind(itemView)
    override fun bind(item: TaskItem) {
        super.bind(item)
        binding.tvProjectName.text = item.projectName
        binding.tvTaskName.text = item.taskName
    }
}