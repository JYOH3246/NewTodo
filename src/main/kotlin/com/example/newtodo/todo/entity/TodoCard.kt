package com.example.newtodo.todo.entity

import com.example.newtodo.common.audit.BaseTimeEntity
import jakarta.persistence.*

@Entity
@Table(name = "todocard")
class TodoCard(
    @Column(name = "title")
    var title: String
) : BaseTimeEntity() {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null
}