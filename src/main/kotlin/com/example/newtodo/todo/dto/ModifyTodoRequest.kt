package com.example.newtodo.todo.dto

data class ModifyTodoRequest(
    val title: String,
    val content: String,
    val status: String
)

