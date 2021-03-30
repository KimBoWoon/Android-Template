package com.bowoon.android.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.CallAdapter
import retrofit2.Converter
import retrofit2.Retrofit

inline fun <reified T> createRetrofit(baseUrl: String, interceptorLevel: HttpLoggingInterceptor.Level, converter: Converter.Factory, factory: CallAdapter.Factory): T = Retrofit.Builder().apply {
    baseUrl(baseUrl)
    client(createOkHttpClient(interceptorLevel))
    addConverterFactory(converter)
    addCallAdapterFactory(factory)
}.build().create(T::class.java)

fun createOkHttpClient(level: HttpLoggingInterceptor.Level): OkHttpClient = OkHttpClient.Builder()
    .addInterceptor(HttpLoggingInterceptor().apply { this.level = level })
    .build()