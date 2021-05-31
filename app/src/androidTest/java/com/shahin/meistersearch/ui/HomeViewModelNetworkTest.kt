package com.shahin.meistersearch.ui

import FakeRepositoryAndroidImpl
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import com.google.common.truth.Truth
import com.shahin.meistersearch.data.remote.models.body.FilterBody
import com.shahin.meistersearch.data.remote.models.response.paging.PagingResult
import com.shahin.meistersearch.data.remote.models.response.search.SearchResponse
import com.shahin.meistersearch.data.remote.models.response.search.SearchResult
import com.shahin.meistersearch.network.NetworkResult
import com.shahin.meistersearch.ui.fragments.home.HomeViewModel
import getOrAwaitValueAndroid
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(AndroidJUnit4::class)
class HomeViewModelNetworkTest {

    @Rule
    @JvmField
    var mActivityTestRule = ActivityTestRule(MainActivity::class.java)

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var viewModel: HomeViewModel

    @Before
    fun before() {
        viewModel = HomeViewModel(FakeRepositoryAndroidImpl())
    }

    @After
    fun tearDown() {
    }

    @Test
    fun testSearchApiCallShouldPass() = runBlocking {
        viewModel.search("buy")

        val search = viewModel.searchResult.getOrAwaitValueAndroid()

        Truth.assertThat(search).isEqualTo(
            NetworkResult.Successful(
                SearchResponse(
                    PagingResult(0, 0, 0, 0),
                    SearchResult(
                        emptyList(),
                        emptyList(),
                        emptyList()
                    )
                )
            )
        )
    }

    @Test
    fun testPagingShouldPass() = runBlocking {
        val response = viewModel.searchPaging("").toList()

        print(response)
        Truth.assertThat(response).isNotNull()
    }

    @Test
    fun testPagingDbShouldPass() = runBlocking {
        val response = viewModel.searchPagingWithDb(
            FilterBody(
                "Hi", emptyList()
            )
        ).toList()

        print(response)
        Truth.assertThat(response).isNotNull()
    }
}