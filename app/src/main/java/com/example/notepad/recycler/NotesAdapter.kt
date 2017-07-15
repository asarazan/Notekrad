package com.example.notepad.recycler

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.example.notepad.R
import com.example.notepad.crud.CrudActivity
import com.example.notepad.data.DataStore
import com.example.notepad.data.Note
import com.example.notepad.extensions.layoutInflater
import com.example.notepad.extensions.postToMain
import kotlinx.android.synthetic.main.item_note.view.*
import org.jetbrains.anko.doAsync

/**
 * Created by Aaron Sarazan on 7/5/17.
 */
class NotesAdapter : RecyclerView.Adapter<NotesAdapter.NotesViewHolder>() {

    private var notes = listOf<Note>()
    private var refreshing = false

    init {
        setHasStableIds(true)
    }

    fun refresh() {
        if (refreshing) return
        refreshing = true
        doAsync {
            val tmpNotes = DataStore.notes.notesDao().getAll()
            postToMain {
                notes = tmpNotes
                refreshing = false
                notifyDataSetChanged()
            }
        }
    }

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView?) {
        refresh()
    }

    override fun getItemId(position: Int): Long {
        return notes[position].id.toLong()
    }

    override fun getItemCount(): Int {
        return notes.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesViewHolder {
        return NotesViewHolder(parent.layoutInflater.inflate(R.layout.item_note, parent, false))
    }

    override fun onBindViewHolder(holder: NotesViewHolder, position: Int) {
        val note = notes[position]
        val context = holder.itemView.context
        holder.text.text = note.text
        holder.content.setOnClickListener {
            context.startActivity(CrudActivity.update(context, note))
        }
    }

    class NotesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val text by lazy { itemView.text }
        val content by lazy { itemView.content }
    }
}