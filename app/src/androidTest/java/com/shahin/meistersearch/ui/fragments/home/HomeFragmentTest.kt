package com.shahin.meistersearch.ui.fragments.home

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.Espresso.onView
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import com.shahin.meistersearch.R
import com.shahin.meistersearch.ui.MainActivity
import org.hamcrest.Matchers.allOf
import org.junit.After
import org.junit.Before
import org.junit.Rule

import org.junit.Test
import org.junit.runner.RunWith


@LargeTest
@RunWith(AndroidJUnit4::class)
class HomeFragmentTest {

    @Rule
    @JvmField
    var mActivityTestRule = ActivityTestRule(MainActivity::class.java)

    @Before
    fun setUp() {
    }

    @After
    fun tearDown() {
    }

    @Test
    fun checkIfErrorTvIsDisplayedAfterQuery() {
        val scenario = launchFragmentInContainer<HomeFragment>(themeResId = R.style.AppTheme)
        val entry = "buy"

        val emailInput = onView(
            allOf(
                withId(R.id.search_view),
                isDisplayed()
            )
        )
        emailInput.perform(replaceText(entry))

        val loading = onView(
            allOf(
                withId(R.id.tv_error)
            )
        ).check(matches(isDisplayed()))
    }

    @Test
    fun checkIfTaskListIsDisplayedAfterQuery() {
        val scenario = launchFragmentInContainer<HomeFragment>(themeResId = R.style.AppTheme)
        val entry = "buy"

        val emailInput = onView(
            allOf(
                withId(R.id.search_view),
                isDisplayed()
            )
        )
        emailInput.perform(replaceText(entry))


        onView(
            allOf(
                withId(R.id.list_tasks)
            )
        ).check(matches(isDisplayed()))
    }

}