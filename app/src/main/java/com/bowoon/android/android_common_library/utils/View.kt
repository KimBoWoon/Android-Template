package com.bowoon.android.android_common_library.utils

import android.content.res.Resources
import android.util.TypedValue

val Int.px
    get() = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, this.toFloat(), Resources.getSystem().displayMetrics).toInt()
