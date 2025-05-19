package com.example.mynotes.data.repository

import com.example.mynotes.data.local.NoteDao
import com.example.mynotes.data.mappers.toNote
import com.example.mynotes.data.mappers.toNoteEntity
import com.example.mynotes.domain.model.Note
import com.example.mynotes.domain.repository.NoteRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class NoteRepositoryImpl @Inject constructor(
    private val noteDao: NoteDao
) : NoteRepository {
    override suspend fun insertNote(note: Note) {
        noteDao.insertNote(note.toNoteEntity())
    }

    override suspend fun updateNote(note: Note) {
        noteDao.updateNote(note.toNoteEntity())
    }

    override suspend fun deleteNote(note: Note) {
        noteDao.deleteNote(note.toNoteEntity())
    }

    override fun getAllNotes(): Flow<List<Note>> {
        return noteDao.getAllNotes().map { noteEntities ->
            noteEntities.map { noteEntity ->
                noteEntity.toNote()
            }
        }
    }
}