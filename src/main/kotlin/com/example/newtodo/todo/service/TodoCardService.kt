package com.example.newtodo.todo.service

import com.example.newtodo.todo.dto.TodoCardRequest
import com.example.newtodo.todo.dto.TodoCardResponse
import com.example.newtodo.todo.entity.TodoCard
import com.example.newtodo.todo.repository.TodoCardRepository
import jakarta.transaction.Transactional
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class TodoCardService(
    private val todoCardRepository: TodoCardRepository
) {
    //1. 전체조회
    fun getTodoCardList(): List<TodoCardResponse> {
        return todoCardRepository.findAll().map { TodoCardResponse.from(it) }
    }

    /*
  함수 할일카드전체조회(){
      할일카드 리스트를 리포지토리에서 가져오고
      할일카드 리스트를 리턴
  }
   */
    //2. 단건조회
    fun getTodoCard(todoCardId: Long): TodoCardResponse {
        val todoCard = todoCardRepository.findByIdOrNull(todoCardId) ?: throw IllegalArgumentException("존재하지 않는 id")
        return todoCard.let { TodoCardResponse.from(it) }
    }

    /*
    함수 할일카드단건조회() {
        등록된 할일카드 하나를 id 기준으로 찾고
        찾은 할일카드를 리턴
    }
     */
    //3. 할일카드 만들기
    @Transactional
    fun createTodoCard(request: TodoCardRequest): TodoCardResponse {
        val todoCard = TodoCard(
            title = request.title
        )
        todoCardRepository.save(todoCard)
        return todoCard.let { TodoCardResponse.from(it) }
    }

    /*
    함수 할일카드만들기 () {
        할일카드 정의하기
        할일카드를 저장하기
        저장한 할일카드를 리턴해주기
    }
     */
    //4. 할일카드 수정하기
    @Transactional
    fun modifyTodoCard(
        todoCardId: Long,
        request: TodoCardRequest
    ): TodoCardResponse {
        val todoCard = todoCardRepository.findByIdOrNull(todoCardId) ?: throw IllegalArgumentException("존재하지 않음")

        return todoCard.let { TodoCardResponse.from(it) }

    }

    /*
    함수 할일카드수정하기() {
        할일카드를 가져오고
        가져온 할일카드의 데이터를 바꿔준 후
        할일카드를 저장하기
        저장된 할일카드를 리턴해주기
    }

     */
    //5. 할일카드 삭제하기
    @Transactional
    fun deleteTodoCard(todoCardId: Long) {
        val todoCard = todoCardRepository.findByIdOrNull(todoCardId) ?: throw IllegalArgumentException("존재하지 않음")
        todoCardRepository.delete(todoCard)
    }
    /*
    할일카드를 가져오고
    가져온 할일카드 데이터를 삭제하고
    이를 저장
     */

}