package com.example.mynotes.domain.repository

import com.example.mynotes.domain.model.Note
import kotlinx.coroutines.flow.Flow

interface NoteRepository {
    suspend fun insertNote(note: Note)

    suspend fun updateNote(note: Note)

    suspend fun deleteNote(note: Note)

    fun getAllNotes(): Flow<List<Note>>
}