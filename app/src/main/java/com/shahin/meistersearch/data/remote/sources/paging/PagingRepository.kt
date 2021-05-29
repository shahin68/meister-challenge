package com.shahin.meistersearch.data.remote.sources.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.shahin.meistersearch.data.remote.models.body.FilterBody
import com.shahin.meistersearch.data.remote.models.response.search.items.TaskItem
import retrofit2.HttpException
import java.io.IOException

class PagingRepository(
    private val filterBody: FilterBody,
    private val pagingDataSource: PagingDataSource
) : PagingSource<Int, TaskItem>() {

    private val startPage = 1
    private val limit = 50

    override fun getRefreshKey(state: PagingState<Int, TaskItem>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, TaskItem> {
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