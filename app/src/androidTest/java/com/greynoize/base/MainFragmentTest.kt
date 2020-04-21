package com.greynoize.base

import android.content.Context
import android.view.inputmethod.InputMethodManager
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.rule.ActivityTestRule
import com.greynoize.base.ui.MainActivity
import com.greynoize.base.ui.main.adapter.MainAdapter
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test


class MainFragmentTest {
    @Rule
    @JvmField
    val activityTestRule = ActivityTestRule(MainActivity::class.java)

    @Before
    fun setUp() {
        initialDelay()
    }

    @Test
    fun loadingTest() {
        onView(withId(R.id.main_loading)).check(matches(withEffectiveVisibility(Visibility.GONE)))
    }

    @Test
    fun itemInListIsClickable() {
        onView(withId(R.id.main_list)).perform(RecyclerViewActions.actionOnItemAtPosition<MainAdapter.ViewHolder>(1, click()))
    }

    @Test
    fun itemScrollable() {
        onView(withId(R.id.main_list)).perform(RecyclerViewActions.scrollToPosition<MainAdapter.ViewHolder>(20))
        Assert.assertTrue(!isKeyboardShown())
    }

    private fun initialDelay() {
        Thread.sleep(1500) // can be replaced with more deep check
    }

    private fun isKeyboardShown(): Boolean {
        val inputMethodManager = InstrumentationRegistry.getInstrumentation().targetContext.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        return inputMethodManager.isAcceptingText
    }
}