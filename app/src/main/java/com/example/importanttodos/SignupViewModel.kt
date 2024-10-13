package com.example.importanttodos

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import org.mindrot.jbcrypt.BCrypt

class SignupViewModel(private val dao: UserDao) : ViewModel() {

    val errorMessage = MutableLiveData<String?>()

    private val _navigateToLogin = MutableLiveData<Boolean>()
    val navigateToLogin: LiveData<Boolean>
        get() = _navigateToLogin

    fun signup(name: String, email: String, password: String) {
        viewModelScope.launch {
            try {
                val existingUser = dao.getUserEmail(email)
                if (existingUser != null) {
                    errorMessage.value = "Email já cadastrado"
                } else {
                    val cryptoPass =  BCrypt.hashpw(password, BCrypt.gensalt(10))
                    val newUser = User(name = name, email = email, password = cryptoPass)
                    dao.insert(newUser)
                    _navigateToLogin.value = true
                }
            } catch (e: Exception) {
                errorMessage.value = "Erro ao cadastrar: ${e.message}"
            }
        }
    }

    fun onNavigatedToLogin() {
        _navigateToLogin.value = false
    }
}
