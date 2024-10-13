package com.example.importanttodos

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class TodosIntegrationTest {
    object User {
        const val email: String = "raphael1angel@gmail.com"
        const val pass: String = "coca123"
    }

    @Before
    fun loginUser() {
        onView(withId(R.id.email_input)).perform(ViewActions.typeText(User.email))
        onView(withId(R.id.password_input)).perform(ViewActions.typeText(User.pass))
        onView(withId(R.id.siginup_button)).perform(ViewActions.click())
    }


    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun addTodo() {
        val originalValueTitle = "Coffe help you"
        onView(withId(R.id.todo_title)).perform(ViewActions.typeText(originalValueTitle))
        onView(withId(R.id.todo_save_button)).perform(ViewActions.click())


    }

    @Test
    fun addAndUpdateTodo() {
        val originalTitle = "Coffe is nice"
        val newValueTitle = "Coffe is very good"
        onView(withId(R.id.todo_title)).perform(ViewActions.typeText(originalTitle))
        onView(withId(R.id.todo_save_button)).perform(ViewActions.click())
        onView(withId(R.id.email_input)).check(ViewAssertions.matches(withText(originalTitle)))
        onView(withId(R.id.email_input)).perform(ViewActions.click())
        onView(withId(R.id.email_input)).perform(ViewActions.clearText())
        onView(withId(R.id.email_input)).perform(ViewActions.typeText(newValueTitle))
        onView(withId(R.id.siginup_button)).perform(ViewActions.click())
        onView(withId(R.id.email_input)).check(ViewAssertions.matches(withText(newValueTitle)))
    }


    @Test
    fun addAndDelete() {
        val titleTmp = "Raphael deve dormir mais cedo"
        onView(withId(R.id.todo_title)).perform(ViewActions.typeText(titleTmp))
        onView(withId(R.id.todo_save_button)).perform(ViewActions.click())
        onView(withId(R.id.email_input)).check(ViewAssertions.matches(withText(titleTmp)))
        onView(withId(R.id.email_input)).perform(ViewActions.click())
        onView(withId(R.id.delete_button)).perform(ViewActions.click())
    }




}