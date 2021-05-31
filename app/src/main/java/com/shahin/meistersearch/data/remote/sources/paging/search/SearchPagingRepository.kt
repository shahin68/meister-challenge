package com.shahin.meistersearch.data.remote.sources.paging.search

import com.shahin.meistersearch.data.remote.models.body.FilterBody
import com.shahin.meistersearch.data.remote.models.response.search.items.TaskResult
import com.shahin.meistersearch.data.remote.sources.paging.PagingDataSource
import com.shahin.meistersearch.general.paging.BasePagingSource
import retrofit2.HttpException
import java.io.IOException

/**
 * Paging Source
 * Responsible for handling seamless paginated api calls
 *
 * This source class does not handle any DB process
 * and only is designed to return a stream of paginated api responses
 *
 * Paging 3 also provides RemoteMediator Helper class to use to integrate
 * a combined flow of Network & DB data
 * @see SearchRemoteMediator
 *
 * ***Extended from [BasePagingSource] which provides basic behaviour***
 *
 * Returns [Flow] of [TaskResult]
 */
class SearchPagingRepository(
    private val filterBody: FilterBody,
    private val pagingDataSource: PagingDataSource
) : BasePagingSource<TaskResult>() {

    private val startPage = 1
    private val limit = 50

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, TaskResult> {
        val position = params.key ?: startPage

        try {
            val response = pagingDataSource.search(
                filterBody, position, limit
            )
            val body = response.body() ?: return LoadResult.Error(Throwable("No Data"))
            val tasks = body.searchResult.tasks
            val totalResults = (body.pagingResult.totalResults ?: 0)
            return LoadResult.Page(
                data = tasks,
                prevKey = if (position == startPage) null else position - 1,
                nextKey = if (totalResults == 0 || position * limit > totalResults) null else position + 1
            )

        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            return LoadResult.Error(exception)
        }
    }
}