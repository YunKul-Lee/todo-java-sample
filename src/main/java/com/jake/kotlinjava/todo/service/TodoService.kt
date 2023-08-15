package com.jake.kotlinjava.todo.service

import com.jake.kotlinjava.todo.controller.model.TodoRequest
import com.jake.kotlinjava.todo.domain.Todo
import com.jake.kotlinjava.todo.domain.TodoRepository
import org.springframework.data.domain.Sort
import org.springframework.data.repository.findByIdOrNull
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.server.ResponseStatusException
import java.time.LocalDateTime

@Service
class TodoService(
    private val todoRepo: TodoRepository
) {

    @Transactional(readOnly = true)
    fun findAll(): List<Todo> =
        todoRepo.findAll(Sort.by(Sort.Direction.DESC, "id"))

    @Transactional(readOnly = true)
    fun findById(id: Long): Todo =
        todoRepo.findByIdOrNull(id)
            ?: throw ResponseStatusException(HttpStatus.NOT_FOUND)

    @Transactional
    fun create(request: TodoRequest?): Todo {
        checkNotNull(request) {
            "TodoRequest is null"
        }

        val todo = Todo(
            title = request.title,
            description = request.description,
            done = request.done,
            createdAt = LocalDateTime.now()
        )

        return todoRepo.save(todo)
    }

    @Transactional
    fun update(id: Long, request: TodoRequest?): Todo {
        checkNotNull(request) {
            "TodoRequest is null"
        }

        return findById(id).let {
            it.update(request.title, request.description, request.done)
            todoRepo.save(it)
        }
    }

    @Transactional
    fun delete(id: Long) = todoRepo.deleteById(id)
}