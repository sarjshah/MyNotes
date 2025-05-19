package com.example.mynotes.data.mappers

import com.example.mynotes.data.local.NoteEntity
import com.example.mynotes.domain.model.Note

fun Note.toNoteEntity(): NoteEntity {
    return NoteEntity(
        id = this.id,
        title = this.title,
        description = this.description
    )
}

fun NoteEntity.toNote(): Note {
    return Note(
        id = this.id,
        title = this.title,
        description = this.description
    )
}
