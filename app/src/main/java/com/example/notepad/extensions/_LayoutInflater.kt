package com.example.notepad.extensions

import android.content.Context
import android.view.LayoutInflater
import android.view.View

/**
 * Created by Aaron Sarazan on 7/13/17.
 */

val Context.layoutInflater: LayoutInflater get() = LayoutInflater.from(this)
val View.layoutInflater: LayoutInflater get() = context.layoutInflater