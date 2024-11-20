package com.example.todoexamen.ui

import android.graphics.Color
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.todoexamen.R
import com.example.todoexamen.models.Tarea

@Composable
fun TodoItem(tarea: Tarea) {
    Column {
        Text(
            text = tarea.name,
            fontWeight = FontWeight.Bold,
        )
        Text(
            text = "Fecha: ${tarea.date}",
            style = MaterialTheme.typography.bodyMedium,
        )
        Box(modifier = Modifier.height(dimensionResource(R.dimen.space_small_box)))


    }
}

@Preview(backgroundColor = Color.WHITE.toLong())
@Composable
fun ItemPreview() {
    TodoItem(tarea = Tarea("Cosas", "1/10/2024"))
}


