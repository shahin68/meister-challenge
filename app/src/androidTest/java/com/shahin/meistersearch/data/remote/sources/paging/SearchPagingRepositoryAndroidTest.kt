package com.shahin.meistersearch.data.remote.sources.paging

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.paging.PagingSource
import androidx.test.runner.AndroidJUnit4
import com.google.common.truth.Truth.assertThat
import com.shahin.meistersearch.data.remote.models.body.FilterBody
import com.shahin.meistersearch.data.remote.models.response.search.items.TaskResult
import com.shahin.meistersearch.data.remote.sources.paging.search.SearchPagingRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(AndroidJUnit4::class)
class SearchPagingRepositoryAndroidTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
    }

    @After
    fun tearDown() {
    }

    @Test
    fun testOnePageShouldPass() = runBlocking {
        val filterBody = FilterBody(
            text = "hello"
        )
        val taskItem = TaskResult(
            id = 0,
            name = "",
            status = 1,
            sectionId = 1
        )
        val list = arrayListOf<TaskResult>()
        for (i in 0 until 50) {
            list.add(taskItem)
        }
        val pagingSource = SearchPagingRepository(
            filterBody,
            FakePagingDataAndroidSource()
        )
        assertThat(
            pagingSource.load(
                PagingSource.LoadParams.Refresh(
                    key = 1,
                    loadSize = 50,
                    placeholdersEnabled = false
                )
            )
        ).isEqualTo(
            PagingSource.LoadResult.Page(
                data = list,
                prevKey = null,
                nextKey = 2
            )
        )
    }

    @Test
    fun testEmptyFilterShouldPass() = runBlocking {
        val filterBody = FilterBody()
        val pagingSource = SearchPagingRepository(
            filterBody,
            FakePagingDataAndroidSource()
        )
        assertThat(
            pagingSource.load(
                PagingSource.LoadParams.Refresh(
                    key = 1,
                    loadSize = 50,
                    placeholdersEnabled = false
                )
            )
        ).isEqualTo(
            PagingSource.LoadResult.Page(
                data = emptyList(),
                prevKey = null,
                nextKey = null
            )
        )
    }

}