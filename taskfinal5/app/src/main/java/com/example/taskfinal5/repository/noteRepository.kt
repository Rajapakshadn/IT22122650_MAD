package com.example.taskfinal5.repository

import androidx.room.Query
import com.example.taskfinal5.model.note_final
import com.example.taskfinal5.database.NoteDatabase


class noteRepository(private val db:NoteDatabase) {

    suspend fun insertNote(this_note: note_final) = db.getNoteDoa().InsertNote(this_note)//called he insert methos in notedoa in notedatabse
    suspend fun updateNote(this_note: note_final) = db.getNoteDoa().updateNote(this_note)//called he update methos in notedoa in notedatabse
    suspend fun deleteNote(this_note: note_final) = db.getNoteDoa().deleteNote(this_note)//called he delete methos in notedoa in notedatabse

    fun getAllNotes() = db.getNoteDoa().getAllNotes()
    fun searchNote(query: String?) = db.getNoteDoa().searchNote(query)





}