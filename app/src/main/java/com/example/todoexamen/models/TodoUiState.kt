package com.example.todoexamen.models

data class TodoUiState(
    val tareas: MutableList<Tarea> = mutableListOf(),
    val pendding: Int = tareas.size
)