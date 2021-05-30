package com.shahin.meistersearch.data.remote.sources.paging

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.paging.PagingSource
import com.google.common.truth.Truth.assertThat
import com.shahin.meistersearch.data.remote.models.body.FilterBody
import com.shahin.meistersearch.data.remote.models.response.search.items.TaskItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class PagingRepositoryTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private val testDispatcher = TestCoroutineDispatcher()

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        testDispatcher.cleanupTestCoroutines()
    }

    @Test
    fun `test one page should pass`() = runBlockingTest {
        val filterBody = FilterBody(
            text = "hello"
        )
        val taskItem = TaskItem(
            id = 0
        )
        val list = arrayListOf<TaskItem>()
        for (i in 0 until 50) {
            list.add(taskItem)
        }
        val pagingSource = PagingRepository(
            filterBody,
            FakePagingDataSource()
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
    fun `test empty filter should pass`() = runBlockingTest {
        val filterBody = FilterBody()
        val pagingSource = PagingRepository(
            filterBody,
            FakePagingDataSource()
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