package com.shahin.meistersearch.data.remote.sources

import androidx.paging.PagingData
import com.shahin.meistersearch.data.remote.models.body.FilterBody
import com.shahin.meistersearch.data.remote.models.response.search.SearchResponse
import com.shahin.meistersearch.data.remote.models.response.search.items.TaskItem
import com.shahin.meistersearch.network.NetworkResult
import kotlinx.coroutines.flow.Flow

interface RemoteRepository {
    suspend fun search(filterBody: FilterBody): NetworkResult<SearchResponse>
    suspend fun searchPaging(filterBody: FilterBody): Flow<PagingData<TaskItem>>
}