package com.shahin.meistersearch.general.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState

abstract class BasePagingSource<T: Any>: PagingSource<Int, T>() {

    override fun getRefreshKey(state: PagingState<Int, T>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

}