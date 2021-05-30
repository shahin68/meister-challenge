package com.shahin.meistersearch.data

import androidx.paging.PagingData
import com.shahin.meistersearch.data.local.models.relations.project_section.ProjectWithSections
import com.shahin.meistersearch.data.local.models.relations.project_section.SectionWithProjects
import com.shahin.meistersearch.data.local.models.relations.task_Section.TaskWithSections
import com.shahin.meistersearch.data.remote.models.response.search.SearchResponse
import com.shahin.meistersearch.data.remote.models.response.search.items.TaskResult
import com.shahin.meistersearch.network.NetworkResult
import kotlinx.coroutines.flow.Flow

interface Repository {
    fun getToken(): String
    suspend fun search(query: String): NetworkResult<SearchResponse>
    suspend fun searchPaging(query: String): Flow<PagingData<TaskResult>>
    suspend fun searchPagingWithDb(query: String): Flow<PagingData<TaskWithSections>>
    suspend fun getProjectsWithSections(projectId: Int): List<ProjectWithSections>
    suspend fun getSectionsWithProjects(projectId: Int): List<SectionWithProjects>
}