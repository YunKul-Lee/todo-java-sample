package com.jake.kotlinjava.todo.controller

import com.jake.kotlinjava.todo.controller.model.TodoListResponse
import com.jake.kotlinjava.todo.controller.model.TodoRequest
import com.jake.kotlinjava.todo.controller.model.TodoResponse
import com.jake.kotlinjava.todo.service.TodoService
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.http.ResponseEntity.*
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(value = ["/api/todos"], produces = [MediaType.APPLICATION_JSON_VALUE])
class TodoController(
    private val todoService: TodoService
) {

    @GetMapping
    fun getAll() =
        ok(TodoListResponse.of(todoService.findAll()))

    @GetMapping("/{id}")
    fun get(@PathVariable id: Long) =
        ok(TodoResponse.of(todoService.findById(id)))

    @PostMapping
    fun create(@RequestBody request: TodoRequest) =
        ok(TodoResponse.of(todoService.create(request)))

    @PutMapping("/{id}")
    fun update(@PathVariable id: Long, @RequestBody request: TodoRequest) =
        ok(TodoResponse.of(todoService.update(id, request)))

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Long): ResponseEntity<Unit> {
        todoService.delete(id)
        return noContent().build()
    }

}