package com.example.notepad.extensions

import android.os.Handler
import android.os.Looper

/**
 * Created by Aaron Sarazan on 7/13/17.
 */

fun <T> T.postToMain(fn: () -> Unit) {
    Handler(Looper.getMainLooper()).post(fn)
}