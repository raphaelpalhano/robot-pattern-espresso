package com.example.importanttodos.todo

import androidx.recyclerview.widget.DiffUtil
import com.example.importanttodos.data.Todo

class TodoItemCallback: DiffUtil.ItemCallback<Todo>() {
    override fun areItemsTheSame(oldItem: Todo, newItem: Todo): Boolean = oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: Todo, newItem: Todo): Boolean = oldItem == newItem
}
