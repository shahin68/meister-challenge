package com.shahin.meistersearch.general.extensions

import android.graphics.drawable.Drawable
import android.view.View
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView

fun <T : RecyclerView.ViewHolder> T.onClick(event: (view: View, position: Int, type: Int) -> Unit): T {
    itemView.setOnClickListener {
        event.invoke(it, adapterPosition, itemViewType)
    }
    return this
}

fun <T : RecyclerView.ViewHolder> T.onLongClick(event: (view: View, position: Int, type: Int) -> Unit): T {
    itemView.setOnClickListener {
        event.invoke(it, adapterPosition, itemViewType)
    }
    return this
}

fun <T : RecyclerView.ViewHolder> T.getColor(@ColorRes colorRes: Int): Int {
    return ContextCompat.getColor(itemView.context, colorRes)
}

fun <T : RecyclerView.ViewHolder> T.getDrawable(@DrawableRes drawableId: Int): Drawable? {
    return ContextCompat.getDrawable(itemView.context, drawableId)
}