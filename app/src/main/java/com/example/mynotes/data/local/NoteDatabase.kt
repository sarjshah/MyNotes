package com.example.mynotes.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [NoteEntity::class], version = 1, exportSchema = false)
abstract class NoteDatabase : RoomDatabase() {
    companion object {
        const val DATABASE_NAME = "notes_db"

        fun getInstance(context: Context) =
            Room.databaseBuilder(context, NoteDatabase::class.java, DATABASE_NAME).build()
    }

    abstract fun getNoteDoa(): NoteDao
}