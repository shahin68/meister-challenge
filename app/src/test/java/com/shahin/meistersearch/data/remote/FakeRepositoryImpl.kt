package com.shahin.meistersearch.data.remote

import com.shahin.meistersearch.BuildConfig
import com.shahin.meistersearch.data.Repository
import com.shahin.meistersearch.data.remote.models.response.search.SearchResponse
import com.shahin.meistersearch.network.NetworkResult

class FakeRepositoryImpl: Repository {
    override fun getToken(): String {
        return BuildConfig.ACCESS_TOKEN
    }

    override suspend fun search(query: String): NetworkResult.Successful<SearchResponse> {
        return NetworkResult.Successful(SearchResponse())
    }
}