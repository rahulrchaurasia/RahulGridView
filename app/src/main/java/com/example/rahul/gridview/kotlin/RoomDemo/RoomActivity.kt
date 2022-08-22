package com.example.rahul.gridview.kotlin.RoomDemo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar

import com.example.rahul.gridview.R
import com.example.rahul.gridview.kotlin.db.Note
import com.example.rahul.gridview.kotlin.db.NoteDatabase

import kotlinx.android.synthetic.main.activity_room.*
import kotlinx.android.synthetic.main.content_room.*

class RoomActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_room)
        setSupportActionBar(toolbar as Toolbar?)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setDisplayShowHomeEnabled(true)

        fab.setOnClickListener { view ->
//            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                    .setAction("Action", null).show()


            val strTitle = etTitle.text.toString().trim()
            val strNote = etNote.text.toString().trim()

            if(strTitle.isEmpty())
            {
                etTitle.error = "title Required"
                etTitle.requestFocus()
                return@setOnClickListener
            }else if(strNote.isEmpty())
            {
                etNote.error = "Note Required"
                etNote.requestFocus()
                return@setOnClickListener
            }


            val note = Note(strTitle,strNote)

            NoteDatabase(this!!).getNoteDao().addNote(note)


        }
    }

}
