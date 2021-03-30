package com.bowoon.android.common.databinding

import android.os.SystemClock
import android.view.View
import androidx.databinding.BindingAdapter
import com.bowoon.android.common.log.Log

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
fun singleClick(view: View, listener: View.OnClickListener) {
    view.singleClick {
        listener.onClick(view)
    }
}

fun View?.singleClick(action: () -> Unit) {
    this?.let {
        var lastClickTime: Long = 0

        it.setOnClickListener {
            if (SystemClock.elapsedRealtime() - lastClickTime < TIME_INTERVAL) {
                Log.d("singleClick", "return")
                return@setOnClickListener
            }
            else {
                Log.d("singleClick", "action")
                action.invoke()
            }
            lastClickTime = SystemClock.elapsedRealtime()
        }
    }
}

private const val TIME_INTERVAL = 500L