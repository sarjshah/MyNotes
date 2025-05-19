package com.example.mynotes.domain.usecase

import com.example.mynotes.domain.model.Note
import com.example.mynotes.domain.repository.NoteRepository
import javax.inject.Inject

class UpdateUseCase @Inject constructor(
    private val repository: NoteRepository
) {
    suspend operator fun invoke(note: Note) {
        repository.updateNote(note = note)
    }
}