package com.jake.kotlinjava.todo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@ExtendWith(SpringExtension.class)
public class TodoServiceTests {

	@MockBean
	private TodoRepository todoRepo;

	private TodoService todoService;

	private Todo stub;

	@BeforeEach
	public void setUp() {
		todoService = new TodoService(todoRepo);
		stub = todoStub();
	}

	@Test
	public void 한개의_TODO_반환해야함() {
		// Given
		given(todoRepo.findById(1L)).willReturn(Optional.of(stub));

		// When
		Todo actual = todoService.findById(1L);

		// Then
		assertThat(actual).isNotNull();
		assertThat(actual).isEqualTo(stub);
	}

	public Todo todoStub() {
		return Todo.builder()
				.id(1L)
				.title("테스트")
				.description("테스트 상세")
				.done(false)
				.createdAt(LocalDateTime.now())
				.updatedAt(LocalDateTime.now())
				.build();
	}
}
