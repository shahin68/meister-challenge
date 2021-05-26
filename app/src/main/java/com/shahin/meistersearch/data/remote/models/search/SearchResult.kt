package com.shahin.meistersearch.data.remote.models.search

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import com.shahin.meistersearch.data.remote.models.search.items.ProjectItem
import com.shahin.meistersearch.data.remote.models.search.items.SectionItem
import com.shahin.meistersearch.data.remote.models.search.items.TaskItem
import kotlinx.parcelize.Parcelize

@Parcelize
data class SearchResult(

    @SerializedName("projects")
    val projects: List<ProjectItem>? = null,

    @SerializedName("tasks")
    val tasks: List<TaskItem>? = null,

    @SerializedName("sections")
    val sections: List<SectionItem>? = null
) : Parcelable