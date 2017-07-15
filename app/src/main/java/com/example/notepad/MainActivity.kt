package com.example.notepad

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.Menu
import android.view.MenuItem
import com.example.notepad.data.DataStore
import com.example.notepad.data.Note
import com.example.notepad.given.SpaceItemDecoration
import com.example.notepad.recycler.NotesAdapter
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import org.jetbrains.anko.doAsync

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        fab.setOnClickListener {
            Snackbar.make(it, "Note Created!", Snackbar.LENGTH_LONG).setAction("Action", null).show()
            doAsync {
                DataStore.notes.notesDao().insert(Note().apply {
                    text = "Hi There!"
                })
            }
        }

        recycler.layoutManager = LinearLayoutManager(this)
        recycler.addItemDecoration(SpaceItemDecoration(this, R.dimen.padding_normal, LinearLayoutManager.VERTICAL))
        recycler.adapter = NotesAdapter()
    }

    override fun onDestroy() {
        super.onDestroy()
        recycler.adapter = null
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}
