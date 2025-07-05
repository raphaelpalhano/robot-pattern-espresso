package com.example.importanttodos.test.screen

import android.app.Activity
import android.content.Context
import android.view.View
import androidx.annotation.IdRes
import androidx.annotation.StringRes
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.pressImeActionButton
import androidx.test.espresso.action.ViewActions.swipeDown
import androidx.test.espresso.action.ViewActions.swipeLeft
import androidx.test.espresso.action.ViewActions.swipeRight
import androidx.test.espresso.action.ViewActions.swipeUp
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.doesNotExist
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.intent.Intents.intended
import androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent
import androidx.test.espresso.matcher.RootMatchers.isDialog
import androidx.test.espresso.matcher.ViewMatchers.isClickable
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withHint
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withTagValue
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.platform.app.InstrumentationRegistry

import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.TypeSafeMatcher

import org.hamcrest.Matchers.not
import org.hamcrest.core.Is.`is`


open class BaseScreen {

    private val activityContext: Activity? = null

    val context: Context
        get() = InstrumentationRegistry.getInstrumentation().targetContext

    @Throws(InterruptedException::class)
    @JvmOverloads
    fun sleep(seconds: Int = 1) {
        Thread.sleep(seconds * 1000L)
    }

    fun checkIsDisplayed(@IdRes vararg viewIds: Int) {
        for (viewId in viewIds) {
            onView(withId(viewId)).check(matches(isDisplayed()))
        }
    }

    fun checkIsClickable(@IdRes vararg viewIds: Int) {
        for (viewId in viewIds) {
            onView(withId(viewId)).check(matches(isClickable()))
        }
    }

    fun checkIsHidden(@IdRes vararg viewIds: Int) {
        for (viewId in viewIds) {
            onView(withId(viewId)).check(matches(not(isDisplayed())))
        }
    }

    fun checkDoesNotExist(@IdRes vararg viewIds: Int) {
        for (viewId in viewIds) {
            onView(withId(viewId)).check(doesNotExist())
        }
    }

    fun checkViewHasText(@IdRes viewId: Int, @StringRes messageResId: Int) {
        onView(withId(viewId)).check(matches(withText(messageResId)))
    }

    fun checkViewHasDrawableAndTag(imageResId: Int) {
        onView(withTagValue(`is`(imageResId as Any))).check(matches(isDisplayed()))
    }

    fun scrollViewDown(@IdRes viewIds: Int) {
        onView(withId(viewIds)).perform(swipeUp(), click())
    }

    fun checkViewHasText(@IdRes viewId: Int, expected: String) {
        onView(withId(viewId)).check(matches(withText(expected)))
    }

    fun scrollViewUp(@IdRes viewIds: Int) {
        onView(withId(viewIds)).perform(swipeDown(), click())
    }

    fun checkViewContainsText(text: String) {
        onView(withText(text)).check(matches(isDisplayed()))
    }

    fun checkViewHasHint(@IdRes viewId: Int, @StringRes messageResId: String) {
        onView(withId(viewId)).check(matches(withHint(messageResId)))
    }


    fun clickOnView(@IdRes viewId: Int) {
        onView(withId(viewId)).perform(click())
    }

    fun pressBack() {
        Espresso.pressBack()
    }


    fun closeKeyboard() {
        Espresso.closeSoftKeyboard()
    }

    fun pressImeAction(@IdRes viewId: Int) {
        onView(withId(viewId)).perform(pressImeActionButton())
    }

    fun assertItTakeMeToScreen(targetClass: Class<*>) {
        intended(hasComponent(targetClass.name))
    }

    fun enterTextIntoView(@IdRes viewId: Int, text: String) {
        onView(withId(viewId)).perform(typeText(text))
        closeKeyboard()
    }

    fun checkDialogWithTextIsDisplayed(@StringRes messageResId: Int) {
        onView(withText(messageResId))
            .inRoot(isDialog())
            .check(matches(isDisplayed()))
    }

    fun checkDialogWithTextIsDisplayed(message: String) {
        onView(withText(message))
            .inRoot(isDialog())
            .check(matches(isDisplayed()))
    }

    fun swipeLeftOnView(@IdRes viewId: Int) {
        onView(withId(viewId)).perform(swipeLeft())
    }

    fun swipeRightOnView(@IdRes viewId: Int) {
        onView(withId(viewId)).perform(swipeRight())
    }

    fun clickOnCardForList(@IdRes viewId: Int, position: Int) {
        onView(withIndex(withId(viewId), position)).perform(click())
    }


    fun withIndex(matcher: Matcher<View>, index: Int): Matcher<View> {
        return object : TypeSafeMatcher<View>() {
            internal var currentIndex = 0

            override fun describeTo(description: Description) {
                description.appendText("with index: ")
                description.appendValue(index)
                matcher.describeTo(description)
            }

            public override fun matchesSafely(view: View): Boolean {
                return matcher.matches(view) && currentIndex++ == index
            }
        }
    }
}
