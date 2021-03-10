package com.patrykkosieradzki.theanimalapp.ui.utils

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions

@BindingAdapter("onClick")
fun View.setOnClick(action: () -> Unit) {
    setOnClickListener { action.invoke() }
}

@BindingAdapter("visibleInvisible")
fun View.setVisibleInvisible(visible: Boolean) {
    visibility = if (visible) View.VISIBLE else View.INVISIBLE
}

@BindingAdapter("imageUrl")
fun ImageView.setImageUrl(url: String?) {
    url?.let {
        Glide.with(context).load(it)
            .placeholder(
                CircularProgressDrawable(context).apply {
                    strokeWidth = 5f
                    centerRadius = 30f
                    start()
                }
            )
            .apply(
                RequestOptions()
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
            )
            .into(this)
    }
}
