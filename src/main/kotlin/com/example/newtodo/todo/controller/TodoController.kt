package com.example.newtodo.todo.controller

import com.example.newtodo.todo.dto.ModifyTodoRequest
import com.example.newtodo.todo.dto.TodoRequest
import com.example.newtodo.todo.dto.TodoResponse
import com.example.newtodo.todo.entity.TodoStatus
import com.example.newtodo.todo.service.TodoService
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.web.PageableDefault
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.http.ResponseEntity.status
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/todoCards/{todoCardId}/todos")
class TodoController(
    private val todoService: TodoService
) {
    //1. 할일 전체 조회
    /*
    @GetMapping
    fun getTodoList(@PathVariable todoCardId: Long): ResponseEntity<List<TodoResponse>> {
        return status(HttpStatus.OK).body(todoService.getTodoList(todoCardId))
    }

     */

    //2. 할일 하나 조회 & 하나 조회 시 댓글 출력
    @GetMapping("/{todoId}")
    fun getTodo(
        @PathVariable todoCardId: Long,
        @PathVariable todoId: Long
    ): ResponseEntity<TodoResponse> {
        return status(HttpStatus.OK).body(todoService.getTodo(todoCardId, todoId))
    }

    //3. 할일 추가하기
    @PostMapping
    fun createTodo(
        @PathVariable todoCardId: Long,
        @RequestBody request: TodoRequest
    ): ResponseEntity<TodoResponse> {
        return status(HttpStatus.CREATED).body(todoService.createTodo(todoCardId, request))
    }

    //4. 할일 수정하기
    @PatchMapping("/{todoId}")
    fun modifyTodo(
        @PathVariable todoCardId: Long,
        @PathVariable todoId: Long,
        @RequestBody request: ModifyTodoRequest
    ): ResponseEntity<TodoResponse> {
        return status(HttpStatus.OK).body(todoService.modifyTodo(todoCardId, todoId, request))
    }

    //5. 할일 삭제하기
    @DeleteMapping("/{todoId}")
    fun deleteTodo(
        @PathVariable todoCardId: Long,
        @PathVariable todoId: Long
    ): ResponseEntity<Unit> {
        todoService.deleteTodo(todoCardId, todoId)
        return status(HttpStatus.NO_CONTENT).build()
    }

    //6. 할일 검색하기
    @GetMapping
    fun searchTodo(
        @PageableDefault(size = 3, sort = ["id"]) pageable: Pageable,
        @PathVariable todoCardId: Long,
        @RequestParam todoStatus: TodoStatus,
        @RequestParam(name = "title") title: String
    ): ResponseEntity<Page<TodoResponse>> {
        return status(HttpStatus.OK).body(todoService.searchTodo(todoCardId, title, todoStatus, pageable))

    }

}