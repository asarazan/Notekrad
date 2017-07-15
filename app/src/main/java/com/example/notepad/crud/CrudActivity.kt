package com.example.notepad.crud

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.notepad.R
import com.example.notepad.data.Note

class CrudActivity : AppCompatActivity() {
    companion object {
        private const val KEY_NOTE = "note"

        fun create(c: Context) = Intent(c, CrudActivity::class.java)
        fun update(c: Context, note: Note) = Intent(c, CrudActivity::class.java).putExtra(KEY_NOTE, note.id)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crud)
    }
}
