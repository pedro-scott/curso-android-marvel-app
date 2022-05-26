package com.example.marvelapp.util.extension

import android.os.Build
import android.text.Html
import android.view.View
import android.widget.TextView

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

fun TextView.setHtmlText(newText: String) {
    text = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        Html.fromHtml(
            newText,
            Html.FROM_HTML_MODE_COMPACT
        )
    } else Html.fromHtml(newText)
}