package com.example.mynotes.presentation.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mynotes.domain.model.Note
import com.example.mynotes.domain.usecase.DeleteUseCase
import com.example.mynotes.domain.usecase.GetAllNotesUseCase
import com.example.mynotes.domain.usecase.InsertUseCase
import com.example.mynotes.domain.usecase.UpdateUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val insertUseCase: InsertUseCase,
    private val deleteUseCase: DeleteUseCase,
    private val updateUseCase: UpdateUseCase,
    private val getAllNotesUseCase: GetAllNotesUseCase
) : ViewModel() {
    val uiState = getAllNotesUseCase.invoke().stateIn(
        scope = viewModelScope,
        started = SharingStarted.Eagerly,
        initialValue = UiState()
    )

    fun insertNote(note: Note) = viewModelScope.launch {
        insertUseCase.invoke(note = note)
    }

    fun deleteNote(note: Note) = viewModelScope.launch {
        deleteUseCase.invoke(note = note)
    }

    fun updateNote(note: Note) = viewModelScope.launch {
        updateUseCase.invoke(note = note)
    }

    data class UiState(
        val data: List<Note> = emptyList<Note>()
    )
}