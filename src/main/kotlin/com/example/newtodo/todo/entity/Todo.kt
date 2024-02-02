package com.example.newtodo.todo.entity

import com.example.newtodo.common.audit.BaseTimeEntity
import com.example.newtodo.todo.dto.ModifyTodoRequest
import com.fasterxml.jackson.annotation.JsonIgnore
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
    var status: TodoStatus,
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "todoCardId")
    val todoCard: TodoCard,
    // 요구사항 : 댓글이 들어가면, 양방향 참조가 생길 것 같다!
    @OneToMany(mappedBy="todo",fetch = FetchType.LAZY, cascade = [CascadeType.ALL], orphanRemoval = true)
    @JsonIgnore
    var comments : MutableList<Comment> = mutableListOf()
) : BaseTimeEntity() {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null
}

fun Todo.modify(request: ModifyTodoRequest) {
    title = request.title
    content = request.content
    status = when (request.status) {
        "READY" -> TodoStatus.READY
        "DOING" -> TodoStatus.DOING
        "DONE" -> TodoStatus.DONE
        else -> throw IllegalArgumentException("대상 없음")
    }
}