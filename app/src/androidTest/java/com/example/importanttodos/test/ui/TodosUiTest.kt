package com.example.importanttodos.test.integration

import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.importanttodos.MainActivity
import com.example.importanttodos.data.User
import com.example.importanttodos.common.rule.TestDatabaseRule
import com.example.importanttodos.screen.LoginScreen
import com.example.importanttodos.screen.TodosScreen
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
    
    // Screens para interação com a UI
    private val loginScreen: LoginScreen = LoginScreen()
    private val todosScreen: TodosScreen = TodosScreen()
    
    // Usuário de teste
    private lateinit var testUser: User
    
    // Rule que gerencia o banco de dados de teste isolado
    @get:Rule
    val testDatabaseRule = TestDatabaseRule()
    
    // Rule para a Activity
    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    @Before
    fun setupTest() {
        // Criar usuário de teste no banco isolado
        testUser = testDatabaseRule.createTestUser()
        loginScreen.login("valid")
        loginScreen.isPagetodo()
        
        println("✅ Test setup completed with isolated database")
    }

    @Test
    fun addTodo_UsingTodosScreen() {
        val todoTitle = "Coffee helps you stay productive"
        
        todosScreen.validateTodoScreenElements()
        
        todosScreen.addTodo(todoTitle)
        
        todosScreen.validateTodoExists(todoTitle)
        
        println("✅ Add TODO test completed successfully")
    }

    @Test
    fun addAndUpdateTodo_UsingTodosScreen() {
        val originalTitle = "Coffee is good"
        val newTitle = "Coffee is excellent"
        
        todosScreen.addTodo(originalTitle)
        todosScreen.validateTodoExists(originalTitle)
        
        todosScreen.editTodo(originalTitle, newTitle)
        
        todosScreen.validateTodoExists(newTitle)
        
        println("✅ Update TODO test completed successfully")
    }

    @Test
    fun addAndDeleteTodo_UsingTodosScreen() {
        val todoTitle = "Buy groceries"
        
        todosScreen.addTodo(todoTitle)
        todosScreen.validateTodoExists(todoTitle)
        
        todosScreen.deleteTodo(todoTitle)
        
        todosScreen.validateTodoDoesNotExist(todoTitle)
        
        println("✅ Delete TODO test completed successfully")
    }

    @Test
    fun markTodoAsCompleted_UsingTodosScreen() {
        val todoTitle = "Read a book"
        
        todosScreen.addTodo(todoTitle)
        todosScreen.validateTodoExists(todoTitle)
        
        todosScreen.markTodoAsCompleted(todoTitle)
        
        todosScreen.validateTodoExists(todoTitle)
        
        println("✅ Mark TODO as completed test completed successfully")
    }
    
    @Test
    fun multipleTodos_IndependentTest() {
        
        val todo1 = "Task 1"
        val todo2 = "Task 2"
        val todo3 = "Task 3"
        
        todosScreen.addTodo(todo1)
        todosScreen.addTodo(todo2)
        todosScreen.addTodo(todo3)
        
        todosScreen.validateTodoExists(todo1)
        todosScreen.validateTodoExists(todo2)
        todosScreen.validateTodoExists(todo3)
        
        todosScreen.deleteTodo(todo2)
        
        todosScreen.validateTodoExists(todo1)
        todosScreen.validateTodoDoesNotExist(todo2)
        todosScreen.validateTodoExists(todo3)
        
        println("✅ Multiple TODOs test completed successfully")
    }
}