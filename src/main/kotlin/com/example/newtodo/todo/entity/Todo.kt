package com.example.newtodo.todo.entity

import jakarta.persistence.*

@Entity
@Table(name = "todo")
class Todo(
    @Column(name = "title")
    var title: String,
    @Column(name = "content")
    var content: String,
    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    var status: String,
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "todoCard_id")
    val todoCard: TodoCard
    // 요구사항 : 댓글이 들어가면, 양방향 참조가 생길 것 같다!
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null
}