package com.example.importanttodos.robots

import com.example.importanttodos.common.components.loginComponents
import com.example.importanttodos.common.components.registerComponents
import com.example.importanttodos.common.components.todosComponents


fun result(func: ResultRobot.() -> Unit) = ResultRobot().apply { func() }

class ResultRobot(): BaseTestRobot() {

    fun validateTodoScreenElements() {
        this.checkIsDisplayed(todosComponents.todoTitleInput)
        this.checkIsDisplayed(todosComponents.todoSaveButton)
        this.checkIsDisplayed(todosComponents.todosList)
    }

    fun validateElementsLogin() {
        this.checkIsDisplayed(loginComponents.email)
        this.checkIsDisplayed(loginComponents.password)
        this.checkIsDisplayed(loginComponents.loginButton)
    }

    fun isPagetodo() = apply {
        checkIsDisplayed(todosComponents.titleTodo)
    }

    fun validateElementsRegister() {
        this.checkIsDisplayed(registerComponents.name)
        this.checkIsDisplayed(registerComponents.password)
        this.checkIsDisplayed(registerComponents.registerbutton)
    }

    fun validateTodoExists(title: String) {
        this.checkViewContainsTextInList(todosComponents.todosList, title)
    }

    fun validateTodoDoesNotExist(title: String) {
        // Use a more specific check that doesn't throw exception when view is not found
        this.checkViewDoesNotExistInList(todosComponents.todosList, title)
    }
}