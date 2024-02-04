package com.example.newtodo.todo.repository.todo

import com.example.newtodo.todo.entity.Todo
import org.springframework.data.jpa.repository.JpaRepository

interface TodoRepository : JpaRepository<Todo, Long>, CustomTodoRepository {
    fun findAllByTodoCardId(id: Long): List<Todo>?

}