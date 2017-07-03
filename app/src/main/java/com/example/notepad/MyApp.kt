package com.example.notepad

import android.app.Application
import com.jakewharton.threetenabp.AndroidThreeTen

/**
 * Created by Aaron Sarazan on 7/3/17.
 */
class MyApp : Application() {

    override fun onCreate() {
        super.onCreate()
        AndroidThreeTen.init(this)
    }
}