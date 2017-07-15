package com.example.notepad.data

import android.content.Context
import com.example.notepad.sqlite.NoteDatabaseSql

/**
 * Created by Aaron Sarazan on 7/5/17.
 */
object DataStore {

    lateinit var notes: NoteDatabaseSql
        private set

    fun init(c: Context) {
//        notes = Room.databaseBuilder(c, NoteDatabase::class.java, "notes").build()
        notes = NoteDatabaseSql(c)
    }

    fun inMemoryTesting(c: Context) {
//        notes.close()
//        notes = Room.inMemoryDatabaseBuilder(c, NoteDatabase::class.java).build()
    }
}