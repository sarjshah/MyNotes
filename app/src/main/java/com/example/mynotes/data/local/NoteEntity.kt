package com.example.mynotes.data.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

const val TABLE_NAME = "notes"

@Entity(tableName = TABLE_NAME)
data class NoteEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo("id") val id: Int = 0,
    @ColumnInfo("title") val title: String,
    @ColumnInfo("description") val description: String
) {
    constructor() :  this(0, "", "")
}
