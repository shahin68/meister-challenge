package com.shahin.meistersearch.data.local.sources

import com.shahin.meistersearch.BuildConfig
import com.shahin.meistersearch.data.local.sources.room.AppDatabase

class LocalRepositoryImpl(
    private val appDatabase: AppDatabase
): LocalRepository {

    override fun getToken(): String {
        return "${BuildConfig.ACCESS_TOKEN_PREFIX} ${BuildConfig.ACCESS_TOKEN}"
    }

}