package com.shahin.meistersearch.data.local.sources

import com.shahin.meistersearch.BuildConfig
import com.shahin.meistersearch.data.local.models.Model

class LocalRepositoryImpl: LocalRepository {
    override fun getModel(): Model {
        return Model(
            "Shahin", "Montazeri"
        )
    }

    override fun getToken(): String {
        return BuildConfig.ACCESS_TOKEN
    }
}