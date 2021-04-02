package com.bowoon.android.app.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bowoon.android.android_template.R
import com.bowoon.android.app.base.BaseViewModel
import com.bowoon.android.app.list.viewholders.PersonViewHolder
import com.bowoon.android.app.models.Person

object ViewHolderFactory {
    fun createViewHolder(type: ListType, parent: ViewGroup, activityVM: BaseViewModel? = null, fragmentVM: BaseViewModel? = null): RecyclerView.ViewHolder {
        return when (type) {
            ListType.PERSON -> {
                PersonViewHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.viewholder_person, parent, false))
            }
        }
    }

    fun <T> bindViewHolder(holder: RecyclerView.ViewHolder, position: Int, item: T?) {
        item?.let {
            when (holder) {
                is PersonViewHolder -> { holder.bind(item as Person) }
            }
        }
    }
}