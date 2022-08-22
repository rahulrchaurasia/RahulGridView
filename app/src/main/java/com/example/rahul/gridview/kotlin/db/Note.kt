package com.example.rahul.gridview.kotlin.db

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by Rahul on 27/01/2020.
 */
@Entity
data class Note(


        val title: String,
        val note: String

) {
    @PrimaryKey(autoGenerate = true)
    val id: Int =0
}


