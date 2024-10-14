package com.example.importanttodos

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user")
data class User (
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0L,
    var name: String = "",
    var email: String = "",
    var password: String = ""
    )