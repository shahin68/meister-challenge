package com.shahin.meistersearch.data.remote.sources

import com.google.gson.Gson
import com.google.gson.JsonSyntaxException
import com.shahin.meistersearch.data.remote.models.response.error.ErrorResponse
import com.shahin.meistersearch.data.remote.models.response.error.ErrorItem
import com.shahin.meistersearch.network.NetworkResult
import retrofit2.Response

/**
 * wrapper responsible for extracting response messages
 */
open class RemoteRepositoryWrapper {
    inline fun <T : Any> networkResponseOf(service: () -> Response<T>): NetworkResult<T> {
        return try {
            val response = service()
            if (response.isSuccessful) {
                NetworkResult.Successful(response.body())
            } else {
                when (response.code()) {
                    500 -> NetworkResult.Error(
                        ErrorResponse(
                            arrayListOf(
                                ErrorItem(
                                    status = response.code(),
                                    message = "Internal server error!"
                                )
                            )
                        )
                    )
                    else -> {
                        try {
                            val gSon = Gson()
                            val typedValue = gSon.fromJson(
                                response.errorBody()?.string(),
                                ErrorResponse::class.java
                            )
                            NetworkResult.Error(typedValue)
                        } catch (e: JsonSyntaxException) {
                            NetworkResult.Error(
                                ErrorResponse(
                                   arrayListOf(
                                       ErrorItem(
                                           status = response.code(),
                                           message = response.message()
                                       )
                                   )
                                )
                            )
                        }
                    }
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
            NetworkResult.NetworkError
        }
    }
}