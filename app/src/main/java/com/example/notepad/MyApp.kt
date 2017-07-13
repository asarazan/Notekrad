package com.example.notepad

import android.app.Application
import com.example.notepad.data.DataStore
import com.jakewharton.threetenabp.AndroidThreeTen

/**
 * Created by Aaron Sarazan on 7/3/17.
 */
class MyApp : Application() {
    companion object {

        var testing = false
            private set

        fun testMode() {
            testing = true
        }
    }

    override fun onCreate() {
        super.onCreate()
        AndroidThreeTen.init(this)
        DataStore.init(this, inMemory = testing)
    }
}