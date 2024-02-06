package com.example.newtodo.todo.service

import com.example.newtodo.common.exception.BaseException
import com.example.newtodo.common.exception.base.BaseResponseCode
import com.example.newtodo.todo.dto.CommentRequest
import com.example.newtodo.todo.dto.CommentResponse
import com.example.newtodo.todo.entity.Comment
import com.example.newtodo.todo.repository.CommentRepository
import com.example.newtodo.todo.repository.todo.TodoRepository
import jakarta.transaction.Transactional
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class CommentService(
    private val commentRepository: CommentRepository,
    private val todoRepository: TodoRepository
) {
    //1. 댓글 전체 조회
    fun getCommentList(todoCardId: Long, todoId: Long): List<CommentResponse> {
        val comment = commentRepository.findAllByTodoId(todoId) ?: throw BaseException(BaseResponseCode.INVALID_COMMENT)
        todoRepository.findByIdOrNull(todoCardId) ?: throw BaseException(BaseResponseCode.INVALID_TODO_CARD)
        return comment.map { CommentResponse.from(it) }
    }

    //2. 댓글 추가
    @Transactional
    fun createComment(
        todoCardId: Long,
        todoId: Long,
        request: CommentRequest
    ): CommentResponse {
        val todo = todoRepository.findByIdOrNull(todoCardId) ?: throw BaseException(BaseResponseCode.INVALID_TODO)
        val comment = Comment(
            content = request.content,
            todo = todo
        )
        return commentRepository.save(comment).let { CommentResponse.from(it) }
    }

    //3. 댓글 수정
    @Transactional
    fun modifyComment(
        todoCardId: Long,
        todoId: Long,
        commentId: Long,
        request: CommentRequest
    ): CommentResponse {
        val comment =
            commentRepository.findByIdOrNull(commentId) ?: throw BaseException(BaseResponseCode.INVALID_COMMENT)
        comment.content = request.content
        return comment.let { CommentResponse.from(it) }
    }

    //4. 댓글 삭제
    @Transactional
    fun deleteComment(
        todoCardId: Long,
        todoId: Long,
        commentId: Long
    ) {
        val comment =
            commentRepository.findByIdOrNull(commentId) ?: throw BaseException(BaseResponseCode.INVALID_COMMENT)
        commentRepository.delete(comment)
    }
}