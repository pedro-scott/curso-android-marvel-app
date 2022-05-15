package com.example.marvelapp.util.extension

import android.view.View

fun View.setVisibility(
    isVisible: Boolean,
    setInvisible: Boolean = false
) {
    visibility = when {
        isVisible -> View.VISIBLE
        setInvisible -> View.INVISIBLE
        else -> View.GONE
    }
}