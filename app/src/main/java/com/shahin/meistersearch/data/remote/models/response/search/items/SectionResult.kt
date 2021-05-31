package com.shahin.meistersearch.data.remote.models.response.search.items

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class SectionResult(

    @SerializedName("project_id")
    val projectId: Int,

    @SerializedName("id")
    val id: Int
) : Parcelable