package com.shahin.meistersearch.data

import androidx.paging.PagingData
import com.shahin.meistersearch.data.remote.models.response.search.SearchResponse
import com.shahin.meistersearch.data.remote.models.response.search.items.TaskItem
import com.shahin.meistersearch.network.NetworkResult
import kotlinx.coroutines.flow.Flow

interface Repository {
    fun getToken(): String
    suspend fun search(query: String): NetworkResult<SearchResponse>
    suspend fun searchPaging(query: String): Flow<PagingData<TaskItem>>
}