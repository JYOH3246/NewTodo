package com.example.newtodo.todo.repository.todo

import com.example.newtodo.todo.entity.Todo

interface CustomTodoRepository {
    fun searchTodoListByTitle(title: String): List<Todo>
}