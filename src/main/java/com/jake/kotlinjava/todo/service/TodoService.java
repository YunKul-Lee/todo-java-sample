package com.jake.kotlinjava.todo.service;

import com.jake.kotlinjava.todo.controller.model.TodoRequest;
import com.jake.kotlinjava.todo.domain.Todo;
import com.jake.kotlinjava.todo.domain.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TodoService {

	private final TodoRepository todoRepo;

	public TodoService(TodoRepository todoRepo) {
		this.todoRepo = todoRepo;
	}

	@Transactional(readOnly = true)
	public List<Todo> findAll() {
		return todoRepo.findAll(Sort.by(Sort.Direction.DESC, "id"));
	}

	@Transactional(readOnly = true)
	public Todo findById(Long id) {
		return todoRepo.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
	}

	@Transactional
	public Todo create(TodoRequest request) {
		Assert.notNull(request, "TodoRequest is null");

		Todo todo = Todo.builder()
				.title(request.getTitle())
				.description(request.getDescription())
				.done(false)
				.createdAt(LocalDateTime.now())
				.build();

		return todoRepo.save(todo);
	}

	@Transactional
	public Todo update(Long id, TodoRequest request) {
		Assert.notNull(request, "TodoRequest is null");

		Todo todo = findById(id);
		todo.update(request.getTitle(),
					request.getDescription(),
					request.getDone());

		return todoRepo.save(todo);
	}

	@Transactional
	public void delete(Long id) {
		todoRepo.deleteById(id);
	}
}
