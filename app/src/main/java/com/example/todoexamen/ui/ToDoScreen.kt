package com.example.todoexamen.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.todoexamen.R
import com.example.todoexamen.models.Tarea
import com.example.todoexamen.ui.theme.ToDoExamenTheme

@Composable
fun TodoScreen(modifier: Modifier = Modifier, todoViewModel: ToDoViewModel = viewModel()) {
    val todoUiState by todoViewModel.uiState.collectAsState()

    var title by rememberSaveable { mutableStateOf("") }
    var date by rememberSaveable { mutableStateOf("") }
    var time by rememberSaveable { mutableStateOf("") }

    Scaffold(modifier = Modifier
        .fillMaxSize(),
        topBar = { TodoAppBar() }

    ) { innerPadding ->
        Column(
            modifier = modifier
                .padding(innerPadding)
                .padding(dimensionResource(R.dimen.medium_padding))
        ) {
            FormInputs(
                title = title,
                date = date,
                time = time,
                onDateChange = { date = it },
                onTitleChange = { title = it },
                onTimeChange = {time = it}
            )

            FormActions(
                addTask = {
                    todoViewModel.addTask(Tarea(title, date, time))
                    title = "";
                    date = "";
                    time = "";
                },
                clearAllTask = { todoViewModel.clearAllTask() },
                undo = {}
            )
            Box(modifier = Modifier.height(dimensionResource(R.dimen.space_box)))

            Text(text = stringResource(R.string.pendding_tasks, todoUiState.pendding))
            Box(modifier = Modifier.height(dimensionResource(R.dimen.space_box)))


            LazyColumn {
                items(todoUiState.tareas) { tarea ->
                    TodoItem(tarea)
                }
            }
        }
    }
}


@Composable
fun FormActions(
    addTask: () -> Unit,
    clearAllTask: () -> Unit,
    undo: () -> Unit,
    ) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                top = dimensionResource(id = R.dimen.small_padding),
                bottom = dimensionResource(id = R.dimen.small_padding),
            ),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        Button(onClick = addTask) {
            Text(text = stringResource(R.string.add_task))

        }
        Button(onClick = undo) {
            Text(text = stringResource(R.string.undo))

        }

        Button(onClick = clearAllTask) {
            Text(text = stringResource(R.string.clear_all_tasks))
        }
    }
}

@Composable
fun FormInputs(
    title: String,
    date: String,
    time: String,
    onTitleChange: (String) -> Unit,
    onDateChange: (String) -> Unit,
    onTimeChange: (String) -> Unit
) {

    Column(
    ) {
        OutlinedTextField(
            value = title,
            onValueChange = onTitleChange,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Next,
            ),
            label = {
                Text(stringResource(R.string.title_of_task))
            },
            modifier = Modifier.fillMaxWidth(),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = colorScheme.surface,
                unfocusedContainerColor = colorScheme.surface,
                disabledContainerColor = colorScheme.surface,
            ),

            )
        OutlinedTextField(
            value = date,
            onValueChange = onDateChange,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text
            ),
            label = {
                Text(stringResource(R.string.expire_date))
            },
            modifier = Modifier.fillMaxWidth(),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = colorScheme.surface,
                unfocusedContainerColor = colorScheme.surface,
                disabledContainerColor = colorScheme.surface,
            ),

            )
        OutlinedTextField(
            value = time,
            onValueChange = onDateChange,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text
            ),
            label = {
                Text(stringResource(R.string.expire_time))
            },
            modifier = Modifier.fillMaxWidth(),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = colorScheme.surface,
                unfocusedContainerColor = colorScheme.surface,
                disabledContainerColor = colorScheme.surface,
            ),

            )
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TodoAppBar(modifier: Modifier = Modifier) {
    TopAppBar(
        title = {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center

            ) {
                Text(
                    text = stringResource(R.string.list_works),
                    style = MaterialTheme.typography.displayMedium,
                )
            }
        },

        colors = TopAppBarDefaults.mediumTopAppBarColors(
            containerColor = colorScheme.primaryContainer
        ),
        modifier = modifier
    )

}

@Preview(showSystemUi = true)
@Composable
fun TodoScreenPreview() {

    ToDoExamenTheme {
        TodoScreen()
    }
}
