package com.example.importanttodos.test.integration

import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.importanttodos.MainActivity
import com.example.importanttodos.data.User
import com.example.importanttodos.common.rule.TestDatabaseRule
import com.example.importanttodos.robots.LoginRobot
import com.example.importanttodos.robots.TodosRobot
import com.example.importanttodos.robots.login
import com.example.importanttodos.robots.result
import com.example.importanttodos.robots.todos
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Testes de integração para o TODO app usando banco de dados isolado
 * 
 * Esta abordagem é SEGURA porque:
 * - Usa um banco de dados em memória completamente isolado
 * - Não contamina dados de produção
 * - Cada teste roda com dados limpos
 * - Cleanup automático após cada teste
 * - Testes são independentes e reproduzíveis
 */
@RunWith(AndroidJUnit4::class)
class TodosUiTest {
    
    private lateinit var testUser: User
    @get:Rule
    val testDatabaseRule = TestDatabaseRule()
    
    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    @Before
    fun setupTest() {
        testUser = testDatabaseRule.createTestUser()
        login {
            makeLogin("valid")
        }.isPagetodo()
        println("✅ Test setup completed with isolated database")
    }

    @Test
    fun addTodo_UsingTodosScreen() {
        val todoTitle = "Coffee helps you stay productive"
        result { validateTodoScreenElements() }
        todos {addTodo(todoTitle)}.validateTodoExists(todoTitle)
        println("✅ Add TODO test completed successfully")
    }

    @Test
    fun addAndUpdateTodo_UsingTodosScreen() {
        val originalTitle = "Coffee is good"
        val newTitle = "Coffee is excellent"
        result {validateTodoScreenElements() }
        todos { addTodo(originalTitle) }.validateTodoExists(originalTitle)
        todos{ editTodo(originalTitle, newTitle) }.validateTodoExists(newTitle)
        println("✅ Update TODO test completed successfully")
    }

    @Test
    fun addAndDeleteTodo_UsingTodosScreen() {
        val todoTitle = "Buy groceries"
        todos { addTodo(todoTitle) }
        result { validateTodoExists(todoTitle) }
        todos { deleteTodo(todoTitle) }
        result { validateTodoDoesNotExist(todoTitle) }
        println("✅ Delete TODO test completed successfully")
    }

}