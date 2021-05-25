package com.shahin.meistersearch.network

import com.shahin.meistersearch.data.remote.models.ErrorResponse

/**
 * class representing restricted network response
 * @param T is any type parameter restricting outputs
 */
sealed class NetworkResult<out T> {
    /**
     * interface representing successful response
     */
    data class Successful<out T>(val data: T? = null): NetworkResult<T>()

    /**
     * interface representing error response
     */
    data class Error<out T>(val error: ErrorResponse): NetworkResult<T>()

    /**
     * interface representing network failure
     * Note: Network failures occur before remote response,
     * therefore, no specific type parameter will be received
     */
    object NetworkError: NetworkResult<Nothing>()
}
