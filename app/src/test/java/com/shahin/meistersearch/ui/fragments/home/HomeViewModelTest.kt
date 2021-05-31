package com.shahin.meistersearch.ui.fragments.home

import com.google.common.truth.Truth
import com.shahin.meistersearch.data.Repository
import com.shahin.meistersearch.data.remote.FakeRepositoryImpl
import com.shahin.meistersearch.di.networkModule
import com.shahin.meistersearch.di.repositoryModule
import com.shahin.meistersearch.di.roomModule
import org.junit.After
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.test.KoinTest
import org.koin.test.get
import org.koin.test.inject

/**
 * Unit Test puts the repository tree on test until and tests results in view model
 */
class HomeViewModelTest: KoinTest {

    private lateinit var viewModel: HomeViewModel

    @Before
    fun setUp() {
        startKoin {
            modules(
                repositoryModule,
                networkModule
            )
        }
        viewModel = HomeViewModel(FakeRepositoryImpl())
    }

    @After
    fun tearDown() {
        stopKoin()
    }

    @Test
    fun `test flow of data from local repo to homeViewModel - should return true`() {
        val token = viewModel.getToken()
        print(token)
        Truth.assertThat(token.isNotBlank())
    }

}