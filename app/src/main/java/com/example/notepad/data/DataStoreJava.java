package com.example.notepad.data;

import android.arch.persistence.room.Room;
import android.content.Context;

/**
 * Created by Aaron Sarazan on 7/15/17.
 */

public class DataStoreJava {

    private static NoteDatabase notes;
    public static NoteDatabase getNotes() {
        return notes;
    }

    public static void init(Context c) {
        notes = Room.databaseBuilder(c, NoteDatabase.class, "notes").build();
    }

    public static void inMemoryTesting(Context c) {
        notes.close();
        notes = Room.inMemoryDatabaseBuilder(c, NoteDatabase.class).build();
    }
}
