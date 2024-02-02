package com.example.newtodo.todo.entity

import jakarta.persistence.*

@Entity
@Table(name = "todocard")
class TodoCard(
    @Column(name = "title")
    var title: String
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null
}