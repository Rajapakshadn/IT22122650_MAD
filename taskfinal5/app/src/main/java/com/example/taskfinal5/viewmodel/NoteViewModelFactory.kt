package com.example.taskfinal5.viewmodel
import androidx.lifecycle.ViewModelProvider
import com.example.taskfinal5.repository.noteRepository

import android.app.Application
import androidx.lifecycle.ViewModel


//instantiate and return view models


class NoteViewModelFactory (val app121: Application, private val noteRepository: noteRepository): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return NoteViewModel(app121, noteRepository)as T
    }
}