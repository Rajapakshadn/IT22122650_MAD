package com.example.taskfinal5.viewmodel
import com.example.taskfinal5.model.note_final
import com.example.taskfinal5.repository.noteRepository


import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import android.app.Application

import kotlinx.coroutines.launch

class NoteViewModel(app: Application , private val noteRepository: noteRepository): AndroidViewModel(app) {
    fun addNote(this_note: note_final) =
        viewModelScope.launch {
            noteRepository.insertNote(this_note)
        }
    fun deleteNote(this_note: note_final) =
        viewModelScope.launch {
            noteRepository.deleteNote(this_note)
        }

    fun updateNote(this_note: note_final) =
        viewModelScope.launch {
            noteRepository.updateNote(this_note)
        }

    fun getAllNotes() = noteRepository.getAllNotes()

    fun searchNotes(query : String?) =
        noteRepository.searchNote(query)

}