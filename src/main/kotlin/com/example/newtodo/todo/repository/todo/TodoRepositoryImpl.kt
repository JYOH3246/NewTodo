package com.example.newtodo.todo.repository.todo

import com.example.newtodo.common.querydsl.QueryDslSupport
import com.example.newtodo.todo.entity.QTodo
import com.example.newtodo.todo.entity.Todo
import org.springframework.stereotype.Repository

@Repository
class TodoRepositoryImpl : QueryDslSupport(), CustomTodoRepository {
    private val todo = QTodo.todo

    //1. 특정 제목을 가진 할일 검색하기
    /*
    select * from todo where title like "%검색어%"
     */
    override fun searchTodoListByTitle(title: String): List<Todo> {
        return queryFactory.selectFrom(todo)
            .where(todo.title.containsIgnoreCase(title))
            .fetch()

    }
}