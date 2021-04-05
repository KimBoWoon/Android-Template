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
    private const val DEFAULT_NETWORK_ERROR_MESSAGE = "알 수 없는 오류로 인해 네트워크 요청을 실패했습니다. 잠시후 다시 시도해주세요."
    private const val DEFAULT_ERROR_MESSAGE = "알 수 없는 오류로 인해 해당 기능을 수행할 수 없습니다."

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

    fun getErrorMessage(throwable: Throwable): String {
        when (throwable) {
            is HttpException -> {
                val code = throwable.code()
                val message = throwable.message()

                return when (code) {
                    400 -> { "알 수 없는 요청입니다." }
                    401 -> { "권한이 필요합니다." }
                    402 -> { "결제가 필요합니다." }
                    403 -> { "서버가 요청을 거부했습니다." }
                    404 -> { "요청한 페이지를 찾을 수 없습니다." }
                    406 -> { "허용되지 않는 요청입니다." }
                    408 -> { "서버가 응답하지 않습니다." }
                    415 -> { "지원하지 않는 미디어 타입 입니다." }
                    500 -> { "서버에서 오류가 발생했습니다." }
                    501 -> { "서버가 해당 기능을 지원하지 않습니다." }
                    502 -> { "잘못된 요청입니다." }
                    503 -> { "서버가 점검중입니다. 잠시후 다시 시도해주세요." }
                    504 -> { "서버가 응답하지 않습니다." }
                    else -> { DEFAULT_NETWORK_ERROR_MESSAGE }
                }
            }
            else -> {
                return if (BuildConfig.DEBUG) {
                    throwable.message ?: DEFAULT_ERROR_MESSAGE
                } else {
                    DEFAULT_ERROR_MESSAGE
                }
            }
        }
    }
}