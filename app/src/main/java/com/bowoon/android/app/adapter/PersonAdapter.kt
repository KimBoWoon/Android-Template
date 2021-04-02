package com.bowoon.android.app.adapter

import com.bowoon.android.app.base.BaseRecyclerViewAdapter
import com.bowoon.android.app.list.ListType
import com.bowoon.android.app.models.Person

class PersonAdapter : BaseRecyclerViewAdapter<Person>() {
    override fun getItemViewType(position: Int) = ListType.PERSON.ordinal
}