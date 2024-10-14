package com.example.importanttodos

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import org.mindrot.jbcrypt.BCrypt

class LoginViewModel(private val dao: UserDao) : ViewModel() {
    private val _navigateToList = MutableLiveData<Boolean>()
    val navigateToList: LiveData<Boolean> get() = _navigateToList

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> get() = _errorMessage

    fun login(email: String, inputPassword: String) {
        if (email.isEmpty()) {
            _errorMessage.value = "Por favor, insira o e-mail."
            return
        }

        if (inputPassword.isEmpty()) {
            _errorMessage.value = "Por favor, insira a senha."
            return
        }

        viewModelScope.launch {
            try {
                val user = dao.getUserEmail(email)
                if (user != null) {
                    // Verifica a senha usando BCrypt
                    if (BCrypt.checkpw(inputPassword, user.password)) {
                        _navigateToList.value = true
                    } else {
                        _errorMessage.value = "Senha inválida."
                    }
                } else {
                    _errorMessage.value = "Usuário não encontrado."
                }
            } catch (e: Exception) {
                _errorMessage.value = "Erro ao tentar logar."
            }
        }
    }

    fun setErrorMessage(message: String) {
        _errorMessage.value = message
    }

    fun onNavigatedToList() {
        _navigateToList.value = false
    }
}