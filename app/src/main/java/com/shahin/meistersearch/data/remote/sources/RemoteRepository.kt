package com.shahin.meistersearch.data.remote.sources

import androidx.paging.PagingData
import com.shahin.meistersearch.data.local.models.relations.task_Section.TaskWithSections
import com.shahin.meistersearch.data.remote.models.body.FilterBody
import com.shahin.meistersearch.data.remote.models.response.search.SearchResponse
import com.shahin.meistersearch.data.remote.models.response.search.items.TaskResult
import com.shahin.meistersearch.network.NetworkResult
import kotlinx.coroutines.flow.Flow

interface RemoteRepository {
    suspend fun search(filterBody: FilterBody): NetworkResult<SearchResponse>
    suspend fun searchPaging(filterBody: FilterBody): Flow<PagingData<TaskResult>>
    suspend fun searchPagingWithDb(filterBody: FilterBody): Flow<PagingData<TaskWithSections>>
}