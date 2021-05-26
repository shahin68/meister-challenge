package com.shahin.meistersearch.data.remote.models.response.search.items

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class ProjectItem(

    @SerializedName("share_token_enabled")
    val shareTokenEnabled: Boolean? = null,

    @SerializedName("notes")
    val notes: String? = null,

    @SerializedName("color")
    val color: String? = null,

    @SerializedName("created_at")
    val createdAt: String? = null,

    @SerializedName("share_mode")
    val shareMode: Int? = null,

    @SerializedName("team_id")
    val teamId: Int? = null,

//	@SerializedName("type")
//	val type: Any? = null,

    @SerializedName("token")
    val token: String? = null,

    @SerializedName("mail_token")
    val mailToken: String? = null,

    @SerializedName("updated_at")
    val updatedAt: String? = null,

    @SerializedName("tasks_active_count")
    val tasksActiveCount: Int? = null,

    @SerializedName("tasks_trash_count")
    val tasksTrashCount: Int? = null,

//	@SerializedName("share_token")
//	val shareToken: Any? = null,

    @SerializedName("name")
    val name: String? = null,

    @SerializedName("tasks_complete_count")
    val tasksCompleteCount: Int? = null,

    @SerializedName("id")
    val id: Int? = null,

    @SerializedName("tasks_archive_count")
    val tasksArchiveCount: Int? = null,

    @SerializedName("status")
    val status: Int? = null
) : Parcelable