package com.example.notepad.crud

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import com.example.notepad.R
import com.example.notepad.data.DataStore
import com.example.notepad.data.Note
import com.example.notepad.extensions.android
import kotlinx.android.synthetic.main.activity_crud.*
import org.jetbrains.anko.doAsync
import org.threeten.bp.Instant

class CrudActivity : AppCompatActivity() {
    companion object {
        private const val KEY_NOTE = "note"
        fun create(c: Context) = Intent(c, CrudActivity::class.java)
        fun update(c: Context, note: Note) = Intent(c, CrudActivity::class.java).putExtra(KEY_NOTE, note.id)
    }

    private var note: Note? = null
    val isUpdateMode: Boolean get() = intent.hasExtra(KEY_NOTE)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crud)
        if (isUpdateMode) {
            DataStore.notes.notesDao().loadAllByIds(intent.getIntExtra(KEY_NOTE, -1))
                    .android()
                    .subscribe {
                        if (note == null) note = it.first()
                        update()
                    }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        if (isUpdateMode) menuInflater.inflate(R.menu.menu_delete, menu)
        menuInflater.inflate(R.menu.menu_accept, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_accept -> {
                save()
                finish()
                true
            }
            R.id.action_delete -> {
                delete()
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    fun save() {
        doAsync {
            val updated = updatedNote()
            if (isUpdateMode) {
                DataStore.notes.notesDao().update(updated)
            } else {
                DataStore.notes.notesDao().insert(updated)
            }
        }
    }

    fun update() {
        edit_text.setText(note!!.text)
    }

    fun delete() {
        doAsync {
            DataStore.notes.notesDao().delete(note!!)
        }
    }

    private fun updatedNote(): Note {
        return (note ?: Note()).apply {
            text = edit_text.text.toString()
            updatedAt = Instant.now()
        }
    }
}
