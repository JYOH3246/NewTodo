package com.example.newtodo.todo.dto

import com.example.newtodo.todo.entity.Comment

data class CommentResponse (
    val content : String
) {
    companion object {
        fun from (comment: Comment) : CommentResponse {
            return CommentResponse(
                content = comment.content
            )
        }
    }
}


