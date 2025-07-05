package com.example.importanttodos.test.screen
import com.example.importanttodos.R
import com.example.importanttodos.test.common.factory.UserFactory


class RegisterScreen() : BaseScreen() {
    companion object {
        private val name = R.id.name_input
        private val password = R.id.password_input
        private val registerbutton = R.id.signup_button
        private val email = R.id.email_input

        private val signupLink = R.id.signup_link
    }

    fun validateElementsRegister() {
        this.checkIsDisplayed(email)
        this.checkIsDisplayed(password)
        this.checkIsDisplayed(registerbutton)
    }
    fun goRegister() {
        this.clickOnView(signupLink)
    }

    fun register(conditional: String) {
        val userData = UserFactory.getRegisterUser(conditional)
        this.enterTextIntoView(name, userData.name)
        this.enterTextIntoView(email, userData.email)
        this.enterTextIntoView(password, userData.password)
        this.clickOnView(registerbutton)
    }

}