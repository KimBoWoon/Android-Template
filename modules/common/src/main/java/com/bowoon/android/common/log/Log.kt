package com.bowoon.android.common.log

import com.bowoon.android.common.BuildConfig

object Log {
    fun i(tag: String, msg: String) {
        if (BuildConfig.DEBUG) {
            android.util.Log.i(tag, msg)
        }
    }

    fun i(tag: String, msg: String, throwable: Throwable) {
        if (BuildConfig.DEBUG) {
            android.util.Log.i(tag, msg, throwable)
        }
    }

    fun v(tag: String, msg: String) {
        if (BuildConfig.DEBUG) {
            android.util.Log.v(tag, msg)
        }
    }

    fun v(tag: String, msg: String, throwable: Throwable) {
        if (BuildConfig.DEBUG) {
            android.util.Log.i(tag, msg, throwable)
        }
    }

    fun d(tag: String, msg: String) {
        if (BuildConfig.DEBUG) {
            android.util.Log.d(tag, msg)
        }
    }

    fun d(tag: String, msg: String, throwable: Throwable) {
        if (BuildConfig.DEBUG) {
            android.util.Log.i(tag, msg, throwable)
        }
    }

    fun w(tag: String, msg: String) {
        if (BuildConfig.DEBUG) {
            android.util.Log.w(tag, msg)
        }
    }

    fun w(tag: String, msg: String, throwable: Throwable) {
        if (BuildConfig.DEBUG) {
            android.util.Log.i(tag, msg, throwable)
        }
    }

    fun e(tag: String, msg: String) {
        if (BuildConfig.DEBUG) {
            android.util.Log.e(tag, msg)
        }
    }

    fun e(tag: String, msg: String, throwable: Throwable) {
        if (BuildConfig.DEBUG) {
            android.util.Log.i(tag, msg, throwable)
        }
    }
}