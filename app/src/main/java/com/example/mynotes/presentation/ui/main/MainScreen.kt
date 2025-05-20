package com.example.mynotes.presentation.ui.main

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.mynotes.domain.model.Note
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(viewModel: MainViewModel) {
    val uiState by viewModel.uiState.collectAsState()
    val sheetState = rememberModalBottomSheetState(initialValue = ModalBottomSheetValue.Hidden)

    var editNote by remember {
        mutableStateOf(Note(-1, "", ""))
    }

    var isEdit by remember {
        mutableStateOf(false)
    }

    val scope = rememberCoroutineScope()

    ModalBottomSheetLayout(
        modifier = Modifier.safeContentPadding(),
        sheetContent = {
            Form(note = editNote) { title, description ->
                if (isEdit) {
                    val note = Note(
                        id = editNote.id,
                        title = title,
                        description = description
                    )

                    viewModel.updateNote(note)
                } else {
                    val note = Note(0, title = title, description = description)

                    viewModel.insertNote(note)
                }

                scope.launch {
                    sheetState.hide()
                }
            }
        },
        sheetState = sheetState
    ) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = {
                        Text("Notes App")
                    },
                    actions = {
                        IconButton(
                            onClick = {
                                scope.launch { sheetState.show() }
                            }
                        ) {
                            Icon(
                                imageVector = Icons.Default.Add,
                                contentDescription = null
                            )
                        }
                    }
                )
            }
        ) { paddingValues ->
            if (uiState.data.isEmpty()) {
                Box(
                    modifier = Modifier
                        .padding(paddingValues)
                        .fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text("Nothing to display")
                }
            } else {
                Notes(
                    modifier = Modifier.padding(paddingValues),
                    notes = uiState.data,
                    onDeleteClick = {
                        viewModel.deleteNote(it)
                        isEdit = false
                    }
                ) { note ->
                    isEdit = true
                    editNote = note
                    scope.launch { sheetState.show() }
                }

            }
        }

    }
}

@Composable
private fun Notes(
    modifier: Modifier,
    notes: List<Note>,
    onDeleteClick: (Note) -> Unit,
    onCardClick: (Note) -> Unit
) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        items(notes) { note ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                onClick = {
                    onCardClick.invoke(note)
                }
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 4.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Column(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxWidth(),
                        verticalArrangement = Arrangement.spacedBy(6.dp)
                    ) {
                        Text(
                            text = note.title,
                            style = MaterialTheme.typography.bodyLarge,
                            fontWeight = FontWeight.Bold
                        )

                        Text(
                            text = note.description,
                            style = MaterialTheme.typography.bodyMedium
                        )

                    }

                    IconButton(
                        onClick = {
                            onDeleteClick.invoke(note)
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Default.Delete,
                            contentDescription = null,
                            tint = Color.Red
                        )
                    }

                }
            }
        }
    }
}

@Composable
private fun Form(
    note: Note,
    onSaveClick: (String, String) -> Unit
) {
    val title = remember {
        mutableStateOf("")
    }

    val description = remember {
        mutableStateOf("")
    }

    LaunchedEffect(note.id != -1) {
        title.value = note.title
        description.value = note.description
    }

    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = title.value,
            onValueChange = {
                title.value = it
            },
            singleLine = true,
            placeholder = { Text("Title") }
        )

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = description.value,
            onValueChange = {
                description.value = it
            },
            singleLine = true,
            placeholder = { Text("Description") }
        )

        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = {
                onSaveClick.invoke(
                    title.value,
                    description.value
                )
            }
        ) {
            Text("Save")
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun Preview() {
    Form(Note(id = 1, title = "title", description = "description"), onSaveClick = { _, _ -> })
//    val notesList = mutableListOf<Note>()
//    val sampleValue = Triple(0, "Food", "Shopping")
//
//    (0..10).forEach {
//        notesList.add(
//            Note(
//                id = it,
//                title = "${sampleValue.second} ${it}",
//                description = "${sampleValue.third} ${it}"
//            )
//        )
//    }
//
//    Scaffold() {
//        Notes(modifier = Modifier.padding(paddingValues = it), notesList, {})
//    }
}
