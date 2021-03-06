package com.bowoon.android.android_common_library.databinding

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition

@BindingAdapter("android:loadImageUrl")
fun ImageView?.loadImageUrl(url: String?) {
    this?.let {
        if (!url.isNullOrEmpty()) {
            Glide
                .with(this)
                .load(url)
                .into(this)
        }
    }
}

@BindingAdapter("android:loadImageUrlWithSize")
fun ImageView?.loadImageUrlWithSize(url: String?, width: Int, height: Int) {
    this?.let {
        if (!url.isNullOrEmpty()) {
            Glide
                .with(this)
                .load(url)
                .override(width, height)
                .into(this)
        }
    }
}

@BindingAdapter("android:loadImageUrlWithSize")
fun ImageView?.loadImageUrlWithSize(url: String?, size: Int) {
    this?.let {
        if (!url.isNullOrEmpty()) {
            Glide
                .with(this)
                .load(url)
                .override(size)
                .into(this)
        }
    }
}

@BindingAdapter("android:loadImageUrlIntoMemory")
fun ImageView?.loadImageUrlIntoMemory(url: String?) {
    this?.let {
        if (!url.isNullOrEmpty()) {
            Glide
                .with(this)
                .asBitmap()
                .load(url)
                .into(object : CustomTarget<Bitmap>() {
                    override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
                        // TODO
                        // it.setImageBitmap(resource)
                    }

                    override fun onLoadCleared(placeholder: Drawable?) {
                        it.setImageDrawable(placeholder)
                    }
                })
        }
    }
}