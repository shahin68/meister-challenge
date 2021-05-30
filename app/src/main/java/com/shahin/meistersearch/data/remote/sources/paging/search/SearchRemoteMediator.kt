package com.shahin.meistersearch.data.remote.sources.paging.search

import androidx.paging.*
import androidx.room.withTransaction
import com.shahin.meistersearch.data.local.models.entities.Project
import com.shahin.meistersearch.data.local.models.entities.Section
import com.shahin.meistersearch.data.local.models.entities.Task
import com.shahin.meistersearch.data.local.models.pagingkey.RemoteKey
import com.shahin.meistersearch.data.local.models.relations.project_section.ProjectSectionCrossRef
import com.shahin.meistersearch.data.local.models.relations.task_Section.TaskWithSections
import com.shahin.meistersearch.data.local.sources.room.AppDatabase
import com.shahin.meistersearch.data.remote.models.body.FilterBody
import com.shahin.meistersearch.data.remote.sources.paging.PagingDataSource
import com.shahin.meistersearch.general.paging.BaseRemoteMediator
import retrofit2.HttpException
import java.io.IOException

/**
 * Paging Source of Remote Mediator
 * Responsible for handling seamless paginated api calls returning combined
 * flow of DB & Network data
 *
 * ***Extended from [BaseRemoteMediator]*** which provides basic behaviour
 *
 * Returns [Flow] of [TaskWithSections]
 */
@OptIn(ExperimentalPagingApi::class)
class SearchRemoteMediator(
    private val filterBody: FilterBody,
    private val appDatabase: AppDatabase,
    private val pagingDataSource: PagingDataSource
) : BaseRemoteMediator<TaskWithSections>() {

    private val startPage = 1

    private val appDao = appDatabase.appDao()
    private val remoteKeyDao = appDatabase.remoteKeyDao()

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, TaskWithSections>
    ): MediatorResult {
        return try {
            val page = when (loadType) {
                LoadType.REFRESH -> {
                    val remoteKeys = getRemoteKeyClosestToCurrentPosition(state)
                    remoteKeys?.nextKey?.minus(1) ?: startPage
                }
                LoadType.PREPEND -> {
                    val remoteKeys = getRemoteKeyForFirstItem(state)
                    val prevKey = remoteKeys?.prevKey
                        ?: return MediatorResult.Success(endOfPaginationReached = remoteKeys != null)
                    prevKey
                }
                LoadType.APPEND -> {
                    val remoteKeys = getRemoteKeyForLastItem(state)
                    val nextKey = remoteKeys?.nextKey
                        ?: return MediatorResult.Success(endOfPaginationReached = remoteKeys != null)
                    nextKey
                }
            }

            val pageSize = state.config.pageSize

            val response = pagingDataSource.search(
                filterBody, page, pageSize
            )

            val body = response.body() ?: return MediatorResult.Error(Throwable("No Data"))
            val tasks =
                body.searchResult.tasks.map { Task(it.id, it.name, it.status, it.sectionId) }
            val sections = body.searchResult.sections.map { Section(it.id, it.projectId) }
            val projects = body.searchResult.projects.map { Project(it.id, it.name) }
            val totalResponseResults = body.pagingResult.totalResults
            val totalResponsePages = body.pagingResult.totalPages
            val currentResponsePage = body.pagingResult.currentPage
            val endOfPaginationReached = tasks.isEmpty() ||
                    (totalResponseResults == 0) ||
                    (totalResponsePages == 0) ||
                    (currentResponsePage == 0) ||
                    (page > currentResponsePage) ||
                    (page > totalResponsePages)
            appDatabase.withTransaction {
                // clear all tables in the database
                if (loadType == LoadType.REFRESH) {
                    remoteKeyDao.clearRemoteKeys()
                }
                val prevKey = if (page == startPage) null else page - 1
                val nextKey = if (endOfPaginationReached) null else page + 1
                val keys = tasks.map {
                    RemoteKey(
                        queryValue = it.taskName,
                        prevKey = prevKey,
                        nextKey = nextKey,
                        lastUpdate = System.currentTimeMillis()
                    )
                }
                remoteKeyDao.insertAll(keys)

                appDao.insertAllTasks(
                    tasks
                )
                appDao.insertAllSections(
                    sections
                )
                appDao.insertAllProjects(
                    projects
                )
                projects.forEach {
                    appDao.insertProjectSectionCrossRefs(
                        ProjectSectionCrossRef(
                            it.projectId,
                            sections.find { e -> e.sectionProjectId == it.projectId }?.sectionProjectId
                                ?: 0
                        )
                    )
                }
            }
            MediatorResult.Success(endOfPaginationReached = endOfPaginationReached)
        } catch (e: IOException) {
            MediatorResult.Error(e)
        } catch (e: HttpException) {
            MediatorResult.Error(e)
        }
    }

    private suspend fun getRemoteKeyForLastItem(state: PagingState<Int, TaskWithSections>): RemoteKey? {
        return state.pages.lastOrNull {
            it.data.isNotEmpty()
        }?.data?.lastOrNull()
            ?.let { taskResult ->
                remoteKeyDao.getRemoteKeyByQuery(taskResult.task.taskName)
            }
    }

    private suspend fun getRemoteKeyForFirstItem(state: PagingState<Int, TaskWithSections>): RemoteKey? {
        return state.pages.firstOrNull {
            it.data.isNotEmpty()
        }?.data?.firstOrNull()
            ?.let { taskResult ->
                remoteKeyDao.getRemoteKeyByQuery(taskResult.task.taskName)
            }
    }

    private suspend fun getRemoteKeyClosestToCurrentPosition(
        state: PagingState<Int, TaskWithSections>
    ): RemoteKey? {
        return state.anchorPosition?.let { position ->
            state.closestItemToPosition(position)?.task?.taskName?.let { query ->
                remoteKeyDao.getRemoteKeyByQuery(query)
            }
        }
    }
}