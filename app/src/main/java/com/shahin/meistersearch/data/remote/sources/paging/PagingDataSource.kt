package com.shahin.meistersearch.data.remote.sources.paging

import com.shahin.meistersearch.data.remote.models.body.FilterBody
import com.shahin.meistersearch.data.remote.models.response.search.SearchResponse
import retrofit2.Response

interface PagingDataSource {
    suspend fun search(filterBody: FilterBody, page : Int, pageLimit: Int) : Response<SearchResponse>
}