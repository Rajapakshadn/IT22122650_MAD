package com.example.taskfinal5

import com.example.taskfinal5.repository.noteRepository
import com.example.taskfinal5.viewmodel.NoteViewModel
import androidx.appcompat.app.AppCompatActivity

import androidx.lifecycle.ViewModelProvider
import com.example.taskfinal5.database.NoteDatabase
import android.os.Bundle

import com.example.taskfinal5.viewmodel.NoteViewModelFactory

class MainActivity : AppCompatActivity() {

    lateinit var n_V_Model: NoteViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setUpViewModel()
    }

    private fun setUpViewModel(){
        val noteRepository = noteRepository(NoteDatabase(this))
        val viewModelProviderFactory = NoteViewModelFactory(application,noteRepository)
        n_V_Model = ViewModelProvider(this, viewModelProviderFactory)[NoteViewModel::class.java]

    }

}