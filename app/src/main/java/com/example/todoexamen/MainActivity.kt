package com.example.todoexamen

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.todoexamen.ui.TodoScreen
import com.example.todoexamen.ui.theme.ToDoExamenTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ToDoExamenTheme {
                TodoScreen()
            }
        }
    }
}


