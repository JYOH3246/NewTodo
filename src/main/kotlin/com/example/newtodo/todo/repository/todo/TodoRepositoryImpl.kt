package com.example.newtodo.todo.repository.todo

import com.example.newtodo.common.querydsl.QueryDslSupport
import com.example.newtodo.todo.entity.QTodo
import com.example.newtodo.todo.entity.Todo
import com.example.newtodo.todo.entity.TodoStatus
import com.querydsl.core.BooleanBuilder
import jakarta.transaction.Transactional
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Repository

@Repository
class TodoRepositoryImpl : QueryDslSupport(), CustomTodoRepository {
    private val todo = QTodo.todo

    //1. 특정 제목을 가진 할일 검색하기 & 페이지네이션
    /*
    select * from todos where title like "%검색어%"
     */
    @Transactional
    override fun searchTodoListByTitleWithPageable(
        pageable: Pageable,
        title: String,
        todoStatus: TodoStatus
    ): Page<Todo> {
        val whereClause = BooleanBuilder()
        title.let { whereClause.and(todo.title.containsIgnoreCase(title)) }
        todoStatus.let { whereClause.and(todo.status.eq(todoStatus)) }
        val totalCount = queryFactory.select(todo.count()).from(todo).where(whereClause).fetchOne() ?: 0L
        val query = queryFactory.selectFrom(todo)
            .where(whereClause)
            .offset(pageable.offset)
            .limit(pageable.pageSize.toLong())
        val contents = query.fetch()
        return PageImpl(contents, pageable, totalCount)

    }
}