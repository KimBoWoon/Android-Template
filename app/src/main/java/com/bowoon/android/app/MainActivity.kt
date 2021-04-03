package com.bowoon.android.app

import android.os.Bundle
import com.bowoon.android.android_template.R
import com.bowoon.android.android_template.databinding.ActivityMainBinding
import com.bowoon.android.app.adapter.PersonAdapter
import com.bowoon.android.app.api.PersonApi
import com.bowoon.android.app.base.DataBindingActivityWithViewModel
import com.bowoon.android.app.dialogs.NetworkErrorDialog
import com.bowoon.android.app.models.Persons
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

class MainActivity : DataBindingActivityWithViewModel<ActivityMainBinding, MainActivityViewModel>
    (R.layout.activity_main, MainActivityViewModel::class.java) {
    private lateinit var personApi: PersonApi

    companion object {
        val TAG = MainActivity::class.simpleName ?: "MainActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        personApi = createRetrofit<PersonApi>(
            "https://randomuser.me/",
            OkHttpClient.Builder().apply {
                addInterceptor(HttpLoggingInterceptor().apply { this.level = HttpLoggingInterceptor.Level.BODY })
            }.build(),
            GsonConverterFactory.create(),
            RxJava3CallAdapterFactory.create()
        )

        getData()
        initLiveData()
        initBinding()
    }

    override fun initLiveData() {
        activityVM.personList.observe(this) { personList ->
            (binding.rvPersonList.adapter as? PersonAdapter)?.let { adapter ->
                adapter.items = personList
                adapter.notifyDataSetChanged()
            }
        }
    }

    override fun initBinding() {
        binding.rvPersonList.adapter = PersonAdapter().also {
            it.activityVM = activityVM
        }
    }

    private fun getData() {
        getUsers()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { person -> activityVM.personList.value = person?.persons },
                { e ->
                    e.printStackTrace()
                    NetworkErrorDialog(
                        "네트위크 통신에 실패했습니다.\n새로고침하시겠습니까?",
                        "새로고침",
                        { getData() },
                        "취소",
                        {}
                    ).show(supportFragmentManager, TAG)
                }
            )
    }

    private fun getUsers(): Single<Persons> {
        return Single.create { emitter ->
            personApi
                .getUsers(10)
                .subscribeOn(Schedulers.io())
                .subscribe(
                    { emitter.onSuccess(it) },
                    { e -> emitter.onError(e) }
                ).addTo(activityVM.compositeDisposable)
        }
    }
}