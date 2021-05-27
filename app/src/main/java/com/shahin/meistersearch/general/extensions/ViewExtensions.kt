package com.shahin.meistersearch.general.extensions

import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible

/**
 * extension function to set view visibility as VISIBLE
 */
fun <T: View> T.visible() {
    visibility = View.VISIBLE
}

/**
 * extension function to set view visibility as GONE
 */
fun <T: View> T.gone() {
    visibility = View.GONE
}

/**
 * extension function to set view visibility as INVISIBLE
 */
fun <T: View> T.hide() {
    visibility = View.INVISIBLE
}

/**
 * extension function to set view visibility as VISIBLE or GONE
 */
fun <T: View> T.visibleOrGone(visible: Boolean) {
    if (visible) {
        visible()
    } else {
        gone()
    }
}

/**
 * extension function to toggle view visibility
 */
fun <T: View> T.toggleVisibleOrGone() {
    if (isVisible) {
        gone()
    } else {
        visible()
    }
}

/**
 * extension function to set view visibility as VISIBLE or INVISIBLE
 */
fun <T: View> T.visibleOrHide(show: Boolean) {
    if (show) {
        visible()
    } else {
        hide()
    }
}
