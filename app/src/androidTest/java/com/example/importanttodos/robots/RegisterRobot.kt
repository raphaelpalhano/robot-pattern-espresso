package com.example.importanttodos.robots
import com.example.importanttodos.R
import com.example.importanttodos.common.components.registerComponents
import com.example.importanttodos.common.factory.UserFactory

fun register(func: RegisterRobot.() -> Unit) = RegisterRobot().apply { func() }

class RegisterRobot() : BaseTestRobot() {

    fun goRegister() {
        this.clickOnView(registerComponents.signupLink)
    }

    fun register(conditional: String) {
        val userData = UserFactory.getRegisterUser(conditional)
        this.enterTextIntoView(registerComponents.name, userData.name)
        this.enterTextIntoView(registerComponents.email, userData.email)
        this.enterTextIntoView(registerComponents.password, userData.password)
        this.clickOnView(registerComponents.registerbutton)
    }

}