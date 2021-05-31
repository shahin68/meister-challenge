package com.shahin.meistersearch.data.local.models.pagingkey

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "remote_keys")
data class RemoteKey(
    @PrimaryKey
    val queryValue: String,
    val prevKey: Int?,
    val nextKey: Int?,
    val lastUpdate: Long?
)
