package com.bowoon.android.common.databinding

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
                .with(it)
                .load(url)
                .into(it)
        }
    }
}

@BindingAdapter("android:loadImageUrlWithSize", "android:imageWidth", "android:imageHeight")
fun ImageView?.loadImageUrlWithSize(url: String?, width: Int?, height: Int?) {
    this?.let {
        if (!url.isNullOrEmpty()) {
            Glide
                .with(it)
                .load(url)
                .override(width ?: it.width, height ?: it.height)
                .into(it)
        }
    }
}

@BindingAdapter("android:loadImageUrlWithSize", "android:imageSize")
fun ImageView?.loadImageUrlWithSize(url: String?, size: Int?) {
    this?.let {
        if (!url.isNullOrEmpty()) {
            Glide
                .with(it)
                .load(url)
                .override(size ?: it.width)
                .into(it)
        }
    }
}

@BindingAdapter("android:loadImageUrlIntoMemory", "android:imageListener")
fun ImageView?.loadImageUrlIntoMemory(url: String?, callback: ((Bitmap) -> Unit)? = null) {
    this?.let {
        if (!url.isNullOrEmpty()) {
            Glide
                .with(it)
                .asBitmap()
                .load(url)
                .into(object : CustomTarget<Bitmap>() {
                    override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
                        callback?.invoke(resource)
                    }

                    override fun onLoadCleared(placeholder: Drawable?) {
                        it.setImageDrawable(placeholder)
                    }
                })
        }
    }
}