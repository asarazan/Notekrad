package com.example.notepad.sqlite;

import android.content.Context;

/**
 * Created by Aaron Sarazan on 7/15/17.
 */

public class NoteDatabaseSql {

    private final NoteDaoSql notesDao;

    public NoteDatabaseSql(Context context) {
        this.notesDao = new NoteDaoSql(context);
    }

    public NoteDaoSql notesDao() {
        return notesDao;
    }
}
