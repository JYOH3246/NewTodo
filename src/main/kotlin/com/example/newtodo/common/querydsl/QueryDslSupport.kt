package com.example.newtodo.common.querydsl

import com.querydsl.jpa.impl.JPAQueryFactory
import jakarta.persistence.EntityManager
import jakarta.persistence.PersistenceContext


open class QueryDslSupport {
    @PersistenceContext //entity manager가 필요하면
    protected lateinit var em: EntityManager

    protected val queryFactory: JPAQueryFactory
        get() {
            return JPAQueryFactory(em)
        }
}