package com.example.newtodo.todo.repository.todo

import com.example.newtodo.todo.entity.Todo
import com.example.newtodo.todo.entity.TodoStatus
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

interface CustomTodoRepository {
    fun searchTodoListByTitleWithPageable(pageable: Pageable, title: String, todoStatus: TodoStatus): Page<Todo>
}