package com.example.mvvm.net

import kotlinx.coroutines.CoroutineScope


data class LoadingDialogEntity(
    @LoadingType var loadingType: Int = LoadingType.LOADING_NULL,
    var loadingMessage: String = "请求网络中...",
    var isShow: Boolean = false,
    var requestCode: String = "mmp",
    var coroutineScope: CoroutineScope? = null //请求绑定的作用域
)