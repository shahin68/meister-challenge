package com.shahin.meistersearch.data

import androidx.paging.PagingData
import com.shahin.meistersearch.data.local.sources.LocalRepository
import com.shahin.meistersearch.data.remote.models.body.FilterBody
import com.shahin.meistersearch.data.remote.models.response.search.SearchResponse
import com.shahin.meistersearch.data.remote.models.response.search.items.TaskItem
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

    override suspend fun searchPaging(query: String): Flow<PagingData<TaskItem>> {
        return remoteRepository.searchPaging(
            FilterBody(
                text = query
            )
        )
    }
}