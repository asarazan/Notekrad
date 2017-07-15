package com.example.notepad.sqlite;

import android.provider.BaseColumns;

import static com.example.notepad.sqlite.NotesContract.NoteTable._TABLE_NAME;

/**
 * Created by Aaron Sarazan on 7/15/17.
 */

public final class NotesContract {
    private NotesContract() {}

    public static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + _TABLE_NAME + " (" +
            NoteTable._ID + " INTEGER PRIMARY KEY, " +
            NoteTable.TEXT + " TEXT, " +
            NoteTable.IS_PINNED + " INTEGER, " +
            NoteTable.CREATED_AT + " INTEGER, " +
            NoteTable.UPDATED_AT + " INTEGER" +
            ")";

    public static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + _TABLE_NAME;

    public static class NoteTable implements BaseColumns {
        public static final String _TABLE_NAME = "notes";
        public static final String TEXT = "text";
        public static final String IS_PINNED = "is_pinned";
        public static final String CREATED_AT = "created_at";
        public static final String UPDATED_AT = "updated_at";
    }
}
