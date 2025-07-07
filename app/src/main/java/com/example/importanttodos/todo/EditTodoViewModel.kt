package com.example.importanttodos.todo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.importanttodos.data.TodosDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class EditTodoViewModel(todoId: Long, private val dao: TodosDao): ViewModel() {
    val todo = dao.get(todoId)
    private val _navigateToList = MutableLiveData<Boolean>()
    val navigateToList: LiveData<Boolean> get() = _navigateToList

    fun updateTodoItem() {
        viewModelScope.launch(Dispatchers.IO) {
            todo.value?.let { dao.update(it) }
            _navigateToList.postValue(true)
        }
    }

    fun deleteTodoItem() {
        viewModelScope.launch(Dispatchers.IO ) {
            todo.value?.let { dao.delete(it) }
            _navigateToList.postValue( true)
        }
    }

    fun onNavigatedToList() {
        _navigateToList.postValue( false)
    }
}
