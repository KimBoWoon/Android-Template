package com.bowoon.android.android_common_library.utils

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

inline fun <reified T> Gson.fromJson(json: String): T =
    this.fromJson(json, object : TypeToken<T>() {}.type)