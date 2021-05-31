package com.shahin.meistersearch.data.remote.models.response.search

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import com.shahin.meistersearch.data.remote.models.response.search.items.ProjectResult
import com.shahin.meistersearch.data.remote.models.response.search.items.SectionResult
import com.shahin.meistersearch.data.remote.models.response.search.items.TaskResult
import kotlinx.parcelize.Parcelize

@Parcelize
data class SearchResult(

    @SerializedName("projects")
    val projects: List<ProjectResult>,

    @SerializedName("tasks")
    val tasks: List<TaskResult>,

    @SerializedName("sections")
    val sections: List<SectionResult>
) : Parcelable