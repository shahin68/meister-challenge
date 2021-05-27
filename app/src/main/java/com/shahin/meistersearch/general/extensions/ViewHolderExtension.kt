package com.shahin.meistersearch.general.extensions

import android.graphics.drawable.Drawable
import android.view.View
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView

/**
 * ViewHolder extension to ease setting click listener on itemView
 * @return [RecyclerView.ViewHolder]
 */
fun <T : RecyclerView.ViewHolder> T.onClick(event: (view: View, position: Int, type: Int) -> Unit): T {
    itemView.setOnClickListener {
        event.invoke(it, adapterPosition, itemViewType)
    }
    return this
}

/**
 * ViewHolder extension to ease setting long click listener on itemView
 * @return [RecyclerView.ViewHolder]
 */
fun <T : RecyclerView.ViewHolder> T.onLongClick(event: (view: View, position: Int, type: Int) -> Unit): T {
    itemView.setOnClickListener {
        event.invoke(it, adapterPosition, itemViewType)
    }
    return this
}

/**
 * ViewHolder extension to ease getting color
 * @return color associated with color resource id
 */
fun <T : RecyclerView.ViewHolder> T.getColor(@ColorRes colorRes: Int): Int {
    return ContextCompat.getColor(itemView.context, colorRes)
}

/**
 * ViewHolder extension to ease getting drawable
 * @return drawable associated with drawable resource id
 */
fun <T : RecyclerView.ViewHolder> T.getDrawable(@DrawableRes drawableId: Int): Drawable? {
    return ContextCompat.getDrawable(itemView.context, drawableId)
}