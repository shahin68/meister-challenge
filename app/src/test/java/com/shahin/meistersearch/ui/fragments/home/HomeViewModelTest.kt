package com.shahin.meistersearch.ui.fragments.home

import com.google.common.truth.Truth
import com.shahin.meistersearch.data.Repository
import com.shahin.meistersearch.di.networkModule
import com.shahin.meistersearch.di.repositoryModule
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
    private val repository: Repository by inject()

    @Before
    fun setUp() {
        startKoin {
            modules(
                repositoryModule,
                networkModule
            )
        }
        viewModel = HomeViewModel(repository)
    }

    @After
    fun tearDown() {
        stopKoin()
    }

    @Test
    fun `test flow of data from local repo to homeViewModel - should return true`() {
        val state = viewModel.getToken()
        print(state)
        Truth.assertThat(state.isNotBlank())
    }

    @Test
    fun `test repository repository injection - should pass`() {
        assertNotNull(get<Repository>())
    }
}