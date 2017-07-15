package com.example.notepad.data

import android.arch.persistence.room.Room
import android.content.Context

/**
 * Created by Aaron Sarazan on 7/5/17.
 */
object DataStore {

    lateinit var notes: NoteDatabase
        private set

    @JvmStatic
    fun init(c: Context) {
        notes = Room.databaseBuilder(c, NoteDatabase::class.java, "notes").build()
    }

    fun inMemoryTesting(c: Context) {
        notes.close()
        notes = Room.inMemoryDatabaseBuilder(c, NoteDatabase::class.java).build()
    }
}