package com.bowoon.android.common.utils

import android.app.Activity
import android.content.res.Configuration
import android.graphics.Point

fun Activity?.getFullScreenSize(): Point {
    val orientation = this?.resources?.configuration?.orientation
    val displayMetrics = this?.resources?.displayMetrics

    return when (orientation) {
        Configuration.ORIENTATION_PORTRAIT -> {
            Point(displayMetrics?.widthPixels ?: -1, displayMetrics?.heightPixels ?: -1)
        }
        Configuration.ORIENTATION_LANDSCAPE -> {
            Point(displayMetrics?.heightPixels ?: -1, displayMetrics?.widthPixels ?: -1)
        }
        else -> { Point(-1, -1) }
    }
}

fun Activity?.getStatusBarSize(): Int {
    this?.resources?.let { resource ->
        resource.getIdentifier("status_bar_height", "dimen", "android").let { resourceId ->
            return if (resourceId > 0) resource.getDimensionPixelSize(resourceId) else 0
        }
    }

    return 0
}

fun Activity?.getBottomNavigationBarSize(): Int {
    this?.resources?.let { resource ->
        resource.getIdentifier("navigation_bar_height", "dimen", "android").let { resourceId ->
            return if (resourceId > 0) resource.getDimensionPixelSize(resourceId) else 0
        }
    }

    return 0
}