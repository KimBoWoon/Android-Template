package com.bowoon.android.network

import android.os.Handler
import android.os.Looper
import androidx.lifecycle.Lifecycle
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.functions.Consumer
import io.reactivex.rxjava3.subjects.PublishSubject
import retrofit2.HttpException
import java.io.IOException
import java.net.SocketTimeoutException
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.ConcurrentLinkedQueue

object RxNetworkError {
    private val publish: PublishSubject<NetworkError>? = null
        get() {
            if (field == null) {
                return PublishSubject.create<NetworkError>()
            }
            return field
        }
    private val disposableMap = ConcurrentHashMap<Lifecycle, CompositeDisposable>()
    private val publisherMap = ConcurrentLinkedQueue<String>()

    data class NetworkError(val code: Int = 0, val message: String? = null, var publisher: String? = null, val retry: (() -> Unit)? = null)

    private fun getCompositeDisposable(lifecycle: Lifecycle): CompositeDisposable? {
        return if (disposableMap.containsKey(lifecycle)) {
            disposableMap[lifecycle]
        } else {
            disposableMap[lifecycle] = CompositeDisposable()
            disposableMap[lifecycle]
        }
    }

    fun register(lifecycle: Lifecycle, action: Consumer<NetworkError>) {
        val disposable = publish?.subscribe(action)
        getCompositeDisposable(lifecycle)?.add(disposable)
    }

    fun register(lifecycle: Lifecycle, publisher: String?, action: Consumer<NetworkError>) {
        if (!publisher.isNullOrEmpty()) {
            if (!publisherMap.contains(publisher)) {
                publisherMap.add(publisher)
            }
        }
        val disposable = publish?.subscribe(action)
        getCompositeDisposable(lifecycle)?.add(disposable)
    }

    fun unregister(lifecycle: Lifecycle) {
        disposableMap.remove(lifecycle)
        getCompositeDisposable(lifecycle)?.dispose()
    }

    fun unregister(lifecycle: Lifecycle, publisher: String?) {
        if (!publisher.isNullOrEmpty()) {
            if (publisherMap.contains(publisher)) {
                publisherMap.remove(publisher)
            }
        }
        disposableMap.remove(lifecycle)
        getCompositeDisposable(lifecycle)?.dispose()
    }

    private fun publish(publisher: String?, networkState: NetworkError) {
        Handler(Looper.getMainLooper())
            .post {
                if (!publisher.isNullOrEmpty()) {
                    if (publisherMap.contains(publisher)) {
                        networkState.publisher = publisher
                        publish?.onNext(networkState)
                    }
                } else {
                    publish?.onNext(networkState)
                }
            }
    }

    fun dispatch(throwable: Throwable, publisher: String? = null, retry: (() -> Unit)? = null) {
        throwable.also {
            when (it) {
                is SocketTimeoutException -> {
                    publish(publisher, NetworkError(retry = retry))
                }
                is IOException -> {
                    publish(publisher, NetworkError(retry = retry))
                }
                is HttpException -> {
                    try {
                        publish(publisher, NetworkError(it.code(), it.response()?.errorBody()?.string(), retry = retry))
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                }
                else -> {
                    publish(publisher, NetworkError(retry = retry))
                }
            }
        }
    }
}