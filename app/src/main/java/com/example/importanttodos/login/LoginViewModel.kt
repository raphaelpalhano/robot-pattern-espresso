package com.example.importanttodos.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.importanttodos.data.UserDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.mindrot.jbcrypt.BCrypt

class LoginViewModel(private val dao: UserDao) : ViewModel() {
    private val _navigateToList = MutableLiveData<Boolean>()
    val navigateToList: LiveData<Boolean> get() = _navigateToList

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> get() = _errorMessage

    fun login(email: String, inputPassword: String) {
        if (email.isEmpty()) {
            _errorMessage.postValue("Por favor, insira o e-mail.")
            return
        }

        if (inputPassword.isEmpty()) {
            _errorMessage.postValue( "Por favor, insira a senha.")
            return
        }

        viewModelScope.launch(Dispatchers.IO) {
            try {
                val user = dao.getUserEmail(email)
                if (user != null) {
                    // Verifica a senha usando BCrypt
                    if (BCrypt.checkpw(inputPassword, user.password)) {
                        _navigateToList.postValue( true)
                    } else {
                        _errorMessage.postValue("Senha inválida.")
                    }
                } else {
                    _errorMessage.postValue("Usuário não encontrado.")
                }
            } catch (e: Exception) {
                _errorMessage.postValue("Erro ao tentar logar.")
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