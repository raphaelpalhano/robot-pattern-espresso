package com.example.importanttodos

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class UserViewModelFactory(private val dao: UserDao) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LoginViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return LoginViewModel(dao) as T
        } else if (modelClass.isAssignableFrom(SignupViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return SignupViewModel(dao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
