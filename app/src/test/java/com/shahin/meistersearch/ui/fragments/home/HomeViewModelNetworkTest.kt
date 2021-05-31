package com.shahin.meistersearch.ui.fragments.home

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth.assertThat
import com.shahin.meistersearch.data.remote.FakeRepositoryImpl
import com.shahin.meistersearch.data.remote.models.body.FilterBody
import com.shahin.meistersearch.data.remote.models.response.paging.PagingResult
import com.shahin.meistersearch.data.remote.models.response.search.SearchResponse
import com.shahin.meistersearch.data.remote.models.response.search.SearchResult
import com.shahin.meistersearch.network.NetworkResult
import getOrAwaitValue
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class HomeViewModelNetworkTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var viewModel: HomeViewModel

    private val testDispatcher = TestCoroutineDispatcher()

    @Before
    fun before() {
        Dispatchers.setMain(testDispatcher)
        viewModel = HomeViewModel(FakeRepositoryImpl())
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        testDispatcher.cleanupTestCoroutines()
    }

    @Test
    fun `test search api call should pass`() {
        viewModel.search("buy")

        val search = viewModel.searchResult.getOrAwaitValue()

        assertThat(search).isEqualTo(NetworkResult.Successful(SearchResponse(
            PagingResult(0,0,0,0),
            SearchResult(
                emptyList(),
                emptyList(),
                emptyList()
            )
        )))
    }

    @Test
    fun `test paging should pass`() = runBlockingTest {
        val response = viewModel.searchPaging("").toList()

        print(response)
        assertThat(response).isNotNull()
    }

    @Test
    fun `test paging db should pass`() = runBlockingTest {
        val response = viewModel.searchPagingWithDb(
            FilterBody(
                "Hi", emptyList()
            )
        ).toList()

        print(response)
        assertThat(response).isNotNull()
    }
}