package com.example.newtodo.todo.service

import com.example.newtodo.common.exception.BaseException
import com.example.newtodo.todo.repository.TodoCardRepository
import com.example.newtodo.todo.repository.todo.TodoRepository
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.BehaviorSpec
import io.mockk.clearAllMocks
import io.mockk.every
import io.mockk.junit5.MockKExtension
import io.mockk.mockk
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.data.repository.findByIdOrNull


@SpringBootTest
@ExtendWith(MockKExtension::class)
class TodoServiceTest : BehaviorSpec(
    {


        afterContainer {
            clearAllMocks()
        }


        val todoCardRepository = mockk<TodoCardRepository>(relaxed = true)
        val todoRepository = mockk<TodoRepository>(relaxed = true)

        val todoService = TodoService(todoCardRepository, todoRepository)

        Given("특정 할일카드가 존재할 때") {
            val todoCardId = 1L
            every { todoCardRepository.findByIdOrNull(any()) } returns mockk()
            When("해당 할일카드에 속하는 특정한 할일이 존재하지 않는다면") {
                val todoId = 2L
                every { todoRepository.findByIdOrNull(any()) } returns null
                Then("BaseException 중 TODO_INVALID를 발생시킨다.") {
                    shouldThrow<BaseException> {
                        todoService.getTodo(todoCardId, todoId)
                    }
                }

            }

        }

    })


