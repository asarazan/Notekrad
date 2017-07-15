package com.example.notepad.data

import android.arch.persistence.room.*
import io.reactivex.Flowable


/**
 * Created by Aaron Sarazan on 7/3/17.
 */
@Dao
interface NoteDao {

    @Query("select * from note order by created_at desc")
    fun getAll(): Flowable<List<Note>>

    @Query("select * from note where id in (:arg0) order by created_at desc")
    fun loadAllByIds(vararg ids: Int): Flowable<List<Note>>

    @Insert
    fun insert(vararg notes: Note)

    @Update
    fun update(note: Note)

    @Delete
    fun delete(note: Note)
}
