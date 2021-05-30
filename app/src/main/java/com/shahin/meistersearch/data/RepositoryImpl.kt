package com.shahin.meistersearch.data

import androidx.paging.PagingData
import com.shahin.meistersearch.data.local.models.relations.project_section.ProjectWithSections
import com.shahin.meistersearch.data.local.models.relations.project_section.SectionWithProjects
import com.shahin.meistersearch.data.local.models.relations.task_Section.TaskWithSections
import com.shahin.meistersearch.data.local.sources.LocalRepository
import com.shahin.meistersearch.data.remote.models.body.FilterBody
import com.shahin.meistersearch.data.remote.models.response.search.SearchResponse
import com.shahin.meistersearch.data.remote.models.response.search.items.TaskResult
import com.shahin.meistersearch.data.remote.sources.RemoteRepository
import com.shahin.meistersearch.network.NetworkResult
import kotlinx.coroutines.flow.Flow

class RepositoryImpl(
    private val localRepository: LocalRepository,
    private val remoteRepository: RemoteRepository
): Repository {
    override fun getToken(): String {
        return localRepository.getToken()
    }

    override suspend fun search(query: String): NetworkResult<SearchResponse> {
        return remoteRepository.search(
            FilterBody(
                text = query
            )
        )
    }

    override suspend fun searchPaging(query: String): Flow<PagingData<TaskResult>> {
        return remoteRepository.searchPaging(
            FilterBody(
                text = query
            )
        )
    }

    override suspend fun searchPagingWithDb(query: String): Flow<PagingData<TaskWithSections>> {
        return remoteRepository.searchPagingWithDb(
            FilterBody(
                text = query
            )
        )
    }

    override suspend fun getProjectsWithSections(projectId: Int): List<ProjectWithSections> {
        return localRepository.getProjectsWithSections(projectId)
    }

    override suspend fun getSectionsWithProjects(projectId: Int): List<SectionWithProjects> {
        return localRepository.getSectionsWithProjects(projectId)
    }
}