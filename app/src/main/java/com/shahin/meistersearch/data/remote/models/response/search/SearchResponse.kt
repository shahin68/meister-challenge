package com.shahin.meistersearch.data.remote.models.response.search

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import com.shahin.meistersearch.data.remote.models.response.paging.PagingResult
import kotlinx.parcelize.Parcelize

/**
 * Search Response Envelope
 *
 *
 * NOTE:
 *
 * Normally A General Model Class will be created to represent a Response Envelope
 * But since this project is just a demonstration piece,
 * And we don't have any other response model to deal with,
 * And for simplicity purposes
 * Only considered [SearchResponse] as general response envelope
 *
 *
 * Example of a more General Envelope:
 *
 * ```kotlin
 *
 *    data class ResponseEnv<T>(
 *
 *        @SerializedName("paging")
 *        val pagingResult: PagingResult,
 *
 *        @SerializedName("results")
 *        val results: T
 *
 *    )
 *
 * ```
 *
 * Where [T] is The result response body
 * And the usage of this class would as shown like below:
 *
 * ```kotlin
 *
 *    ResponseEnv<SearchResult>
 * ```
 * OR something like below:
 * ```kotlin
 *
 *    @POST("")
 *    @Headers("")
 *    suspend fun apiCall(): Response<ResponseEnv<SearchResult>>
 *
 * ```
 *
 *
 */
@Parcelize
data class SearchResponse(

	@SerializedName("paging")
	val pagingResult: PagingResult,

	@SerializedName("results")
	val searchResult: SearchResult
) : Parcelable


