package com.example.importanttodos.test.steps

import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.example.importanttodos.MainActivity
import io.cucumber.junit.WithJunitRule
import org.junit.Rule

@WithJunitRule
class ActivityRuleHolder {
    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

}