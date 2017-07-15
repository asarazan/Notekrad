package com.example.notepad.extensions

import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers.mainThread
import io.reactivex.schedulers.Schedulers.io

/**
 * Created by Aaron Sarazan on 7/14/17.
 */

fun <T> Flowable<T>.android() = observeOn(mainThread()).subscribeOn(io())