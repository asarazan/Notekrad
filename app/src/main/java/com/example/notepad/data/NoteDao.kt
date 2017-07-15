package com.example.notepad.data

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Delete
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import io.reactivex.Flowable


/**
 * Created by Aaron Sarazan on 7/3/17.
 */
@Dao
interface NoteDao {

    @Query("select * from note")
    fun getAll(): Flowable<List<Note>>

    @Query("select * from note where id in (:arg0)")
    fun loadAllByIds(ids: IntArray): Flowable<List<Note>>

    @Insert
    fun insert(vararg notes: Note)

    @Delete
    fun delete(note: Note)
}
