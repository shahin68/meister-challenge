package com.shahin.meistersearch.general.utils

import android.view.View
import androidx.databinding.BindingAdapter
import com.shahin.meistersearch.general.extensions.visibleOrGone

class BindingAdapterUtils {
    @BindingAdapter("app:isVisible")
    fun setIsVisible(view: View, isVisible: Boolean) {
        view.visibleOrGone(isVisible)
    }
}