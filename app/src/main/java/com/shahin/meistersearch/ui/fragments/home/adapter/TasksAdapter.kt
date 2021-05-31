package com.shahin.meistersearch.ui.fragments.home.adapter

import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.shahin.meistersearch.databinding.ItemTaskBinding
import com.shahin.meistersearch.general.extensions.inflater
import com.shahin.meistersearch.general.views.ViewClickCallback
import com.shahin.meistersearch.ui.fragments.home.models.TaskItem
import com.shahin.meistersearch.ui.fragments.home.viewholders.TaskViewHolder

class TasksAdapter(
    private val clickCallback: (
        view: View,
        position: Int,
        viewClickCallback: ViewClickCallback<TaskItem>
    ) -> Unit
): PagingDataAdapter<TaskItem, TaskViewHolder>(DiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        return TaskViewHolder(
            ItemTaskBinding.inflate(
                parent.inflater(), parent, false
            ),
            clickCallback
        )
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        val item = getItem(position)
        if (item != null) {
            holder.bind(item)
        }
    }

    object DiffCallback : DiffUtil.ItemCallback<TaskItem>() {
        override fun areItemsTheSame(oldItem: TaskItem, newItem: TaskItem): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: TaskItem, newItem: TaskItem): Boolean {
            return oldItem.taskName.equals(newItem.taskName, ignoreCase = true)
        }
    }

}