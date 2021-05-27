package com.shahin.meistersearch.di

import com.google.gson.Gson
import com.shahin.meistersearch.data.Repository
import com.shahin.meistersearch.data.RepositoryImpl
import com.shahin.meistersearch.data.local.sources.LocalRepository
import com.shahin.meistersearch.data.local.sources.LocalRepositoryImpl
import com.shahin.meistersearch.data.remote.sources.RemoteRepository
import com.shahin.meistersearch.data.remote.sources.RemoteRepositoryImpl
import org.koin.dsl.module

/**
 * module responsible for creation of repository classes
 */
val repositoryModule = module {
    single<LocalRepository> { LocalRepositoryImpl() }
    single<RemoteRepository> { RemoteRepositoryImpl(get(), get()) }
    single<Repository> { RepositoryImpl(get(), get()) }
    single { createGsonInstance() }
}

private fun createGsonInstance(): Gson {
    return Gson()
}