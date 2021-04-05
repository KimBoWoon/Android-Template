package com.bowoon.android.app.fragments.viewmodels

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.OnLifecycleEvent
import com.bowoon.android.app.api.PersonApi
import com.bowoon.android.app.base.BaseViewModel
import com.bowoon.android.app.models.Person
import com.bowoon.android.common.utils.rxRunOnUiThread
import com.bowoon.android.network.createRetrofit
import io.reactivex.rxjava3.kotlin.addTo
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class MainFragmentViewModel : BaseViewModel() {
    private lateinit var personApi: PersonApi
    val personList = MutableLiveData<MutableList<Person>>()
    val showDialog = MutableLiveData<Throwable>()

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun onCreate() {
        personApi = createRetrofit<PersonApi>(
            "https://randomuser.me/",
            OkHttpClient.Builder().apply {
                addInterceptor(HttpLoggingInterceptor().apply { this.level = HttpLoggingInterceptor.Level.BODY })
            }.build(),
            GsonConverterFactory.create(),
            RxJava3CallAdapterFactory.create()
        )
        getData()
    }

    fun getData() {
        personApi.getUsers(10)
            .rxRunOnUiThread()
            .subscribe(
                { person -> personList.value = person?.persons },
                { e ->
                    e.printStackTrace()
                    showDialog.value = e
                }
            ).addTo(compositeDisposable)
    }
}