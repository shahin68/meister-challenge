package com.shahin.meistersearch.data.remote.sources

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.google.gson.Gson
import com.shahin.meistersearch.data.local.models.relations.task_Section.TaskWithSections
import com.shahin.meistersearch.data.local.sources.room.AppDatabase
import com.shahin.meistersearch.data.remote.models.body.FilterBody
import com.shahin.meistersearch.data.remote.models.response.search.SearchResponse
import com.shahin.meistersearch.data.remote.models.response.search.items.TaskResult
import com.shahin.meistersearch.data.remote.services.Api
import com.shahin.meistersearch.data.remote.sources.paging.PagingDataSource
import com.shahin.meistersearch.data.remote.sources.paging.search.SearchPagingRepository
import com.shahin.meistersearch.data.remote.sources.paging.search.SearchRemoteMediator
import com.shahin.meistersearch.network.NetworkResult
import kotlinx.coroutines.flow.Flow

class RemoteRepositoryImpl(
    private val api: Api,
    private val gson: Gson,
    private val pagingDataSource: PagingDataSource,
    private val appDatabase: AppDatabase
): RemoteRepositoryWrapper(), RemoteRepository {

    private val appDao = appDatabase.appDao()

    /**
     * Simple search Api Call
     */
    override suspend fun search(filterBody: FilterBody): NetworkResult<SearchResponse> {
        return networkResponseOf {
            api.search(
                filter = gson.toJson(filterBody)
            )
        }
    }

    /**
     * Paginated Only network search api calls
     */
    override suspend fun searchPaging(filterBody: FilterBody): Flow<PagingData<TaskResult>> {
        return Pager(
            config = PagingConfig(
                pageSize = 50
            ),
            pagingSourceFactory = {
                SearchPagingRepository(
                    filterBody,
                    pagingDataSource
                )
            }
        ).flow
    }


    /**
     * Paginated DB flow & Network search api calls
     */
    @OptIn(ExperimentalPagingApi::class)
    override suspend fun searchPagingWithDb(filterBody: FilterBody): Flow<PagingData<TaskWithSections>> {
        return Pager(
            config = PagingConfig(
                pageSize = 50,
                enablePlaceholders = false
            ),
            remoteMediator = SearchRemoteMediator(
                filterBody,
                appDatabase,
                pagingDataSource
            ),
        ) {
            if (filterBody.status.isEmpty()) {
                appDao.getTasksWithSections()
            } else {
                appDao.getTasksWithSectionsByStatus(filterBody.status.first())
            }
        }.flow
    }

}