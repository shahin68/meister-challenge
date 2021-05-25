package com.shahin.meistersearch.data.local.sources

import com.shahin.meistersearch.data.local.models.Model

interface LocalRepository {
    @Deprecated("To be removed") fun getModel(): Model
    fun getToken(): String
}