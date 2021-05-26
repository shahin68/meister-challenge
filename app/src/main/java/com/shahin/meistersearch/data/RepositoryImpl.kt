package com.shahin.meistersearch.data

import com.shahin.meistersearch.data.local.sources.LocalRepository
import com.shahin.meistersearch.data.remote.sources.RemoteRepository

class RepositoryImpl(
    private val localRepository: LocalRepository,
    private val remoteRepository: RemoteRepository
): Repository {
    override fun getToken(): String {
        return localRepository.getToken()
    }
}