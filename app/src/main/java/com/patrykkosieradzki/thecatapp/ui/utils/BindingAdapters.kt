package com.patrykkosieradzki.thecatapp.ui.utils

import android.view.View
import androidx.databinding.BindingAdapter

@BindingAdapter("onClick")
fun View.setOnClick(action: () -> Unit) {
    setOnClickListener { action.invoke() }
}