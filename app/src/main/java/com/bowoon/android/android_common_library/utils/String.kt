package com.bowoon.android.android_common_library.utils

import java.text.NumberFormat
import java.util.*

fun String?.commaString(): String? {
    this?.let {
        return NumberFormat.getInstance(Locale.getDefault()).format(it)
    }

    return null
}

fun String?.moneyString(): String? {
    this?.let {
        return NumberFormat.getCurrencyInstance(Locale.getDefault()).format(it)
    }

    return null
}

fun commaString(number: Int?): String? {
    number?.let {
        return NumberFormat.getInstance(Locale.getDefault()).format(it)
    }

    return null
}

fun moneyString(number: Int?): String? {
    number?.let {
        return NumberFormat.getCurrencyInstance(Locale.getDefault()).format(it)
    }

    return null
}