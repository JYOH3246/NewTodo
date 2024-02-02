package com.example.newtodo.todo.repository

import com.example.newtodo.todo.entity.TodoCard
import org.springframework.data.jpa.repository.JpaRepository

interface TodoCardRepository : JpaRepository<TodoCard, Long>