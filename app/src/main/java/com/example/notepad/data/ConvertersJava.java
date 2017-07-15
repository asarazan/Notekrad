package com.example.notepad.data;

import android.arch.persistence.room.TypeConverter;

import org.threeten.bp.Instant;

/**
 * Created by Aaron Sarazan on 7/15/17.
 */

public class ConvertersJava {

    @TypeConverter
    public static Instant fromTimestamp(long value) {
        return Instant.ofEpochMilli(value);
    }

    @TypeConverter
    public static long fromInstant(Instant value) {
        return value.toEpochMilli();
    }
}
