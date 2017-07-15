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
import kotlinx.android.synthetic.main.activity_crud.*
import org.jetbrains.anko.doAsync
import org.threeten.bp.Instant

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

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
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
            else -> super.onOptionsItemSelected(item)
        }
    }

    fun save() {
        doAsync {
            DataStore.notes.notesDao().insert(Note().apply {
                text = edit_text.text.toString()
                updatedAt = Instant.now()
            })
        }
    }
}
