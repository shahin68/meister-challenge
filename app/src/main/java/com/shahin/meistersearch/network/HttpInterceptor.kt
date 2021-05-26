package com.shahin.meistersearch.network

import com.shahin.meistersearch.data.local.sources.LocalRepository
import okhttp3.Interceptor
import okhttp3.Response
import java.util.*

/**
 * http client interceptor
 * responsible for intercepting http request calls
 * and adding headers
 */
class HttpInterceptor(
    private val localRepository: LocalRepository
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()

        val requestBuilder = request.newBuilder()

        /**
         * Case 1:  If Header already has "Exposed" header, means we don't need a token
         * And we also remove this header from the request to avoid sending unknown header to the server
         *
         * Case 2: If Header does Not have "Exposed" as header, means we need to add token
         * to the request header
         */
        if (request.header("Exposed") == null) {
            requestBuilder.addHeader("Authorization", localRepository.getToken())
        } else {
            requestBuilder.removeHeader("Exposed")
        }

        /**
         * Case 1:  If Header already has "Monolingual" header, means we don't need to add locale
         * And we also remove this header from the request to avoid sending unknown header to the server
         *
         * Case 2: If Header does Not have "Monolingual" as header, means we need to add locale
         * to the request header to receive localized & translated responses
         */
        if (request.header("Monolingual") == null) {
            val url = request.url.newBuilder().addQueryParameter(
                "Locale",
                Locale.getDefault().language
            ).build()
            requestBuilder.url(url)
        } else {
            requestBuilder.removeHeader("Monolingual")
        }

        return chain.proceed(requestBuilder.build())
    }

}