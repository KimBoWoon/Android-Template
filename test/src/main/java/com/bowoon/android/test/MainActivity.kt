package com.bowoon.android.test

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bowoon.android.android_common_library.utils.hideSoftKeyBoard
import com.bowoon.android.android_common_library.utils.showSoftKeyBoard

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        this.hideSoftKeyBoard()
        this.showSoftKeyBoard()
    }
}