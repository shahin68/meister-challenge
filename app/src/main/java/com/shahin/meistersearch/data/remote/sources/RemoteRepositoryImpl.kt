package com.shahin.meistersearch.data.remote.sources

import com.shahin.meistersearch.data.remote.models.body.FilterBody
import com.shahin.meistersearch.data.remote.models.response.search.SearchResponse
import com.shahin.meistersearch.data.remote.services.Api
import com.shahin.meistersearch.network.NetworkResult

class RemoteRepositoryImpl(
    private val api: Api
): RemoteRepositoryWrapper(), RemoteRepository {

    override suspend fun search(filterBody: FilterBody): NetworkResult<SearchResponse> {
        return networkResponseOf {
            api.search(
                filter = filterBody
            )
        }
    }

}