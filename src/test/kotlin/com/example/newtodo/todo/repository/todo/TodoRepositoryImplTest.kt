package com.example.newtodo.todo.repository.todo

import com.example.newtodo.common.querydsl.QueryDslSupport
import com.example.newtodo.todo.entity.Todo
import com.example.newtodo.todo.entity.TodoCard
import com.example.newtodo.todo.entity.TodoStatus
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.context.annotation.Import
import org.springframework.data.domain.Pageable
import org.springframework.test.context.ActiveProfiles

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Import(value = [QueryDslSupport::class])
@ActiveProfiles("test")
class TodoRepositoryImplTest @Autowired constructor(
    private val todoRepository: TodoRepository
) {
    @Test
    fun `타이틀이 n번이면 컨텐츠는 n번할일, 1건만 조회되어야 함`() {
        //Given : 특정 메소드 호출을 위한 준비물
        todoRepository.saveAllAndFlush(DEFAULT_TODO_LIST)
        //When : 내가 테스트하려는 메소드
        val result1 = todoRepository.searchTodoListByTitleWithPageable(
            Pageable.ofSize(5),
            todoStatus = TodoStatus.READY,
            title = "8번"
        )
        val result2 = todoRepository.searchTodoListByTitleWithPageable(
            Pageable.ofSize(5),
            todoStatus = TodoStatus.READY,
            title = "9번"
        )
        val result3 = todoRepository.searchTodoListByTitleWithPageable(
            Pageable.ofSize(5),
            todoStatus = TodoStatus.READY,
            title = "10번"
        )
        //Then : 검증하기
        result1.content.size shouldBe 1
        result2.content.size shouldBe 1
        result3.content.size shouldBe 1

    }

    @Test
    fun `조회된 결과가 0건일 때 결과`() {
        todoRepository.saveAllAndFlush(DEFAULT_TODO_LIST)
        val result1 = todoRepository.searchTodoListByTitleWithPageable(
            Pageable.ofSize(5),
            todoStatus = TodoStatus.READY,
            title = "ql"
        )
        result1.content.size shouldBe 0


    }

    @Test
    fun `조회된 결과가 7건이고, pagesize가 3일 때`() {
        todoRepository.saveAllAndFlush(DEFAULT_TODO_LIST)
        val result1 = todoRepository.searchTodoListByTitleWithPageable(
            Pageable.ofSize(3),
            todoStatus = TodoStatus.READY,
            title = "q"
        )
        result1.content.size shouldBe 3
        result1.isLast shouldBe false
        result1.totalPages shouldBe 3
        result1.number shouldBe 0
        result1.totalElements shouldBe 7


    }

    companion object {
        private val DEFAULT_TODO_LIST = listOf(
            Todo(title = "q", content = "1번할일", status = TodoStatus.READY, todoCard = TodoCard("재영1")),
            Todo(title = "q", content = "2번할일", status = TodoStatus.READY, todoCard = TodoCard("재영1")),
            Todo(title = "q", content = "3번할일", status = TodoStatus.READY, todoCard = TodoCard("재영1")),
            Todo(title = "q", content = "4번할일", status = TodoStatus.READY, todoCard = TodoCard("재영1")),
            Todo(title = "q", content = "5번할일", status = TodoStatus.READY, todoCard = TodoCard("재영2")),
            Todo(title = "q", content = "6번할일", status = TodoStatus.READY, todoCard = TodoCard("재영1")),
            Todo(title = "q", content = "7번할일", status = TodoStatus.READY, todoCard = TodoCard("재영2")),
            Todo(title = "8번", content = "8번할일", status = TodoStatus.READY, todoCard = TodoCard("재영1")),
            Todo(title = "9번", content = "9번할일", status = TodoStatus.READY, todoCard = TodoCard("재영2")),
            Todo(title = "10번", content = "10번할일", status = TodoStatus.READY, todoCard = TodoCard("재영1")),

            )
    }
}
