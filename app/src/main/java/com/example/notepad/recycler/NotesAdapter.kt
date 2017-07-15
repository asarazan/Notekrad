package com.example.notepad.recycler

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.example.notepad.R
import com.example.notepad.data.DataStore
import com.example.notepad.data.Note
import com.example.notepad.extensions.android
import com.example.notepad.extensions.layoutInflater
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.item_note.view.*

/**
 * Created by Aaron Sarazan on 7/5/17.
 */
class NotesAdapter : RecyclerView.Adapter<NotesAdapter.NotesViewHolder>() {

    private var subscription: Disposable? = null
    private var notes = listOf<Note>()

    init {
        setHasStableIds(true)
    }

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView?) {
        subscription = DataStore.notes.notesDao().getAll()
                .android()
                .subscribe {
                    notes = it
                    notifyDataSetChanged()
                }
    }

    override fun onDetachedFromRecyclerView(recyclerView: RecyclerView?) {
        subscription?.dispose()
        subscription = null
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
        holder.text.text = notes[position].text
    }

    class NotesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val text by lazy { itemView.text }
    }
}