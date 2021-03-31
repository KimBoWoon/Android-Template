package com.bowoon.android.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.CallAdapter
import retrofit2.Converter
import retrofit2.Retrofit

inline fun <reified T> createRetrofit(baseUrl: String, converter: Converter.Factory, factory: CallAdapter.Factory): T = Retrofit.Builder().apply {
    baseUrl(baseUrl)
    client(createOkHttpClient())
    addConverterFactory(converter)
    addCallAdapterFactory(factory)
}.build().create(T::class.java)

inline fun <reified T> createRetrofit(baseUrl: String, okHttpClient: OkHttpClient, converter: Converter.Factory, factory: CallAdapter.Factory): T = Retrofit.Builder().apply {
    baseUrl(baseUrl)
    client(okHttpClient)
    addConverterFactory(converter)
    addCallAdapterFactory(factory)
}.build().create(T::class.java)

fun createOkHttpClient(): OkHttpClient = OkHttpClient.Builder()
    .addInterceptor(HttpLoggingInterceptor().apply { this.level = level })
    .build()