package com.example.mynotes.common.di

import android.content.Context
import com.example.mynotes.data.local.NoteDao
import com.example.mynotes.data.local.NoteDatabase
import com.example.mynotes.data.repository.NoteRepositoryImpl
import com.example.mynotes.domain.repository.NoteRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object AppModule {
    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): NoteDatabase {
        return NoteDatabase.getInstance(context = context)
    }

    @Provides
    fun provideNoteDao(noteDatabase: NoteDatabase): NoteDao {
        return noteDatabase.getNoteDoa()
    }

    @Provides
    fun provideRepository(noteDao: NoteDao): NoteRepository {
        return NoteRepositoryImpl(noteDao = noteDao)
    }
}