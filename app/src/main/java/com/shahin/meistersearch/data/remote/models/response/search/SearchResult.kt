package com.shahin.meistersearch.data.remote.models.response.search

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import com.shahin.meistersearch.data.remote.models.response.search.items.ProjectItem
import com.shahin.meistersearch.data.remote.models.response.search.items.SectionItem
import com.shahin.meistersearch.data.remote.models.response.search.items.TaskItem
import kotlinx.parcelize.Parcelize

@Parcelize
data class SearchResult(

    @SerializedName("projects")
    val projects: List<ProjectItem>,

    @SerializedName("tasks")
    val tasks: List<TaskItem>,

    @SerializedName("sections")
    val sections: List<SectionItem>
) : Parcelable