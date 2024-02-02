package com.example.newtodo.todo.service

import com.example.newtodo.common.exception.BaseException
import com.example.newtodo.common.exception.base.BaseResponseCode
import com.example.newtodo.todo.dto.ModifyTodoRequest
import com.example.newtodo.todo.dto.TodoRequest
import com.example.newtodo.todo.dto.TodoResponse
import com.example.newtodo.todo.entity.Todo
import com.example.newtodo.todo.entity.TodoStatus
import com.example.newtodo.todo.entity.modify
import com.example.newtodo.todo.repository.TodoCardRepository
import com.example.newtodo.todo.repository.TodoRepository
import jakarta.transaction.Transactional
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class TodoService(
    private val todoCardRepository: TodoCardRepository,
    private val todoRepository: TodoRepository
) {
    //1. 할일 전체 조회
    fun getTodoList(todoCardId: Long): List<TodoResponse> {
        val todo = todoRepository.findAllByTodoCardId(todoCardId) ?: throw BaseException(BaseResponseCode.INVALID_TODO)
        return todo.map { TodoResponse.from(it) }
    }

    /*
    할일전체조회함수(할일카드id) {
        할일카드를 정의
        할일카드에 있는 할일의 리스트를 정의하고
        리스트를 리턴
    }
     */
    //2. 할일 하나 조회 & 하나 조회 시 댓글 출력
    fun getTodo(todoCardId: Long, todoId: Long): TodoResponse {
        val todo = todoRepository.findByIdOrNull(todoId) ?: throw BaseException(BaseResponseCode.INVALID_TODO)
        return if (todo.todoCard.id==todoCardId) {
            todo.let { TodoResponse.from(it) }
        }
        else throw IllegalArgumentException("대상 없음")
    }

    /*
    할일조회함수(할일id) {
        할일id로 할일을 찾기
        할일을 리턴
    }
     */
    //3. 할일 추가하기
    @Transactional
    fun createTodo(
        todoCardId: Long,
        request: TodoRequest
    ): TodoResponse {
        val todoCard = todoCardRepository.findByIdOrNull(todoCardId) ?: throw BaseException(BaseResponseCode.INVALID_TODO_CARD)
        val todo = Todo(
            title = request.title,
            content = request.content,
            status = TodoStatus.READY,
            todoCard = todoCard
        )
        return todoRepository.save(todo).let { TodoResponse.from(it) }
    }

    //4. 할일 수정하기
    @Transactional
    fun modifyTodo(
        todoCardId: Long,
        todoId: Long,
        request: ModifyTodoRequest
    ) : TodoResponse {
        val todo = todoRepository.findByIdOrNull(todoId) ?: throw BaseException(BaseResponseCode.INVALID_TODO)
        todo.modify(request)
        return todo.let { TodoResponse.from(it) }
    }

    //5. 할일 삭제하기
    @Transactional
    fun deleteTodo(
        todoCardId: Long,
        todoId: Long
    ) {
        val todo = todoRepository.findByIdOrNull(todoId) ?: throw BaseException(BaseResponseCode.INVALID_TODO)
        return if (todoCardId==todo.todoCard.id){
            todoRepository.delete(todo)
        }
        else throw IllegalArgumentException("대상 없음")
    }
}