package com.example.newtodo.todo.dto

import com.example.newtodo.todo.entity.Todo

data class TodoResponse (
    val id : Long?,
    val title : String,
    val content : String,
    val status :String,
    val comment: List<CommentResponse>
)
{
    companion object {
        fun from(todo: Todo) : TodoResponse {
            return TodoResponse(
                id = todo.id,
                title = todo.title,
                content = todo.content,
                status = todo.status.name,
                comment = todo.comments.map { CommentResponse.from(it) }
            )
        }
    }
}
