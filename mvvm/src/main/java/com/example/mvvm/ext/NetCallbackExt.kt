package com.example.mvvm.ext

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.mvvm.net.BaseNetConstant
import com.example.mvvm.net.LoadStatusEntity
import com.example.mvvm.net.LoadingDialogEntity
import com.example.mvvm.net.LoadingType
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

/**
 * 普通封装一下 RxHttp请求
 * @receiver BaseViewModel
 * @param requestDslClass [@kotlin.ExtensionFunctionType] Function1<HttpRequestDsl, Unit>
 * @return Job? 可以根据这玩意 取消请求
 */
fun com.example.mvvm.base.BaseViewModel.rxHttpRequest(requestDslClass: HttpRequestDsl.() -> Unit): Job? {
    val httpRequestDsl = HttpRequestDsl()
    requestDslClass(httpRequestDsl)
    return viewModelScope.launch {
        //发起请求时
        if (httpRequestDsl.loadingType != LoadingType.LOADING_NULL) {
            loadingChange.loading.value = LoadingDialogEntity(
                loadingType = httpRequestDsl.loadingType,
                loadingMessage = httpRequestDsl.loadingMessage,
                isShow = true,
                requestCode = httpRequestDsl.requestCode,
                coroutineScope = this
            )
        }
        runCatching {
            // 携程体方法执行工作 默认是在主线程中运行的，如果有耗时操作，需要自己切换线程 ，当然请求的话RxHttp内部做了处理的，不需要额外操作
            httpRequestDsl.onRequest.invoke(this)
        }.onSuccess {
            //请求完成 走到这里说明请求成功了 如果请求类型为LOADING_XML 那么通知Activity/Fragment展示success 界面
            if (httpRequestDsl.loadingType == LoadingType.LOADING_XML) {
                loadingChange.showSuccess.value = true
            }
            //结束loading
            if (httpRequestDsl.loadingType != LoadingType.LOADING_NULL) {
                loadingChange.loading.value = LoadingDialogEntity(
                    loadingType = httpRequestDsl.loadingType,
                    loadingMessage = httpRequestDsl.loadingMessage,
                    isShow = false,
                    requestCode = httpRequestDsl.requestCode
                )
            }
        }.onFailure {
            //手动取消异常，不用管 一般是手动关闭了请求弹窗，会跟着把这个请求取消了，然后会走错误异常，这里处理一下
            if (it is CancellationException) {
                return@onFailure
            }
            if (httpRequestDsl.onError == null) {
                //如果没有传递 onError参数 默认调用封装的逻辑
                if (it.code.toString() == BaseNetConstant.EMPTY_CODE) {
                    //如果错误code 为 定义的 EMPTY_CODE（具体逻辑看 ResponseParser 的onParse方法）  说明是列表请求到了空数据
                    loadingChange.showEmpty.value =
                        LoadStatusEntity(
                            requestCode = httpRequestDsl.requestCode,
                            throwable = it,
                            errorCode = it.code,
                            errorMessage = it.msg,
                            isRefresh = httpRequestDsl.isRefreshRequest,
                            loadingType = httpRequestDsl.loadingType,
                            intentData = httpRequestDsl.intentData
                        )
                } else {
                    //请求失败时将错误日志打印一下 防止错哪里了都不晓得
                    it.printStackTrace()
                    "操！请求出错了----> ${it.message}".logE()
                    //请求失败
                    loadingChange.showError.value =
                        LoadStatusEntity(
                            requestCode = httpRequestDsl.requestCode,
                            throwable = it,
                            errorCode = it.code,
                            errorMessage = it.msg,
                            isRefresh = httpRequestDsl.isRefreshRequest,
                            loadingType = httpRequestDsl.loadingType,
                            intentData = httpRequestDsl.intentData
                        )
                    //请求结束时
                    if (httpRequestDsl.loadingType != LoadingType.LOADING_NULL) {
                        loadingChange.loading.value =
                            LoadingDialogEntity(
                                loadingType = httpRequestDsl.loadingType,
                                loadingMessage = httpRequestDsl.loadingMessage,
                                isShow = false,
                                requestCode = httpRequestDsl.requestCode
                            )
                    }
                }
            } else {
                //请求失败时将错误日志打印一下 防止错哪里了都不晓得
                it.printStackTrace()
                "操！请求出错了----> ${it.message}".logE()
                //传递了 onError 需要自己处理
                httpRequestDsl.onError?.invoke(it)
            }
        }

    }
}

