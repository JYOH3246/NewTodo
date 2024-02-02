package com.example.newtodo.todo.dto

import com.example.newtodo.todo.entity.TodoCard

data class TodoCardResponse(
    val id: Long?,
    val title: String

) {
    companion object {
        fun from(todoCard: TodoCard): TodoCardResponse {
            return TodoCardResponse(
                id = todoCard.id,
                title = todoCard.title
            )

        }
    }
}
