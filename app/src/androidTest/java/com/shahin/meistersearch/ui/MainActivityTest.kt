package com.shahin.meistersearch.ui


import android.view.View
import android.view.ViewGroup
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.NoMatchingViewException
import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import com.google.common.truth.Truth
import com.shahin.meistersearch.R
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf
import org.hamcrest.TypeSafeMatcher
import org.hamcrest.core.IsInstanceOf
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito

@LargeTest
@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    @Rule
    @JvmField
    var mActivityTestRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun mainActivityTest() {
        val emptyView = onView(
            allOf(
                withId(R.id.empty_view),
                withParent(withParent(IsInstanceOf.instanceOf(android.view.ViewGroup::class.java))),
                isDisplayed()
            )
        )
        emptyView.check(matches(isDisplayed()))

        val searchAutoComplete = onView(
            allOf(
                withId(R.id.search_src_text),
                childAtPosition(
                    allOf(
                        withId(R.id.search_plate),
                        childAtPosition(
                            withId(R.id.search_edit_frame),
                            1
                        )
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        searchAutoComplete.perform(replaceText("buy"), closeSoftKeyboard())

        val editText = onView(
            allOf(
                withId(R.id.search_src_text), withText("buy"),
                withParent(
                    allOf(
                        withId(R.id.search_plate),
                        withParent(withId(R.id.search_edit_frame))
                    )
                ),
                isDisplayed()
            )
        )
        editText.check(matches(withText("buy")))

        val recyclerView = onView(
            allOf(
                withId(R.id.list_tasks),
                withParent(withParent(IsInstanceOf.instanceOf(android.view.ViewGroup::class.java))),
                isDisplayed()
            )
        )
        recyclerView.check(matches(isDisplayed()))

        val appCompatImageView = onView(
            allOf(
                withId(R.id.search_close_btn), withContentDescription("Clear query"),
                childAtPosition(
                    allOf(
                        withId(R.id.search_plate),
                        childAtPosition(
                            withId(R.id.search_edit_frame),
                            1
                        )
                    ),
                    1
                ),
                isDisplayed()
            )
        )
        appCompatImageView.perform(click())

        recyclerView.check(matches(isDisplayed()))

        val searchAutoComplete2 = onView(
            allOf(
                withId(R.id.search_src_text),
                childAtPosition(
                    allOf(
                        withId(R.id.search_plate),
                        childAtPosition(
                            withId(R.id.search_edit_frame),
                            1
                        )
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        searchAutoComplete2.perform(replaceText("hhhhh"), closeSoftKeyboard())

        emptyView.check(matches(isDisplayed()))
    }

    @Test
    fun testIfErrorTvWithNoDataStringIsShownShouldPass() {
        val query = "buy"


        val searchView = onView(
            allOf(
                withId(R.id.search_view)
            )
        )
        searchView.perform(replaceText(query))

        val errorTv = onView(
            allOf(
                withId(R.id.tv_error),
                withText(R.string.home_error_no_data)
            )
        ).isVisible()

        Truth.assertThat(errorTv).isFalse()
    }

    @Test
    fun testQuicklyClearingSearchViewShouldPass() {
        val query = "buy"

        val searchView = onView(
            allOf(
                withId(R.id.search_view),
                isDisplayed()
            )
        )

        Mockito.`when`(searchView.perform(replaceText(query)))
            .thenReturn(searchView.perform(replaceText("")))


        val loading = onView(
            allOf(
                withId(R.id.loading)
            )
        ).isVisible()

        Truth.assertThat(loading).isFalse()
    }

    private fun ViewInteraction.isVisible(): Boolean {
        try {
            check(matches(isDisplayed()))
            return true
        } catch (e: NoMatchingViewException) {
            return false
        }
    }

    private fun childAtPosition(
        parentMatcher: Matcher<View>, position: Int
    ): Matcher<View> {

        return object : TypeSafeMatcher<View>() {
            override fun describeTo(description: Description) {
                description.appendText("Child at position $position in parent ")
                parentMatcher.describeTo(description)
            }

            public override fun matchesSafely(view: View): Boolean {
                val parent = view.parent
                return parent is ViewGroup && parentMatcher.matches(parent)
                        && view == parent.getChildAt(position)
            }
        }
    }
}