/**
 * 普通封装一下 RxHttp请求
 * @receiver BaseViewModel
 * @param requestDslClass [@kotlin.ExtensionFunctionType] Function1<HttpRequestCallBackDsl<T>, Unit>
 * @return MutableLiveData<T>? 返回内部的 liveData
 */
fun <T> com.example.mvvm.base.BaseViewModel.rxHttpRequestCallBack(requestDslClass: HttpRequestCallBackDsl<T>.() -> Unit): MutableLiveData<T>? {
    val httpRequestDsl = HttpRequestCallBackDsl<T>()
    httpRequestDsl.iAwaitLiveData = MutableLiveData()
    requestDslClass(httpRequestDsl)
    viewModelScope.launch {
        //发起请求时
        if (httpRequestDsl.loadingType != LoadingType.LOADING_NULL) {
            loadingChange.loading.value = LoadingDialogEntity(
                loadingType = httpRequestDsl.loadingType,
                loadingMessage = httpRequestDsl.loadingMessage,
                isShow = true,
                requestCode = httpRequestDsl.requestCode,
                coroutineScope = this
            )
        }
        runCatching {
            // 携程体方法执行工作 默认是在主线程中运行的，如果有耗时操作，需要切换线程 ，当然请求的话RxHttp内部做了处理的，不需要额外操作
            httpRequestDsl.onRequest.invoke(this)
        }.onSuccess {
            //请求完成 走到这里说明请求成功了 如果请求类型为LOADING_XML 那么通知Activity/Fragment展示success 界面
            if (httpRequestDsl.loadingType == LoadingType.LOADING_XML) {
                loadingChange.showSuccess.value = true
            }
            //结束loading
            if (httpRequestDsl.loadingType != LoadingType.LOADING_NULL) {
                loadingChange.loading.value = LoadingDialogEntity(
                    loadingType = httpRequestDsl.loadingType,
                    loadingMessage = httpRequestDsl.loadingMessage,
                    isShow = false,
                    requestCode = httpRequestDsl.requestCode,
                )
            }
            //请求成功且结束，将 iAwaitLiveData 置空
            httpRequestDsl.iAwaitLiveData = null
        }.onFailure {
            //请求失败，将 iAwaitLiveData 置空
            httpRequestDsl.iAwaitLiveData = null
            //手动取消异常，不用管 一般是手动关闭了请求弹窗，会跟着把这个请求取消了，然后会走错误异常，这里处理一下
            if (it is CancellationException) {
                return@onFailure
            }
            if (httpRequestDsl.onError == null) {
                //如果没有传递 onError参数 默认调用封装的逻辑
                if (it.code.toString() == BaseNetConstant.EMPTY_CODE) {
                    //如果错误code 为 定义的 EMPTY_CODE（具体逻辑看 ResponseParser 的onParse方法）  说明是列表请求到了空数据
                    loadingChange.showEmpty.value =
                        LoadStatusEntity(
                            requestCode = httpRequestDsl.requestCode,
                            throwable = it,
                            errorCode = it.code,
                            errorMessage = it.msg,
                            isRefresh = httpRequestDsl.isRefreshRequest,
                            loadingType = httpRequestDsl.loadingType,
                            intentData = httpRequestDsl.intentData
                        )
                } else {
                    //请求失败时将错误日志打印一下 防止错哪里了都不晓得
                    it.printStackTrace()
                    "操！请求出错了----> ${it.message}".logE()
                    //请求失败
                    loadingChange.showError.value =
                        LoadStatusEntity(
                            requestCode = httpRequestDsl.requestCode,
                            throwable = it,
                            errorCode = it.code,
                            errorMessage = it.msg,
                            isRefresh = httpRequestDsl.isRefreshRequest,
                            loadingType = httpRequestDsl.loadingType,
                            intentData = httpRequestDsl.intentData
                        )
                    //请求结束时
                    if (httpRequestDsl.loadingType != LoadingType.LOADING_NULL) {
                        loadingChange.loading.value =
                            LoadingDialogEntity(
                                loadingType = httpRequestDsl.loadingType,
                                loadingMessage = httpRequestDsl.loadingMessage,
                                isShow = false,
                                requestCode = httpRequestDsl.requestCode
                            )
                    }
                }
            } else {
                //请求失败时将错误日志打印一下 防止错哪里了都不晓得
                it.printStackTrace()
                "操！请求出错了----> ${it.message}".logE()
                //传递了 onError 需要自己处理
                httpRequestDsl.onError?.invoke(it)
            }
        }

    }
    return httpRequestDsl.iAwaitLiveData
}


