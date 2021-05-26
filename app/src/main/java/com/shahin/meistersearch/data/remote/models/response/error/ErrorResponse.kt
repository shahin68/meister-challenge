package com.shahin.meistersearch.data.remote.models.response.error

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class ErrorResponse(

    @SerializedName("errors")
    val errors: List<ErrorItem>? = null
) : Parcelable
