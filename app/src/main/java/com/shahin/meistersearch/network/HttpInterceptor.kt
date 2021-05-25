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

        if (request.header("Exposed") == null) {
            requestBuilder.addHeader("Authorization", localRepository.getToken())
        } else {
            requestBuilder.removeHeader("Exposed")
        }

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