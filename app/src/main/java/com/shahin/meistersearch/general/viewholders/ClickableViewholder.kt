package com.shahin.meistersearch.general.viewholders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.shahin.meistersearch.general.extensions.onClick
import com.shahin.meistersearch.general.views.ViewClickCallback

/**
 * clickable ViewHolder
 * @param T is any data class representing content of the view holder
 * @param clickCallback is a callback returning item content
 */
abstract class ClickableViewHolder<T>(
    itemView: View,
    private val clickCallback: (
        view: View,
        viewClickCallback: ViewClickCallback<T>
    ) -> Unit
) : RecyclerView.ViewHolder(itemView) {
    open fun bind(item: T) {
        onClick { _, _, _ ->
            clickCallback.invoke(
                itemView,
                ViewClickCallback.ToOpen(item)
            )
        }
    }
}
