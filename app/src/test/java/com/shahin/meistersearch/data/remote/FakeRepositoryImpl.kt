package com.shahin.meistersearch.data.remote

import androidx.paging.PagingData
import com.shahin.meistersearch.BuildConfig
import com.shahin.meistersearch.data.Repository
import com.shahin.meistersearch.data.local.models.relations.project_section.ProjectWithSections
import com.shahin.meistersearch.data.local.models.relations.project_section.SectionWithProjects
import com.shahin.meistersearch.data.local.models.relations.task_Section.TaskWithSections
import com.shahin.meistersearch.data.remote.models.response.paging.PagingResult
import com.shahin.meistersearch.data.remote.models.response.search.SearchResponse
import com.shahin.meistersearch.data.remote.models.response.search.SearchResult
import com.shahin.meistersearch.data.remote.models.response.search.items.TaskResult
import com.shahin.meistersearch.network.NetworkResult
import kotlinx.coroutines.flow.Flow

class FakeRepositoryImpl: Repository {
    override fun getToken(): String {
        return BuildConfig.ACCESS_TOKEN
    }

    override suspend fun search(query: String): NetworkResult.Successful<SearchResponse> {
        return NetworkResult.Successful(SearchResponse(
            PagingResult(0,0,0,0),
            SearchResult(
                emptyList(),
                emptyList(),
                emptyList()
            )
        ))
    }

    override suspend fun searchPaging(query: String): Flow<PagingData<TaskResult>> {
        TODO("Not yet implemented")
    }

    override suspend fun searchPagingWithDb(query: String): Flow<PagingData<TaskWithSections>> {
        TODO("Not yet implemented")
    }

    override suspend fun getProjectsWithSections(projectId: Int): List<ProjectWithSections> {
        TODO("Not yet implemented")
    }

    override suspend fun getSectionsWithProjects(projectId: Int): List<SectionWithProjects> {
        TODO("Not yet implemented")
    }
}