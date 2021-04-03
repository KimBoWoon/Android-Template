package com.bowoon.android.app.dialogs

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.bowoon.android.android_template.R
import com.bowoon.android.android_template.databinding.DialogNetworkErrorBinding
import com.bowoon.android.app.base.DataBindingDialogWithViewModel
import com.bowoon.android.app.dialogs.viewmodels.NetworkErrorDialogViewModel

class NetworkErrorDialog(
        private val message: String = "",
        private val confirmText: String = "확인",
        private val confirmCallback: (() -> Unit)? = null,
        private val dismissText: String = "취소",
        private val dismissCallback: (() -> Unit)? = null
) : DataBindingDialogWithViewModel<DialogNetworkErrorBinding, NetworkErrorDialogViewModel>
(R.layout.dialog_network_error, NetworkErrorDialogViewModel::class.java) {
    private val viewModel by viewModels<NetworkErrorDialogViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            lifecycleOwner = this@NetworkErrorDialog
            vm = viewModel
        }

        viewModel.init(message, confirmText, dismissText)

        initLiveData()
    }

    fun initLiveData() {
        viewModel.buttonClicked.observe(this) {
            if (it) {
                confirmCallback?.invoke()
                dismissAllowingStateLoss()
            } else {
                dismissCallback?.invoke()
                dismissAllowingStateLoss()
            }
        }
    }
}