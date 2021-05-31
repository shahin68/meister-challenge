package com.shahin.meistersearch.ui.fragments.home.viewholders

import android.view.View
import com.shahin.meistersearch.databinding.ItemTaskBinding
import com.shahin.meistersearch.general.viewholders.ClickableViewHolder
import com.shahin.meistersearch.general.views.ViewClickCallback
import com.shahin.meistersearch.ui.fragments.home.models.TaskItem

class TaskViewHolder(
    private val binding: ItemTaskBinding,
    clickCallback: (
        view: View,
        position: Int,
        viewClickCallback: ViewClickCallback<TaskItem>
    ) -> Unit
) : ClickableViewHolder<TaskItem>(binding.root, clickCallback) {
    override fun bind(item: TaskItem) {
        super.bind(item)

        // Data Binding
        binding.task = item



        // View Binding Implementations
        /*binding.tvProjectName.text = item.projectName
        binding.tvTaskName.text = item.taskName*/
    }
}