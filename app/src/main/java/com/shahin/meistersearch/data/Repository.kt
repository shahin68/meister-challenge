package com.shahin.meistersearch.data

import com.shahin.meistersearch.data.remote.models.response.search.SearchResponse
import com.shahin.meistersearch.network.NetworkResult

interface Repository {
    fun getToken(): String
    suspend fun search(query: String): NetworkResult<SearchResponse>
}