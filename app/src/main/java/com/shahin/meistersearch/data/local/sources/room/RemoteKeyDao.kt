package com.shahin.meistersearch.data.local.sources.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.shahin.meistersearch.data.local.models.pagingkey.RemoteKey

/**
 * A Remote Key DAO
 *
 * This class is created to provide access to tables handling paging keys
 * such as Query, Next & Previous Pages and Last Updated Timestamp
 *
 * @see RemoteKey
 */
@Dao
interface RemoteKeyDao {

    //region Paging Mediator Remote Key Queries
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(remoteKey: List<RemoteKey>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrReplace(remoteKey: RemoteKey)

    @Query("SELECT * FROM remote_keys WHERE queryValue = :query")
    suspend fun getRemoteKeyByQuery(query: String): RemoteKey?

    @Query("DELETE FROM remote_keys WHERE queryValue = :query")
    suspend fun deleteByQuery(query: String)

    @Query("DELETE FROM remote_keys")
    suspend fun clearRemoteKeys()
    //endregion

}