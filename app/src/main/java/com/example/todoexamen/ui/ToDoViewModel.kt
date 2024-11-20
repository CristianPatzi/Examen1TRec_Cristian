package com.example.todoexamen.ui

import androidx.lifecycle.ViewModel
import com.example.todoexamen.models.Tarea
import com.example.todoexamen.models.TodoUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class ToDoViewModel: ViewModel() {
    private var _uiState = MutableStateFlow(TodoUiState())
    val uiState: StateFlow<TodoUiState> = _uiState.asStateFlow()

    fun addTask(tarea: Tarea){
        _uiState.update { currentState ->
            val currentList = currentState.tareas;
            currentList.add(tarea)
            currentState.copy(
                tareas = currentList,
                pendding = currentList.size,

            )

        }
    }

    fun clearAllTask(){
        _uiState.update { currentState ->
            val currentList = currentState.tareas;
            currentList.clear()
            currentState.copy(
                tareas = currentList,
                pendding = currentList.size,
                )

        }
    }
}