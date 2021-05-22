package com.shahin.meistersearch.data.local.sources

import com.shahin.meistersearch.data.local.models.Model

class LocalRepositoryImpl: LocalRepository {
    override fun getModel(): Model {
        return Model(
            "Shahin", "Montazeri"
        )
    }
}