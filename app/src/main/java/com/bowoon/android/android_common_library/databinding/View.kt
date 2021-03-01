package com.bowoon.android.android_common_library.databinding

import android.os.SystemClock
import android.view.View
import androidx.databinding.BindingAdapter

@BindingAdapter("android:visibleIf")
fun View?.visibleIf(condition: Boolean) {
    this?.let {
        if (condition) {
            this.visibility = View.VISIBLE
        } else {
            this.visibility = View.GONE
        }
    }
}

@BindingAdapter("android:goneIf")
fun View?.goneIf(condition: Boolean) {
    this?.let {
        if (condition) {
            this.visibility = View.GONE
        } else {
            this.visibility = View.VISIBLE
        }
    }
}

@BindingAdapter("android:singleClick")
fun View?.singleClick(action: () -> Unit) {
    this?.let {
        val currentTime = SystemClock.elapsedRealtime()

        if (currentTime + TIME_INTERVAL < SystemClock.elapsedRealtime()) {
            action.invoke()
        }
    }
}

private const val TIME_INTERVAL = 300