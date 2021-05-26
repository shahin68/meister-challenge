package com.shahin.meistersearch.data.remote.sources

import com.shahin.meistersearch.data.remote.models.body.FilterBody
import com.shahin.meistersearch.data.remote.models.response.search.SearchResponse
import com.shahin.meistersearch.network.NetworkResult

interface RemoteRepository {
    suspend fun search(filterBody: FilterBody): NetworkResult<SearchResponse>
}