package com.example.importanttodos.test.steps

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.example.importanttodos.AppDatabase
import com.example.importanttodos.User
import com.example.importanttodos.UserDao
import com.example.importanttodos.test.screen.LoginScreen
import com.example.importanttodos.test.screen.RegisterScreen
import io.cucumber.java.After
import io.cucumber.java.Before
import io.cucumber.java.en.Given
import io.cucumber.java.en.Then
import io.cucumber.java.en.When

class LoginSteps {
    val loginScreen: LoginScreen = LoginScreen()
    val registerScreen: RegisterScreen = RegisterScreen()

    @Before
    fun setup() {
        registerScreen.goRegister()
        registerScreen.register("valid")
    }

    @After
    fun teardowm() {
    }

    @Given("I access login page")
    fun validateLoginPage() {
        loginScreen.validateElementsLogin()
    }

    @When("as user I make login with {string} data")
    fun loginUser(conditional: String) {
        loginScreen.login(conditional)
    }

    @Then("I see the todo title")
    fun userInTodoPage() {
        loginScreen.isPagetodo()
    }

}