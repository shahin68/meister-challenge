package com.shahin.meistersearch.data.remote.sources

import com.google.gson.Gson
import com.shahin.meistersearch.data.remote.models.body.FilterBody
import com.shahin.meistersearch.data.remote.models.response.search.SearchResponse
import com.shahin.meistersearch.data.remote.services.Api
import com.shahin.meistersearch.network.NetworkResult

class RemoteRepositoryImpl(
    private val api: Api,
    private val gson: Gson
): RemoteRepositoryWrapper(), RemoteRepository {

    override suspend fun search(filterBody: FilterBody): NetworkResult<SearchResponse> {
        return networkResponseOf {
            api.search(
                filter = gson.toJson(filterBody)
            )
        }
    }

}