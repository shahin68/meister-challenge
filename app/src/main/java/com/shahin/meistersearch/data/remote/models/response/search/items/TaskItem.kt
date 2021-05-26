package com.shahin.meistersearch.data.remote.models.response.search.items

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class TaskItem(

    @SerializedName("assigned_to_id")
    val assignedToId: Int? = null,

    @SerializedName("notes")
    val notes: String? = null,

    @SerializedName("created_by_origin")
    val createdByOrigin: String? = null,

    @SerializedName("created_at")
    val createdAt: String? = null,

//	@SerializedName("flagged")
//	val flagged: Any? = null,

    @SerializedName("updated_at")
    val updatedAt: String? = null,

    @SerializedName("section_id")
    val sectionId: Int? = null,

    @SerializedName("total_cl_items_count")
    val totalClItemsCount: Int? = null,

//	@SerializedName("repeat")
//	val repeat: Any? = null,

//	@SerializedName("attachment_id")
//	val attachmentId: Any? = null,

    @SerializedName("id")
    val id: Int? = null,

    @SerializedName("status_updated_at")
    val statusUpdatedAt: String? = null,

//	@SerializedName("reminder")
//	val reminder: Any? = null,

//	@SerializedName("meta_information")
//	val metaInformation: Any? = null,

    @SerializedName("tracked_time")
    val trackedTime: Int? = null,

//	@SerializedName("priority")
//	val priority: Any? = null,

    @SerializedName("status_changed_by_id")
    val statusChangedById: Int? = null,

    @SerializedName("token")
    val token: String? = null,

    @SerializedName("sequence")
    val sequence: Double? = null,

    @SerializedName("completed_at")
    val completedAt: String? = null,

    @SerializedName("closed_cl_items_count")
    val closedClItemsCount: Int? = null,

    @SerializedName("due")
    val due: String? = null,

    @SerializedName("comments_count")
    val commentsCount: Int? = null,

    @SerializedName("name")
    val name: String? = null,

    @SerializedName("created_by_id")
    val createdById: Int? = null,

    @SerializedName("attachments_count")
    val attachmentsCount: Int? = null,

    @SerializedName("status")
    val status: Int? = null
) : Parcelable