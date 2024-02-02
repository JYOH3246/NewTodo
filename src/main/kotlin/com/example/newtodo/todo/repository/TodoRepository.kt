package com.example.newtodo.todo.repository

import com.example.newtodo.todo.entity.Todo
import org.springframework.data.jpa.repository.JpaRepository

interface TodoRepository : JpaRepository<Todo, Long> {
    fun findAllByTodoCardId(id:Long) : List<Todo>?
}