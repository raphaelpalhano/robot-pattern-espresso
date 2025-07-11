package com.example.importanttodos.robots

import com.example.importanttodos.R
import com.example.importanttodos.common.components.todosComponents

fun todos(func: TodosRobot.() -> ResultRobot): ResultRobot = TodosRobot().func()
class TodosRobot : BaseTestRobot() {

    fun addTodo(title: String): ResultRobot = apply  {
        this.enterTextIntoView(todosComponents.todoTitleInput, title)
        this.clickOnView(todosComponents.todoSaveButton)
        this.clearText(todosComponents.todoTitleInput)
    }.let { ResultRobot() }

    fun editTodo(originalTitle: String, newTitle: String): ResultRobot = apply  {
        this.clickOnViewWithTextInList(originalTitle)
        this.clearTextFromView(todosComponents.editTodoTitleInput)
        this.enterTextIntoView(todosComponents.editTodoTitleInput, newTitle)
        this.clickOnView(todosComponents.editTodoUpdateButton)
    }.let{ ResultRobot() }

    fun deleteTodo(title: String): ResultRobot = apply  {
        this.clickOnViewWithTextInList(title)
        this.clickOnView(todosComponents.editTodoDeleteButton)
    }.let{ ResultRobot() }

    fun markTodoAsCompleted(title: String): ResultRobot = apply  {
        // Click on the todo with the title (specifically in the list)
        this.clickOnViewWithTextInList(title)
        
        // Click the completed checkbox
        this.clickOnView(todosComponents.editTodoCompletedCheckbox)
        
        // Click update button to save changes
        this.clickOnView(todosComponents.editTodoUpdateButton)
    }.let{ ResultRobot() }



    private fun clickOnViewWithText(text: String): ResultRobot = apply  {
        this.clickOnView(text)
    }.let{ ResultRobot() }

    private fun clickOnViewWithTextInList(text: String): ResultRobot = apply  {
        // Click on text that is specifically within the RecyclerView/list
        this.clickOnViewWithTextInList(todosComponents.todosList, text)
    }.let{ ResultRobot() }

    private fun clearTextFromView(viewId: Int): ResultRobot = apply  {
        this.clearText(viewId)
    }.let{ ResultRobot() }
}