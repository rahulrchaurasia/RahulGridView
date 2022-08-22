package com.example.rahul.gridview.kotlin.db

import androidx.room.Insert
import androidx.room.Query

/**
 * Created by Rahul on 27/01/2020.
 */
interface NoteDao {


    @Insert
    fun addNote(note :Note)


    @Query("select * from Note")
    fun getAllNotes() : List<Note>



}