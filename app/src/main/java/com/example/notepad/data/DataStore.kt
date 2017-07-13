package com.example.notepad.data

import android.arch.persistence.room.Room
import android.content.Context

/**
 * Created by Aaron Sarazan on 7/5/17.
 */
object DataStore {

    lateinit var notes: NoteDatabase
        private set

    fun init(c: Context, inMemory: Boolean = false) {
        notes = if (inMemory) {
            Room.inMemoryDatabaseBuilder(c, NoteDatabase::class.java).build()
        } else {
            Room.databaseBuilder(c, NoteDatabase::class.java, "notes").build()
        }
    }
}