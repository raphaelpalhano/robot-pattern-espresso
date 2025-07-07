package com.example.importanttodos.common.rule

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.example.importanttodos.data.AppDatabase
import com.example.importanttodos.data.DatabaseProvider
import com.example.importanttodos.data.User
import kotlinx.coroutines.runBlocking
import org.junit.rules.TestRule
import org.junit.runner.Description
import org.junit.runners.model.Statement
import org.mindrot.jbcrypt.BCrypt

/**
 * JUnit Rule que gerencia um banco de dados de teste isolado
 * 
 * Esta versão usa DatabaseProvider em vez de reflection, sendo:
 * - Mais simples e confiável
 * - Não depende de reflection
 * - Mais fácil de manter
 * - Completamente isolado do banco de produção
 */
class TestDatabaseRule : TestRule {
    
    private var testDatabase: AppDatabase? = null
    
    val database: AppDatabase
        get() = testDatabase ?: throw IllegalStateException("Database not initialized. Make sure the rule is properly set up.")
    
    override fun apply(base: Statement, description: Description): Statement {
        return object : Statement() {
            override fun evaluate() {
                setupTestDatabase()
                try {
                    base.evaluate()
                } finally {
                    tearDownTestDatabase()
                }
            }
        }
    }
    
    private fun setupTestDatabase() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        
        // Criar banco de teste isolado em memória
        testDatabase = Room.inMemoryDatabaseBuilder(
            context,
            AppDatabase::class.java
        ).allowMainThreadQueries().build()
        
        // Configurar o DatabaseProvider para usar o banco de teste
        DatabaseProvider.setTestDatabase(testDatabase)
        
        println("✅ Test database setup completed - using DatabaseProvider with isolated in-memory database")
        println("   Using test database: ${DatabaseProvider.isUsingTestDatabase()}")
    }
    
    private fun tearDownTestDatabase() {
        try {
            // Limpar o DatabaseProvider
            DatabaseProvider.clearTestDatabase()
            
            println("✅ Test database cleanup completed")
        } catch (e: Exception) {
            println("⚠️ Error during test database cleanup: ${e.message}")
        } finally {
            testDatabase = null
        }
    }
    
    /**
     * Cria e insere um usuário de teste no banco isolado
     */
    fun createTestUser(
        name: String = "Rafa",
        email: String = "rafa1@gmail.com",
        password: String = "coca123"
    ): User {
        val testUser = User(
            0L, // Room auto-generates ID
            name,
            email,
            BCrypt.hashpw(password, BCrypt.gensalt())
        )
        
        runBlocking {
            database.userDao.insert(testUser)
        }
        
        println("✅ Test user created: $email")
        return testUser
    }
    
    /**
     * Limpa todos os dados do banco de teste
     */
    fun clearAllData() {
        runBlocking {
            try {
                // Limpar todos os TODOs
                val todos = database.todosDao.getAll().value
                todos?.forEach { todo ->
                    database.todosDao.delete(todo)
                }
                
                // Limpar todos os usuários
                val users = database.userDao.getAll().value
                users?.forEach { user ->
                    database.userDao.delete(user)
                }
                
                println("✅ All test data cleared")
            } catch (e: Exception) {
                println("⚠️ Error clearing test data: ${e.message}")
            }
        }
    }
} 