package com.bowoon.android.app

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bowoon.android.android_template.R
import com.bowoon.android.app.api.PersonApi
import com.bowoon.android.app.models.Person
import com.bowoon.android.common.log.Log
import com.bowoon.android.network.createRetrofit
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.addTo
import io.reactivex.rxjava3.schedulers.Schedulers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    private lateinit var personApi: PersonApi

    companion object {
        val TAG = MainActivity::class.simpleName ?: "MainActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        personApi = createRetrofit<PersonApi>(
            "https://randomuser.me/",
            OkHttpClient.Builder().apply {
                addInterceptor(HttpLoggingInterceptor().apply { this.level = HttpLoggingInterceptor.Level.BODY })
            }.build(),
            GsonConverterFactory.create(),
            RxJava3CallAdapterFactory.create()
        )

        getUsers()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { person ->
                    for (item in person.items) {
                        Log.d(TAG, item.toString())
                    }
                },
                { e -> e.printStackTrace() }
            )
    }

    private fun getUsers(): Single<Person> {
        return Single.create { emitter ->
            personApi
                .getUsers(10)
                .subscribeOn(Schedulers.io())
                .subscribe(
                    { emitter.onSuccess(it) },
                    { e -> emitter.onError(e) }
                ).addTo(CompositeDisposable())
        }
    }
}