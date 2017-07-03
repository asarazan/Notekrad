package com.example.notepad.data

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import org.threeten.bp.Instant

/**
 * Created by Aaron Sarazan on 7/3/17.
 */

@Entity
class Note {

    @PrimaryKey(autoGenerate = true)
    var id: Int = 0

    /**
     * This is nullable because eventually we may want other media types.
     */
    @ColumnInfo(name = "text")
    var text: String? = null

    @ColumnInfo(name = "is_pinned")
    var isPinned: Boolean = false

    @ColumnInfo(name = "created_at")
    var createdAt: Instant = Instant.now()

    @ColumnInfo(name = "updated_at")
    var updatedAt: Instant = createdAt
}
