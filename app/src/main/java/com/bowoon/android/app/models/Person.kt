package com.bowoon.android.app.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Name(
    @SerializedName("title")
    @Expose
    val title: String,

    @SerializedName("first")
    @Expose
    val first: String,

    @SerializedName("last")
    @Expose
    val last: String
)

data class UserProfileImage(
    @SerializedName("large")
    @Expose
    val large: String,

    @SerializedName("medium")
    @Expose
    val medium: String,

    @SerializedName("thumbnail")
    @Expose
    val thumbnail: String
)

data class Item(
    @SerializedName("name")
    @Expose
    val name: Name,

    @SerializedName("picture")
    @Expose
    val userProfileImage: UserProfileImage,

    @SerializedName("gender")
    @Expose
    val gender: String,

    @SerializedName("phone")
    @Expose
    val phone: String? = null
)

data class Person(
    @SerializedName("results")
    @Expose
    val items: List<Item>,

    @SerializedName("page")
    @Expose
    val page: Int
)