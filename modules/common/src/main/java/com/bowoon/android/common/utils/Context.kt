package com.bowoon.android.common.utils

import android.content.Context
import android.view.View
import com.google.android.material.snackbar.Snackbar

fun Context?.showShortSnackbar(view: View, text: String) {
    this?.let {
        Snackbar.make(it, view, text, Snackbar.LENGTH_SHORT).show()
    }
}

fun Context?.showLongSnackbar(view: View, text: String) {
    this?.let {
        Snackbar.make(it, view, text, Snackbar.LENGTH_LONG).show()
    }
}

fun Context?.showIndefiniteSnackbar(confirmText: String, view: View, text: String) {
    this?.let {
        Snackbar.make(it, view, text, Snackbar.LENGTH_INDEFINITE).apply {
            setAction(confirmText) {
                dismiss()
            }
        }.show()
    }
}