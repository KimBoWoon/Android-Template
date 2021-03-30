package com.bowoon.android.common.utils

import android.content.res.Resources
import android.util.TypedValue
import android.view.View
import androidx.annotation.StringRes
import com.google.android.material.snackbar.Snackbar

val Int.dp
    get() = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, this.toFloat(), Resources.getSystem().displayMetrics).toInt()


fun View?.showShortSnackbar(message: String) {
    this?.let {
        Snackbar.make(it, message, Snackbar.LENGTH_SHORT).show()
    }
}

fun View?.showLongSnackbar(message: String) {
    this?.let {
        Snackbar.make(it, message, Snackbar.LENGTH_LONG).show()
    }
}

fun View?.showIndefiniteSnackbar(confirmText: String, message: String) {
    this?.let {
        Snackbar.make(it, message, Snackbar.LENGTH_INDEFINITE).apply {
            setAction(confirmText) {
                dismiss()
            }
        }.show()
    }
}

fun View?.showShortSnackbar(@StringRes resId: Int) {
    this?.let {
        Snackbar.make(it, resId, Snackbar.LENGTH_SHORT).show()
    }
}

fun View?.showLongSnackbar(@StringRes resId: Int) {
    this?.let {
        Snackbar.make(it, resId, Snackbar.LENGTH_LONG).show()
    }
}

fun View?.showIndefiniteSnackbar(confirmText: String, @StringRes resId: Int) {
    this?.let {
        Snackbar.make(it, resId, Snackbar.LENGTH_INDEFINITE).apply {
            setAction(confirmText) {
                dismiss()
            }
        }.show()
    }
}