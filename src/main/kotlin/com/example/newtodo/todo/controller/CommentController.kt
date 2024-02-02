package com.example.newtodo.todo.controller

import com.example.newtodo.todo.dto.CommentRequest
import com.example.newtodo.todo.dto.CommentResponse
import com.example.newtodo.todo.service.CommentService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.http.ResponseEntity.*
import org.springframework.web.bind.annotation.*

@RequestMapping("/todoCards/{todoCardId}/todos/{todoId}/comments")
@RestController
class CommentController (
    private val commentService: CommentService,
)
{
    //1. 댓글 전체 조회
    @GetMapping
    fun getCommentList(
        @PathVariable todoCardId: Long,
        @PathVariable todoId: Long
    ): ResponseEntity<List<CommentResponse>> {
        return status(HttpStatus.OK).body(commentService.getCommentList(todoCardId,todoId))
    }
    //2. 댓글 추가
    @PostMapping
    fun createComment(
        @PathVariable todoCardId: Long,
        @PathVariable todoId: Long,
        @RequestBody request: CommentRequest
    ): ResponseEntity<CommentResponse> {
        return status(HttpStatus.CREATED).body(commentService.createComment(todoCardId,todoId,request))
    }

    //3. 댓글 수정
    @PutMapping("/{commentId}")
    fun modifyComment(
        @PathVariable todoCardId: Long,
        @PathVariable todoId: Long,
        @PathVariable commentId: Long,
        @RequestBody request: CommentRequest
    ): ResponseEntity<CommentResponse> {
        return status(HttpStatus.OK).body(commentService.modifyComment(todoCardId,todoId,commentId,request))
    }
    //4. 댓글 삭제
    @DeleteMapping("/{commentId}")
    fun deleteComment(
        @PathVariable todoCardId: Long,
        @PathVariable todoId: Long,
        @PathVariable commentId: Long,
    ): ResponseEntity<Unit> {
        commentService.deleteComment(todoCardId,todoId,commentId)
        return status(HttpStatus.OK).build()
    }
}