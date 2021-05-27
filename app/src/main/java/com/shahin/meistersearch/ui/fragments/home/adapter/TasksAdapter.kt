package com.shahin.meistersearch.ui.fragments.home.adapter

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.shahin.meistersearch.R
import com.shahin.meistersearch.data.remote.models.response.search.items.TaskItem
import com.shahin.meistersearch.general.extensions.inflate
import com.shahin.meistersearch.general.views.ViewClickType
import com.shahin.meistersearch.ui.fragments.home.viewholders.TaskViewHolder

class TasksAdapter(
    private val clickBlock: (
        view: View,
        viewClickType: ViewClickType<TaskItem>
    ) -> Unit
): ListAdapter<TaskItem, TaskViewHolder>(DiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        return TaskViewHolder(
            parent.inflate(R.layout.item_task),
            clickBlock
        )
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    object DiffCallback : DiffUtil.ItemCallback<TaskItem>() {
        override fun areItemsTheSame(oldItem: TaskItem, newItem: TaskItem): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: TaskItem, newItem: TaskItem): Boolean {
            return oldItem.name.equals(newItem.name)
        }
    }

    override fun submitList(list: List<TaskItem>?) {
        super.submitList(ArrayList(list))
    }
}