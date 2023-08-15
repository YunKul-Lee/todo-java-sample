package com.jake.kotlinjava.todo.controller.model

import com.fasterxml.jackson.annotation.JsonIgnore
import com.jake.kotlinjava.todo.domain.Todo

data class TodoListResponse(
    val items: List<TodoResponse>,
) {

    val size: Int
        @JsonIgnore
        get() = items.size

    fun get(index: Int): TodoResponse = items[index]

    // 코틀린은 static 이 존재하지 않기 때문에 companion object 로 대체
    companion object {
        fun of(todoList: List<Todo>): TodoListResponse =
            TodoListResponse(todoList.map(TodoResponse::of))
    }
}