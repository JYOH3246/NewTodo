package com.example.newtodo.todo.controller

import com.example.newtodo.common.security.jwt.JwtPlugin
import com.example.newtodo.todo.dto.CommentResponse
import com.example.newtodo.todo.dto.TodoResponse
import com.example.newtodo.todo.service.TodoService
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe
import io.mockk.clearAllMocks
import io.mockk.every
import io.mockk.junit5.MockKExtension
import io.mockk.mockk
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(MockKExtension::class)
class TodoControllerTest @Autowired constructor(
    private val mockMvc: MockMvc,
    private val jwtPlugin: JwtPlugin,
) : DescribeSpec({

    afterContainer {
        clearAllMocks()
    }
    val todoService = mockk<TodoService>()
    describe("GET /todoCards/{todoCardId}/todos/{todoId} 는") {
        context("존재하는 ID에 대한 요청을 보낼 때") {
            it("200 status code를 응답해야 한다.") {
                val todoCardId = 1L
                val todoId = 2L
                val comments: MutableList<CommentResponse> = mutableListOf()
                every { todoService.getTodo(any(), any()) } returns TodoResponse(
                    id = todoId,
                    title = "title1",
                    content = "content1",
                    status = "MEMBER",
                    comment = comments
                )

                val jwtToken = jwtPlugin.generateAccessToken(
                    subject = "1",
                    email = "test@test.com",
                    role = "MEMBER"
                )
                val result = mockMvc.perform(
                    MockMvcRequestBuilders.get("/todoCards/$todoCardId/todos/$todoId")
                        .header("Authorization", "Bearer $jwtToken")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                ).andReturn()
                result.response.status shouldBe 200
                val responseDto = jacksonObjectMapper().readValue(
                    result.response.contentAsString,
                    TodoResponse::class.java
                )
                responseDto.id shouldBe todoCardId
            }
        }
    }
})
