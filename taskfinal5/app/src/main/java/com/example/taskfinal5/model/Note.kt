package com.example.taskfinal5.model

import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize
import android.os.Parcelable
import androidx.room.Entity


@Entity(tableName = "note_app")
@Parcelize
data class note_final(
    @PrimaryKey(autoGenerate = true)
    val id : Int,
    val noteTitle : String,
    val noteDesc : String
):Parcelable

