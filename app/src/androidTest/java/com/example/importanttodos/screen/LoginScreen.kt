package com.example.importanttodos.screen
import com.example.importanttodos.R
import com.example.importanttodos.common.factory.UserFactory


class LoginScreen() : BaseScreen() {
    companion object {
        private val email = R.id.email_input
        private val password = R.id.password_input
        private val loginButton = R.id.siginup_button
        private val titleTodo = R.id.todo_title

    }

    fun validateElementsLogin() {
        this.checkIsDisplayed(email)
        this.checkIsDisplayed(password)
        this.checkIsDisplayed(loginButton)
    }

    fun login(conditional: String) {
        val userData = UserFactory.getLoginUser(conditional)
        this.enterTextIntoView(email, userData.email)
        this.enterTextIntoView(password, userData.password)
        this.clickOnView(loginButton)
    }

    fun isPagetodo() {
        this.checkIsDisplayed(titleTodo)
    }
}