package com.bowoon.android.test

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.bowoon.android.android_common_library.log.Log
import com.bowoon.android.android_common_library.utils.commaString
import com.bowoon.android.android_common_library.utils.getBottomNavigationBarSize
import com.bowoon.android.android_common_library.utils.getFullScreenSize
import com.bowoon.android.android_common_library.utils.getStatusBarSize
import com.bowoon.android.test.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    companion object {
        const val TAG = "MainActivity"
    }

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        binding.fullScreenSize.text = getFullScreenSize().toString()
        binding.statusBarSize.text = getStatusBarSize().toString()
        binding.bottomNavigationBarSize.text = getBottomNavigationBarSize().toString()

        Log.d(TAG, "Full Screen Size => ${getFullScreenSize()}")
        Log.d(TAG, "Status Bar Size => ${getStatusBarSize()}")
        Log.d(TAG, "Bottom Navigation Bar Size => ${getBottomNavigationBarSize()}")

        Log.d(TAG, "Bottom Navigation Bar Size => ${"123456789".commaString()}")
        Log.d(TAG, "Bottom Navigation Bar Size => ${commaString(123456789)}")
    }
}