package com.example.importanttodos.data

import android.content.Context
import androidx.room.Room

/**
 * Provedor de banco de dados que pode ser facilmente substituído para testes
 * 
 * Esta abordagem é mais limpa e segura que usar reflection
 */
object DatabaseProvider {
    
    @Volatile
    private var testDatabase: AppDatabase? = null
    
    /**
     * Obtém a instância do banco de dados
     * Se uma instância de teste estiver definida, ela será usada
     * Caso contrário, usa o banco de produção
     */
    fun getDatabase(context: Context): AppDatabase {
        return testDatabase ?: AppDatabase.getInstance(context)
    }
    
    /**
     * Define uma instância de banco de dados para testes
     * Usado pelos testes para injetar um banco de teste isolado
     */
    fun setTestDatabase(database: AppDatabase?) {
        testDatabase = database
    }
    
    /**
     * Verifica se está usando banco de teste
     */
    fun isUsingTestDatabase(): Boolean {
        return testDatabase != null
    }
    
    /**
     * Limpa a instância de teste
     */
    fun clearTestDatabase() {
        testDatabase?.close()
        testDatabase = null
    }
} 