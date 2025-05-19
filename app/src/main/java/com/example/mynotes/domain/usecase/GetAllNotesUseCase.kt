package com.example.mynotes.domain.usecase

import com.example.mynotes.domain.model.Note
import com.example.mynotes.domain.repository.NoteRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllNotesUseCase @Inject constructor(
    private val repository: NoteRepository
) {
    operator fun invoke(): Flow<List<Note>> = repository.getAllNotes()
}