package com.example.importanttodos.todo

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.importanttodos.data.TodosDao

class TodosViewModelFactory(private val dao: TodosDao): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(TodosDao::class.java).newInstance(dao)
    }
}
