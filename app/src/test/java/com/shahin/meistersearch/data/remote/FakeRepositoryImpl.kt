package com.shahin.meistersearch.data.remote

import androidx.paging.PagingData
import com.shahin.meistersearch.BuildConfig
import com.shahin.meistersearch.data.Repository
import com.shahin.meistersearch.data.remote.models.response.paging.PagingResult
import com.shahin.meistersearch.data.remote.models.response.search.SearchResponse
import com.shahin.meistersearch.data.remote.models.response.search.SearchResult
import com.shahin.meistersearch.data.remote.models.response.search.items.TaskItem
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

    override suspend fun searchPaging(query: String): Flow<PagingData<TaskItem>> {
        TODO("Not yet implemented")
    }
}