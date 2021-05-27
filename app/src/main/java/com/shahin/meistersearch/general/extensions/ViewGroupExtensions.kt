package com.shahin.meistersearch.general.extensions

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

/**
 * Extension function to inflate [ViewGroup]
 * @return inflated view
 */
fun <T: ViewGroup> T.inflate(layoutRes: Int): View {
    return LayoutInflater.from(context).inflate(layoutRes, this, false)
}