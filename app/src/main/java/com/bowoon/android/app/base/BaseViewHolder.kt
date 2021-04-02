package com.bowoon.android.app.base

import android.view.View
import androidx.annotation.CallSuper
import androidx.recyclerview.widget.RecyclerView
import io.reactivex.rxjava3.disposables.CompositeDisposable

abstract class BaseViewHolder<T>(view: View) : RecyclerView.ViewHolder(view) {
    val compositeDisposable = CompositeDisposable()

    abstract fun bind(item: T)
}