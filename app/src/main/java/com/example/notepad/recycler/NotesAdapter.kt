package com.example.notepad.recycler

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup

/**
 * Created by Aaron Sarazan on 7/5/17.
 */
class NotesAdapter : RecyclerView.Adapter<NotesAdapter.NotesViewHolder>() {

    class NotesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }

//    private val dao = Room.

    init {
        refresh()
    }

    fun refresh() {

    }

    override fun getItemCount(): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onBindViewHolder(holder: NotesViewHolder?, position: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): NotesViewHolder {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}