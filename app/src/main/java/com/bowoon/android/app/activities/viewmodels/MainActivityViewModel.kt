package com.bowoon.android.app.activities.viewmodels

import androidx.lifecycle.MutableLiveData
import com.bowoon.android.app.base.BaseViewModel
import com.bowoon.android.app.models.Person

class MainActivityViewModel : BaseViewModel() {
    val personList = MutableLiveData<MutableList<Person>>()
}