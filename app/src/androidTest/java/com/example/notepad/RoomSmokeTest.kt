package com.example.notepad

import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import com.example.notepad.data.DataStore
import com.example.notepad.data.Note
import org.junit.Assert
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
        DataStore.inMemoryTesting(InstrumentationRegistry.getContext())
    }

    @Test
    fun smokeTest() {
        val note = Note().apply {
            text = "foo bar!"
        }
        DataStore.notes.notesDao().insert(note)
        DataStore.notes.notesDao().getAll().subscribe {
            all ->
            Assert.assertEquals(1, all.size)
            Assert.assertEquals("foo bar!", all.first().text)
        }
    }
}