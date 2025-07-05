package com.example.importanttodos

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface UserDao {

    @Insert
    fun insert(user: User)

    @Update
    fun update(user: User)

    @Delete
    fun delete(user: User)

    @Query("SELECT * FROM user WHERE id = :userId")
    fun get(userId: Long): LiveData<User?>

    @Query("SELECT * FROM user WHERE email = :email")
    fun getUserEmail(email: String): User?

    @Query("SELECT * FROM user ORDER BY id DESC")
    fun getAll(): LiveData<List<User>>
}
