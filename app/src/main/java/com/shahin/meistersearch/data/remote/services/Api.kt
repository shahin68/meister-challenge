package com.shahin.meistersearch.data.remote.services

import com.shahin.meistersearch.data.remote.models.response.search.SearchResponse
import retrofit2.Response
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Query

interface Api {

    /**
     * ***interface calling search***
     *
     * [Headers]
     * Not Exposed (Needs Authorization Token in header)
     * Monolingual (No need to define a locale)
     *
     * @param filter
     * parameter defining query keyword and filtering
     *
     * @param itemSize
     * counts items in response with default value of 50
     *
     * @param page
     * pages responses starting from 1
     *
     * @param sort
     * sort=updated_at,-name -> ascending by updated_at and then descending by name
     * sort=id -> ascending by ID
     * sort=-id -> descending by ID
     *
     * @param responseFormat
     * defines response type
     *
     *
     *
     * Example of exposed (No token in header meaning it's exposed to calls):
     * ```kotlin
     *    @POST("")
     *    @Headers("Exposed: true", "Monolingual: true")
     *    suspend fun exposedCallExample(): Response<Any>
     * ```
     *
     *
     *@return search response
     */
    @POST("search")
    @Headers("Monolingual: true")
    suspend fun search(
        @Query("filter") filter: String,
        @Query("items") itemSize: Int = 50,
        @Query("page") page: Int = 1,
        @Query("sort") sort: String = "id",
        @Query("response_format") responseFormat: String = "object",
    ): Response<SearchResponse>
}