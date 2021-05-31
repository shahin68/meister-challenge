package com.shahin.meistersearch.general.utils

import android.view.View
import androidx.databinding.BindingAdapter
import com.google.android.material.progressindicator.LinearProgressIndicator
import com.shahin.meistersearch.general.extensions.visibleOrGone

class BindingAdapterUtils {
    companion object {
        @JvmStatic
        @BindingAdapter("app:viewIsVisible")
        fun setIsVisible(view: View, isVisible: Boolean) {
            view.visibleOrGone(isVisible)
        }

        @JvmStatic
        @BindingAdapter("app:viewIsEnable")
        fun setIsEnable(view: View, isEnable: Boolean) {
            view.isEnabled = isEnable
        }

        @JvmStatic
        @BindingAdapter("app:showOrHideLoading")
        fun setShowOrHide(view: LinearProgressIndicator, showLoading: Boolean) = when {
            showLoading -> {
                view.show()
            }
            else -> {
                view.hide()
            }
        }
    }
}