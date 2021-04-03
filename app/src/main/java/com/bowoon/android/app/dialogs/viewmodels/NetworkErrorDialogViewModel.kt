package com.bowoon.android.app.dialogs.viewmodels

import androidx.lifecycle.MutableLiveData
import com.bowoon.android.app.base.BaseViewModel

class NetworkErrorDialogViewModel : BaseViewModel() {
    val message = MutableLiveData<String>()
    val confirmText = MutableLiveData<String>()
    val dismissText = MutableLiveData<String>()
    val buttonClicked = MutableLiveData<Boolean>()

    fun init(message: String, confirmText: String, dismissText: String) {
        this.message.value = message
        this.confirmText.value = confirmText
        this.dismissText.value = dismissText
    }

    fun onConfirm() {
        buttonClicked.value = true
    }

    fun onDismiss() {
        buttonClicked.value = false
    }
}