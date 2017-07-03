package com.example.notepad.data

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.TypeConverters

/**
 * Created by Aaron Sarazan on 7/3/17.
 */

@Database(entities = arrayOf(Note::class), version = 1)
@TypeConverters(Converters::class)
abstract class NoteDatabase : RoomDatabase() {
    abstract fun notesDao(): NoteDao
}
