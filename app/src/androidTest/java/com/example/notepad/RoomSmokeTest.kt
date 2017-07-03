package com.example.notepad

import android.arch.persistence.room.Room
import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import com.example.notepad.data.Note
import com.example.notepad.data.NoteDao
import com.example.notepad.data.NoteDatabase
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith






/**
 * Created by Aaron Sarazan on 7/3/17.
 */

@RunWith(AndroidJUnit4::class)
class RoomSmokeTest {

    private lateinit var noteDao: NoteDao
    private lateinit var db: NoteDatabase

    @Before
    fun createDb() {
        val context = InstrumentationRegistry.getTargetContext()
        db = Room.inMemoryDatabaseBuilder(context, NoteDatabase::class.java).build()
        noteDao = db.notesDao()
    }

    @After
    fun closeDb() {
        db.close()
    }

    @Test
    fun smokeTest() {
        val note = Note().apply {
            text = "foo bar!"
        }
        noteDao.insert(note)
        val all = noteDao.getAll()
        assertEquals(1, all.size)
        assertEquals("foo bar!", all.first().text)
    }
}