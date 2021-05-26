package com.shahin.meistersearch.data.remote.models.response.search.items

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class SectionItem(

    @SerializedName("indicator")
    val indicator: Int? = null,

    @SerializedName("sequence")
    val sequence: Double? = null,

    @SerializedName("updated_at")
    val updatedAt: String? = null,

    @SerializedName("color")
    val color: String? = null,

    @SerializedName("project_id")
    val projectId: Int? = null,

    @SerializedName("name")
    val name: String? = null,

//	@SerializedName("limit")
//	val limit: Any? = null,

    @SerializedName("created_at")
    val createdAt: String? = null,

    @SerializedName("description")
    val description: String? = null,

    @SerializedName("id")
    val id: Int? = null,

    @SerializedName("status")
    val status: Int? = null
) : Parcelable