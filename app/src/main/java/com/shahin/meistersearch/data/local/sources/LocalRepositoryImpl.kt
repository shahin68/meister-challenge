package com.shahin.meistersearch.data.local.sources

import com.shahin.meistersearch.BuildConfig

class LocalRepositoryImpl: LocalRepository {

    override fun getToken(): String {
        return "${BuildConfig.ACCESS_TOKEN_PREFIX} ${BuildConfig.ACCESS_TOKEN}"
    }

}