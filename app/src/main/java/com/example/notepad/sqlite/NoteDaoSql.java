package com.example.notepad.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.notepad.data.Note;

import org.threeten.bp.Instant;

import java.util.ArrayList;
import java.util.List;

import static android.provider.BaseColumns._ID;
import static com.example.notepad.sqlite.NotesContract.NoteTable.CREATED_AT;
import static com.example.notepad.sqlite.NotesContract.NoteTable.IS_PINNED;
import static com.example.notepad.sqlite.NotesContract.NoteTable.TEXT;
import static com.example.notepad.sqlite.NotesContract.NoteTable.UPDATED_AT;
import static com.example.notepad.sqlite.NotesContract.NoteTable._TABLE_NAME;

/**
 * Created by Aaron Sarazan on 7/15/17.
 */

public final class NoteDaoSql {

    private final NotesOpenHelper helper;

    public NoteDaoSql(Context context) {
        helper = new NotesOpenHelper(context);
    }

    public List<Note> getAll() {
        Cursor cursor = helper.getReadableDatabase().query(_TABLE_NAME,
                null,
                null,
                null,
                null,
                null,
                CREATED_AT);
        List<Note> retval = new ArrayList<>();
        while (cursor.moveToNext()) {
            retval.add(fromCursor(cursor));
        }
        cursor.close();
        return retval;
    }

    public List<Note> loadAllByIds(int... ids) {
        StringBuilder questionMarks = new StringBuilder();
        int i = 0;
        while (i++ < ids.length) {
            questionMarks.append("?");
            if (i != ids.length - 1) {
                questionMarks.append(", ");
            }
        }
        String[] args = new String[ids.length];
        for (i = 0; i < ids.length; ++i) {
            args[i] = Integer.toString(ids[i]);
        }
        String selection = _ID + " IN (" + questionMarks.toString() + ")";
        Cursor cursor = helper.getReadableDatabase().query(_TABLE_NAME,
                null,
                selection,
                args,
                null,
                null,
                CREATED_AT);
        List<Note> retval = new ArrayList<>();
        cursor.close();
        return retval;
    }

    public void insert(Note... notes) {
        List<ContentValues> values = fromNotes(notes);
        SQLiteDatabase db = helper.getWritableDatabase();
        db.beginTransaction();
        try {
            for (ContentValues value : values) {
                db.insert(_TABLE_NAME, null, value);
            }
            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
        }
    }

    public void update(Note note) {
        ContentValues values = fromNote(note);
        helper.getWritableDatabase().update(_TABLE_NAME,
                values,
                _ID + " = ?",
                new String[]{ Integer.toString(note.getId()) });
    }

    public void delete(Note note) {
        helper.getWritableDatabase().delete(_TABLE_NAME,
                _ID + " = ?",
                new String[]{ Integer.toString(note.getId()) });
    }

    private static Note fromCursor(Cursor cursor) {
        int col = 0;
        Note note = new Note();
        note.setId(cursor.getInt(col++));
        note.setText(cursor.getString(col++));
        note.setPinned(cursor.getInt(col++) != 0);
        note.setCreatedAt(Instant.ofEpochMilli(cursor.getLong(col++)));
        note.setUpdatedAt(Instant.ofEpochMilli(cursor.getLong(col)));
        return note;
    }

    private static ContentValues fromNote(Note note) {
        ContentValues values = new ContentValues();
        values.put(_ID, note.getId());
        values.put(TEXT, note.getText());
        values.put(IS_PINNED, note.isPinned());
        values.put(CREATED_AT, note.getCreatedAt().toEpochMilli());
        values.put(UPDATED_AT, note.getUpdatedAt().toEpochMilli());
        return values;
    }

    private static List<ContentValues> fromNotes(Note[] notes) {
        List<ContentValues> values = new ArrayList<>();
        for (Note note : notes) {
            values.add(fromNote(note));
        }
        return values;
    }
}
