package com.shahin.meistersearch.data.local.sources

import com.shahin.meistersearch.data.local.models.Model

interface LocalRepository {
    fun getModel(): Model
}