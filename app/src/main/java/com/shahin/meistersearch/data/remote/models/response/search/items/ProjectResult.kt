package com.shahin.meistersearch.data.remote.models.response.search.items

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class ProjectResult(

    @SerializedName("name")
    val name: String,

    @SerializedName("id")
    val id: Int
) : Parcelable