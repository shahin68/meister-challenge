package com.shahin.meistersearch.data.remote.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ErrorResponse(
    val any: String? = null
) : Parcelable