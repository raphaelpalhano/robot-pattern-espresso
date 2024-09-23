package com.example.importanttodos

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.ViewAssertion
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class TodosIntegrationTest {

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
        onView(withId(R.id.todo_name)).check(ViewAssertions.matches(withText(originalTitle)))
        onView(withId(R.id.todo_name)).perform(ViewActions.click())
        onView(withId(R.id.todo_name)).perform(ViewActions.clearText())
        onView(withId(R.id.todo_name)).perform(ViewActions.typeText(newValueTitle))
        onView(withId(R.id.update_button)).perform(ViewActions.click())
        onView(withId(R.id.todo_name)).check(ViewAssertions.matches(withText(newValueTitle)))
    }


    @Test
    fun addAndDelete() {
        val titleTmp = "Raphael deve dormir mais cedo"
        onView(withId(R.id.todo_title)).perform(ViewActions.typeText(titleTmp))
        onView(withId(R.id.todo_save_button)).perform(ViewActions.click())
        onView(withId(R.id.todo_name)).check(ViewAssertions.matches(withText(titleTmp)))
        onView(withId(R.id.todo_name)).perform(ViewActions.click())
        onView(withId(R.id.delete_button)).perform(ViewActions.click())
    }




}