package com.shahin.meistersearch.data.remote

import androidx.paging.PagingData
import com.shahin.meistersearch.BuildConfig
import com.shahin.meistersearch.data.Repository
import com.shahin.meistersearch.data.local.models.entities.Project
import com.shahin.meistersearch.data.local.models.entities.Section
import com.shahin.meistersearch.data.local.models.entities.Task
import com.shahin.meistersearch.data.local.models.relations.project_section.ProjectWithSections
import com.shahin.meistersearch.data.local.models.relations.project_section.SectionWithProjects
import com.shahin.meistersearch.data.local.models.relations.task_Section.TaskWithSections
import com.shahin.meistersearch.data.remote.models.response.paging.PagingResult
import com.shahin.meistersearch.data.remote.models.response.search.SearchResponse
import com.shahin.meistersearch.data.remote.models.response.search.SearchResult
import com.shahin.meistersearch.data.remote.models.response.search.items.TaskResult
import com.shahin.meistersearch.network.NetworkResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

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
        return flowOf(
            PagingData.from(
                listOf(
                    TaskResult(
                        1,
                        100,
                        "shahin",
                        1
                    ),
                    TaskResult(
                        2,
                        200,
                        "shahin",
                        1
                    )
                )
            )
        )
    }

    override suspend fun searchPagingWithDb(query: String): Flow<PagingData<TaskWithSections>> {
        return flowOf(
            PagingData.from(
                listOf(
                    TaskWithSections(
                        Task(
                            1,
                            "Task 1",
                            1,
                            100
                        ),
                        listOf(
                            Section(
                                100,
                                1000
                            )
                        )
                    )
                )
            )
        )
    }

    override suspend fun getProjectsWithSections(projectId: Int): List<ProjectWithSections> {
        return listOf(
            ProjectWithSections(
                Project(
                    1000,
                    "Project 1"
                ),
                listOf(
                    Section(
                        100,
                        1000
                    )
                )
            )
        )
    }

    override suspend fun getSectionsWithProjects(projectId: Int): List<SectionWithProjects> {
        return listOf(
            SectionWithProjects(
                Section(
                    100,
                    1000
                ),
                listOf(
                    Project(
                        1000,
                        "Project 1"
                    )
                )
            )
        )
    }
}