package com.example.importanttodos.screen

import com.example.importanttodos.R

class TodosScreen : BaseScreen() {
    companion object {
        private val todoTitleInput = R.id.todo_title
        private val todoSaveButton = R.id.todo_save_button
        private val todosList = R.id.todos_list
        
        // IDs da tela de edição
        private val editTodoTitleInput = R.id.email_input
        private val editTodoUpdateButton = R.id.siginup_button
        private val editTodoDeleteButton = R.id.delete_button
        private val editTodoCompletedCheckbox = R.id.todo_done
    }

    fun validateTodoScreenElements() {
        this.checkIsDisplayed(todoTitleInput)
        this.checkIsDisplayed(todoSaveButton)
        this.checkIsDisplayed(todosList)
    }

    fun addTodo(title: String) {
        this.enterTextIntoView(todoTitleInput, title)
        this.clickOnView(todoSaveButton)
        // Clear the input field after adding to avoid ambiguous view matching
        this.clearText(todoTitleInput)
    }

    fun editTodo(originalTitle: String, newTitle: String) {
        // Click on the todo with the original title (specifically in the list)
        this.clickOnViewWithTextInList(originalTitle)
        
        // Clear the text field and enter new title
        this.clearTextFromView(editTodoTitleInput)
        this.enterTextIntoView(editTodoTitleInput, newTitle)
        
        // Click update button
        this.clickOnView(editTodoUpdateButton)
    }

    fun deleteTodo(title: String) {
        // Click on the todo with the title (specifically in the list)
        this.clickOnViewWithTextInList(title)
        
        // Click delete button
        this.clickOnView(editTodoDeleteButton)
    }

    fun markTodoAsCompleted(title: String) {
        // Click on the todo with the title (specifically in the list)
        this.clickOnViewWithTextInList(title)
        
        // Click the completed checkbox
        this.clickOnView(editTodoCompletedCheckbox)
        
        // Click update button to save changes
        this.clickOnView(editTodoUpdateButton)
    }

    fun validateTodoExists(title: String) {
        // Be more specific - check that the text exists in the RecyclerView/list
        this.checkViewContainsTextInList(todosList, title)
    }

    fun validateTodoDoesNotExist(title: String) {
        // Use a more specific check that doesn't throw exception when view is not found
        this.checkViewDoesNotExistInList(todosList, title)
    }

    private fun clickOnViewWithText(text: String) {
        this.clickOnView(text)
    }

    private fun clickOnViewWithTextInList(text: String) {
        // Click on text that is specifically within the RecyclerView/list
        this.clickOnViewWithTextInList(todosList, text)
    }

    private fun clearTextFromView(viewId: Int) {
        // This method should be implemented in BaseScreen if not already available
        // For now, we'll assume it exists or we can implement it here
        this.clearText(viewId)
    }
}