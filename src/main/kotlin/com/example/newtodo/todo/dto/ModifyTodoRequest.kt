package com.example.newtodo.todo.dto

import com.example.newtodo.todo.entity.Todo

data class ModifyTodoRequest(
    val title: String,
    val content : String,
    val status : String
)

