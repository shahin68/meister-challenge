package com.shahin.meistersearch

import com.shahin.meistersearch.di.networkModule
import com.shahin.meistersearch.di.repositoryModule
import com.shahin.meistersearch.di.uiModule
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.koin.test.check.checkModules

/**
 * Unit Test checks whether modules are properly defined
 */
class TestKoinModules {

    @Before
    fun setUp() {
    }

    @After
    fun tearDown() {
    }

    @Test
    fun `check modules`() {
        checkModules {
            modules(
                uiModule,
                repositoryModule,
                networkModule
            )
        }
    }
}