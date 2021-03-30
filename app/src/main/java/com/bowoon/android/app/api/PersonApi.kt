package com.bowoon.android.app.api

import com.bowoon.android.app.models.Person
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface PersonApi {
    @GET("/api/")
    fun getUsers(@Query("results") results: Int): Single<Person>
}