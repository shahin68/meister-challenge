package com.shahin.meistersearch.data

import com.shahin.meistersearch.data.local.models.Model

interface Repository {
    fun getModel(): Model
}