class HttpRequestDsl {
    /**
     * 请求工作 在这里执行网络接口请求，然后回调成功数据
     */
    var onRequest: suspend CoroutineScope.() -> Unit = {}

    /**
     * 错误回调，默认为null 如果你传递了他 那么就代表你请求失败的逻辑你自己处理
     */
    var onError: ((Throwable) -> Unit)? = null

    /**
     * 目前这个只有在 loadingType != LOADING_NULL 的时候才有用 不然的话都不用传他
     */
    var loadingMessage: String = "请求网络中..."

    /**
     * 请求时loading类型 默认请求时不显示loading
     */
    @LoadingType
    var loadingType = LoadingType.LOADING_NULL

    /**
     * 请求 code 请求错误时 需要根据该字段去判断到底是哪个请求做相关处理 可以用URL去标记
     */
    var requestCode: String = "mmp"

    /**
     * 是否是刷新请求 做列表分页功能使用 一般请求用不到的
     */
    var isRefreshRequest: Boolean = false

    /**
     * 请求时回调给发起请求时携带的参数 示例场景：发起请求时传递一个 position ,如果请求失败时，可能需要把这个position回调给 activity/fragment 根据position做错误处理
     */
    var intentData: Any? = null
}

class HttpRequestCallBackDsl<T> {
    /**
     * 请求工作 在这里执行网络接口请求，然后回调成功数据
     */
    var onRequest: suspend CoroutineScope.() -> Unit = {}

    /**
     * 错误回调，默认为null 如果你传递了他 那么就代表你请求失败的逻辑你自己处理
     */
    var onError: ((Throwable) -> Unit)? = null

    /**
     * 目前这个只有在 loadingType != LOADING_NULL 的时候才有用 不然的话都不用传他
     */
    var loadingMessage: String = "请求网络中..."

    /**
     * 请求时loading类型 默认请求时不显示loading
     */
    @LoadingType
    var loadingType = LoadingType.LOADING_NULL

    /**
     * 请求 code 请求错误时 需要根据该字段去判断到底是哪个请求做相关处理 可以用URL去标记
     */
    var requestCode: String = "mmp"

    /**
     * 是否是刷新请求 做列表分页功能使用 一般请求用不到的
     */
    var isRefreshRequest: Boolean = false

    /**
     * 请求时回调给发起请求时携带的参数 示例场景：发起请求时传递一个 position ,如果请求失败时，可能需要把这个position回调给 activity/fragment 根据position做错误处理
     */
    var intentData: Any? = null

    /**
     * 请求成功 需要发射的数据
     */
    var iAwaitLiveData: MutableLiveData<T>? = null
}
