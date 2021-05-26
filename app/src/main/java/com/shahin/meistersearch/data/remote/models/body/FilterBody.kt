package com.shahin.meistersearch.data.remote.models.body

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

/**
 * Body envelop representing search api filter query
 */
@Parcelize
data class FilterBody(

    @SerializedName("text")
    val text: String? = null,

    @SerializedName("status")
    val status: List<Int>? = null
) : Parcelable
