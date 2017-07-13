package com.example.notepad

import android.support.test.runner.AndroidJUnit4
import com.example.notepad.data.DataStore
import com.example.notepad.data.Note
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Created by Aaron Sarazan on 7/3/17.
 */

@RunWith(AndroidJUnit4::class)
class RoomSmokeTest {

    @Before
    fun createDb() {
        MyApp.testMode()
    }

    @Test
    fun smokeTest() {
        val note = Note().apply {
            text = "foo bar!"
        }
        DataStore.notes.notesDao().insert(note)
        val all = DataStore.notes.notesDao().getAll()
        assertEquals(1, all.size)
        assertEquals("foo bar!", all.first().text)
    }
}