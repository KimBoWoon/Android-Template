package com.bowoon.android.android_common_library.utils

import android.app.Activity
import android.content.Context
import android.view.inputmethod.InputMethodManager

fun Activity?.hideSoftKeyBoard() {
    this?.let {
        if (!it.isFinishing) {
            val imm = it.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(it.currentFocus?.windowToken, 0)
        }
    }
}

fun Activity?.showSoftKeyBoard() {
    this?.let {
        if (!it.isFinishing) {
            val imm = it.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.showSoftInput(it.currentFocus, 0)
        }
    }
}