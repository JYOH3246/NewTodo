package com.example.newtodo.todo.entity

import jakarta.persistence.*

@Entity
@Table(name = "comments")
class Comment(
    @Column(name = "content")
    var content: String,
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "todoId")
    val todo: Todo

)  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

}