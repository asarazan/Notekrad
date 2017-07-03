package com.example.notepad.data

import android.arch.persistence.room.TypeConverter

import org.threeten.bp.Instant

/**
 * Created by Aaron Sarazan on 7/3/17.
 */

object Converters {

    @JvmStatic
    @TypeConverter
    fun fromTimestamp(value: Long): Instant {
        return Instant.ofEpochMilli(value)
    }

    @JvmStatic
    @TypeConverter
    fun fromInstant(value: Instant): Long {
        return value.toEpochMilli()
    }
}
