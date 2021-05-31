package com.shahin.meistersearch.di

import com.shahin.meistersearch.BuildConfig
import com.shahin.meistersearch.data.local.sources.LocalRepository
import com.shahin.meistersearch.data.remote.services.Api
import com.shahin.meistersearch.network.HttpInterceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * module responsible for creation of http client and retrofit client
 */
val networkModule = module {
    single { buildRetrofit(get()) }
    factory { buildOkHttpClient(get()) }
    factory { provideApiService(get()) }
}

fun provideApiService(retrofit: Retrofit): Api {
    return retrofit.create(Api::class.java)
}

/**
 * retrofit client builder
 */
fun buildRetrofit(okHttpClient: OkHttpClient): Retrofit {
    return Retrofit.Builder()
        .baseUrl(BuildConfig.HOST)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}

/**
 * http client
 */
private fun buildOkHttpClient(localRepository: LocalRepository): OkHttpClient {
    val logInterceptor = HttpLoggingInterceptor().apply {
        level = if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
    }
    return OkHttpClient.Builder()
        .connectTimeout(120, TimeUnit.SECONDS)
        .writeTimeout(120, TimeUnit.SECONDS)
        .readTimeout(120, TimeUnit.SECONDS)
//        .authenticator() // auth interceptor used to handle authentication and refresh token
        .addInterceptor(HttpInterceptor(localRepository))
        .addInterceptor(logInterceptor)
        .build()
}