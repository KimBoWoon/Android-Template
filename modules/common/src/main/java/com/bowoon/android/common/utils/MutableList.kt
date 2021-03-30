package com.bowoon.android.common.utils

fun <E> MutableList<E>.swap(x: Int, y: Int) {
    if (x in 0 until this.size && y in 0 until this.size) {
        val temp = this[x]
        this[x] = this[y]
        this[y] = temp
    }
}