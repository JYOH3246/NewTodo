package com.example.newtodo.todo.repository

import com.example.newtodo.todo.entity.Comment
import org.springframework.data.jpa.repository.JpaRepository

interface CommentRepository : JpaRepository<Comment, Long> {
    fun findAllByTodoId(todoId: Long): List<Comment>?
}