package com.example.taskfinal5.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.taskfinal5.model.note_final

@Dao
interface NoteDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)        //if there is a conflict old data will replace the new data
    suspend fun InsertNote(finalnote: note_final)

    @Update
    suspend fun updateNote(finalnote: note_final)

    @Delete
    suspend fun deleteNote(finalnote: note_final)

    @Query("SELECT * FROM note_app ORDER BY id DESC")      //first created at the botton and last created at the top
    fun getAllNotes():LiveData<List<note_final>>

    @Query("SELECT * FROM note_app WHERE noteTitle LIKE :query OR noteDesc LIKE noteDesc LIKE :query")
    fun searchNote(query: String?): LiveData<List<note_final>>
}