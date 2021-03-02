package com.patrykkosieradzki.theanimalapp.ui.utils

import android.widget.ImageView
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso

fun loadWithPicasso(
    url: String,
    into: ImageView,
    onSuccess: () -> Unit = {},
    onError: () -> Unit = {}
) {
    Picasso.get().load(url).into(into, object : Callback {
        override fun onSuccess() {
            onSuccess.invoke()
        }

        override fun onError(e: Exception?) {
            onError.invoke()
        }
    })
}