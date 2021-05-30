package com.shahin.meistersearch.general.paging

import androidx.paging.ExperimentalPagingApi
import androidx.paging.RemoteMediator

/**
 * Default Remote Mediator Behaviour
 */
@OptIn(ExperimentalPagingApi::class)
abstract class BaseRemoteMediator<T : Any>: RemoteMediator<Int, T>() {

    override suspend fun initialize(): InitializeAction {
        return InitializeAction.LAUNCH_INITIAL_REFRESH
    }

}