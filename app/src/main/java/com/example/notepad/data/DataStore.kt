package com.example.notepad.data

import android.arch.persistence.room.Room
import android.content.Context
import com.example.notepad.sqlite.NoteDatabaseSql

/**
 * Created by Aaron Sarazan on 7/5/17.
 */
object DataStore {

    lateinit var notes: NoteDatabase
        private set

    lateinit var notesSql: NoteDatabaseSql
        private set

    fun init(c: Context) {
        notes = Room.databaseBuilder(c, NoteDatabase::class.java, "notes").build()
        notesSql = NoteDatabaseSql(c)
    }

    fun inMemoryTesting(c: Context) {
        notes.close()
        notes = Room.inMemoryDatabaseBuilder(c, NoteDatabase::class.java).build()
    }
}