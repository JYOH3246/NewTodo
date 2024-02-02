package com.example.newtodo.todo.entity

import com.example.newtodo.common.audit.BaseTimeEntity
import jakarta.persistence.*

@Entity
@Table(name = "comments")
class Comment(
    @Column(name = "content")
    var content: String,
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "todoId")
    val todo: Todo

): BaseTimeEntity()  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

}