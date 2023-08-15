package com.jake.kotlinjava.todo

import com.jake.kotlinjava.todo.domain.Todo
import com.jake.kotlinjava.todo.domain.TodoRepository
import com.jake.kotlinjava.todo.service.TodoService
import com.ninjasquad.springmockk.MockkBean
import io.mockk.every
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.data.repository.findByIdOrNull
import org.springframework.test.context.junit.jupiter.SpringExtension
import java.time.LocalDateTime

@ExtendWith(SpringExtension::class)
class TodoServiceTests {

    @MockkBean
    lateinit var todoRepo: TodoRepository

    lateinit var todoService: TodoService

    // lazy loading
    val stub: Todo by lazy {
        Todo(
            id = 1,
            title = "테스트",
            description = "테스트 상세",
            done = false,
            createdAt = LocalDateTime.now(),
            updatedAt = LocalDateTime.now(),
        )
    }

    @BeforeEach
    fun setUp() {
        todoService = TodoService(todoRepo)
    }

    @Test
    fun `한개의 TODO 반환해야함`() {
        // Given
        every { todoRepo.findByIdOrNull(1) } returns stub

        // When
        val actual = todoService.findById(1L)

        // Then
        assertThat(actual).isNotNull
        assertThat(actual).isEqualTo(stub)

    }
}