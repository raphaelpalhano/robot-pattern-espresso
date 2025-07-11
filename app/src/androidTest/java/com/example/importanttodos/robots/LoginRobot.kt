package com.example.importanttodos.robots
import com.example.importanttodos.R
import com.example.importanttodos.common.components.loginComponents
import com.example.importanttodos.common.factory.UserFactory


fun login(func: LoginRobot.() -> ResultRobot ): ResultRobot = LoginRobot().func()

class LoginRobot() : BaseTestRobot() {
    fun makeLogin(conditional: String): ResultRobot = apply {
        val userData = UserFactory.getLoginUser(conditional)
        this.enterTextIntoView(loginComponents.email, userData.email)
        this.enterTextIntoView(loginComponents.password, userData.password)
        this.clickOnView(loginComponents.loginButton)
    }.let { ResultRobot() }

}