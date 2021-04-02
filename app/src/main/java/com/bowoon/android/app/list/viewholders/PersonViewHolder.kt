package com.bowoon.android.app.list.viewholders

import com.bowoon.android.android_template.databinding.ViewholderPersonBinding
import com.bowoon.android.app.base.BaseViewHolder
import com.bowoon.android.app.models.Person

class PersonViewHolder(
    private val binding: ViewholderPersonBinding
) : BaseViewHolder<Person>(binding.root) {
    override fun bind(item: Person) {
        binding.dto = item
    }
}