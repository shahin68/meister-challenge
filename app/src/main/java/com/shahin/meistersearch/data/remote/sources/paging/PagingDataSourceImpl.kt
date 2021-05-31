package com.shahin.meistersearch.data.remote.sources.paging

import com.google.gson.Gson
import com.shahin.meistersearch.data.remote.models.body.FilterBody
import com.shahin.meistersearch.data.remote.models.response.search.SearchResponse
import com.shahin.meistersearch.data.remote.services.Api
import retrofit2.Response

class PagingDataSourceImpl (
    private val api: Api,
    private val gson: Gson
): PagingDataSource {

    override suspend fun search(
        filterBody: FilterBody,
        page: Int,
        pageLimit: Int
    ): Response<SearchResponse> {
        return api.search(
            filter = gson.toJson(filterBody),
            itemSize = pageLimit,
            page = page
        )
    }

}