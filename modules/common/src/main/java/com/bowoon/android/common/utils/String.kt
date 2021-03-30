package com.bowoon.android.common.utils

import java.text.NumberFormat
import java.util.*

val String?.commaString: String?
    get() {
        this?.let {
            val number = it.toBigDecimal()
            return NumberFormat.getInstance(Locale.getDefault()).format(number)
        }

        return null
    }

val String?.moneyString: String?
    get() {
        this?.let {
            val number = it.toBigDecimal()
            return NumberFormat.getCurrencyInstance(Locale.getDefault()).format(number)
        }

        return null
    }

val Int?.commaString: String?
    get() = NumberFormat.getInstance(Locale.getDefault()).format(this)

val Int?.moneyString: String?
    get() = NumberFormat.getCurrencyInstance(Locale.getDefault()).format(this)

val String?.removeLast: String?
    get() {
        this?.let {
            return when {
                it.isEmpty() -> ""
                it.length == 1 -> ""
                it.isNotEmpty() -> it.substring(0, it.lastIndex)
                else -> throw IllegalArgumentException("please string argument check")
            }
        }

        return null
    }

val String?.removeFirst: String?
    get() {
        this?.let {
            return when {
                it.isEmpty() -> ""
                it.length == 1 -> ""
                it.isNotEmpty() -> it.substring(1, it.lastIndex + 1)
                else -> throw IllegalArgumentException("please string argument check")
            }
        }

        return null
    }