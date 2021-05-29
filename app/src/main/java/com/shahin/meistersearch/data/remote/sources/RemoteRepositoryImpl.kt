package com.shahin.meistersearch.data.remote.sources

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.google.gson.Gson
import com.shahin.meistersearch.data.remote.models.body.FilterBody
import com.shahin.meistersearch.data.remote.models.response.search.SearchResponse
import com.shahin.meistersearch.data.remote.models.response.search.items.TaskItem
import com.shahin.meistersearch.data.remote.services.Api
import com.shahin.meistersearch.data.remote.sources.paging.PagingDataSource
import com.shahin.meistersearch.data.remote.sources.paging.PagingRepository
import com.shahin.meistersearch.network.NetworkResult
import kotlinx.coroutines.flow.Flow

class RemoteRepositoryImpl(
    private val api: Api,
    private val gson: Gson,
    private val pagingDataSource: PagingDataSource
): RemoteRepositoryWrapper(), RemoteRepository {

    override suspend fun search(filterBody: FilterBody): NetworkResult<SearchResponse> {
        return networkResponseOf {
            api.search(
                filter = gson.toJson(filterBody)
            )
        }
    }

    override suspend fun searchPaging(filterBody: FilterBody): Flow<PagingData<TaskItem>> {
        return Pager(
            config = PagingConfig(
                pageSize = 50
            ),
            pagingSourceFactory = {
                PagingRepository(
                    filterBody,
                    pagingDataSource
                )
            }
        ).flow
    }
}