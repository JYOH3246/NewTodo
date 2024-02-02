package com.example.newtodo.todo.controller

import com.example.newtodo.todo.dto.TodoCardRequest
import com.example.newtodo.todo.dto.TodoCardResponse
import com.example.newtodo.todo.service.TodoCardService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.http.ResponseEntity.status
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/todoCards")
class TodoCardController
    (
    private val todoCardService: TodoCardService
) {
    //1. 전체조회
    @GetMapping
    fun getTodoCardList(): ResponseEntity<List<TodoCardResponse>> {
        return status(HttpStatus.OK).body(todoCardService.getTodoCardList())
    }

    //2. 단건조회
    @GetMapping("/{todoCardId}")
    fun getTodoCard(@PathVariable todoCardId: Long): ResponseEntity<TodoCardResponse> {
        return status(HttpStatus.OK).body(todoCardService.getTodoCard(todoCardId))
    }

    //3. 할일카드 만들기
    @PostMapping
    fun createTodoCard(@RequestBody request: TodoCardRequest): ResponseEntity<TodoCardResponse> {
        return status(HttpStatus.OK).body(todoCardService.createTodoCard(request))
    }

    @PutMapping("/{todoCardId}")
    //4. 할일카드 수정하기
    fun modifyTodoCard(
        @PathVariable todoCardId: Long,
        @RequestBody request: TodoCardRequest
    ): ResponseEntity<TodoCardResponse> {
        return status(HttpStatus.CREATED).body(todoCardService.modifyTodoCard(todoCardId, request))
    }

    //5. 할일카드 삭제하기
    @DeleteMapping("/{todoCardId}")
    fun deleteTodoCard(
        @PathVariable todoCardId: Long
    ): ResponseEntity<Unit> {
        todoCardService.deleteTodoCard(todoCardId)
        return status(HttpStatus.NO_CONTENT).build()
    }
}