package com.example.newtodo.todo.repository.todo

import com.example.newtodo.common.querydsl.QueryDslSupport
import com.example.newtodo.todo.entity.QTodo
import com.example.newtodo.todo.entity.Todo
import com.querydsl.core.BooleanBuilder
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Repository

@Repository
class TodoRepositoryImpl : QueryDslSupport(), CustomTodoRepository {
    private val todo = QTodo.todo

    //1. 특정 제목을 가진 할일 검색하기
    /*
    select * from todo where title like "%검색어%"
     */
    override fun searchTodoListByTitleWithPageable(pageable: Pageable, title: String): Page<Todo> {
        val whereClause = BooleanBuilder()
        title.let { whereClause.and(todo.title.containsIgnoreCase(title)) }
        val totalCount = queryFactory.select(todo.count()).from(todo).where(whereClause).fetchOne() ?: 0L
        val query = queryFactory.selectFrom(todo)
            .where(whereClause)
            .offset(pageable.offset)
            .limit(pageable.pageSize.toLong())
        val contents = query.fetch()
        return PageImpl(contents, pageable, totalCount)

    }
}