package com.example.notepad;

import android.app.Application;

import com.example.notepad.data.DataStore;
import com.jakewharton.threetenabp.AndroidThreeTen;

/**
 * Created by Aaron Sarazan on 7/15/17.
 */

public class MyAppJava extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        AndroidThreeTen.init(this);
        DataStore.init(this);
    }
}
