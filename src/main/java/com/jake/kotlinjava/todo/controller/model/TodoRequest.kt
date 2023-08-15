package com.jake.kotlinjava.todo.controller.model

data class TodoRequest(
    val title: String,
    val description: String,
    val done: Boolean = false,
) {
}