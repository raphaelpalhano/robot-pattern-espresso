package com.example.importanttodos

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface UserDao {

    @Insert
    suspend fun insert(user: User)

    @Update
    suspend fun update(user: User)

    @Delete
    suspend fun delete(user: User)

    @Query("SELECT * FROM user WHERE id = :userId")
    suspend fun get(userId: Long): User?

    @Query("SELECT * FROM user WHERE email = :email")
    suspend fun getUserEmail(email: String): User?

    @Query("SELECT * FROM user ORDER BY id DESC")
    fun getAll(): LiveData<List<User>>
}
