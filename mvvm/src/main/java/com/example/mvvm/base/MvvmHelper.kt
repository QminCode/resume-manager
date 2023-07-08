package com.example.mvvm.base

import android.app.Application
import android.view.Gravity
import com.example.mvvm.util.KtxActivityLifecycleCallbacks
import com.example.mvvm.util.mvvmHelperLog
import com.example.mvvm.widget.state.BaseEmptyCallback
import com.example.mvvm.widget.state.BaseErrorCallback
import com.example.mvvm.widget.state.BaseLoadingCallback
import com.hjq.toast.ToastUtils
import com.example.mvvm.ext.dp


/**
 * 作者　: hegaojian
 * 时间　: 2022/1/13
 * 描述　:
 */

/**
 * 全局上下文，可直接拿
 */
val appContext: Application by lazy { MvvmHelper.app }

object MvvmHelper {

    lateinit var app: Application

    /**
     * 框架初始化
     * @param application Application 全局上下文
     * @param debug Boolean  true为debug模式，会打印Log日志 false 关闭Log日志
     */
    fun init(application: Application, debug: Boolean) {
        app = application
        mvvmHelperLog = debug
        //注册全局 activity生命周期监听
        application.registerActivityLifecycleCallbacks(KtxActivityLifecycleCallbacks())
        com.example.mvvm.loadsir.core.LoadSir.beginBuilder()
            .setErrorCallBack(BaseErrorCallback())
            .setEmptyCallBack(BaseEmptyCallback())
            .setLoadingCallBack(BaseLoadingCallback())
            .setDefaultCallback(com.example.mvvm.loadsir.callback.SuccessCallback::class.java)
            .commit()
        ToastUtils.init(app)
        ToastUtils.setGravity(Gravity.BOTTOM, 0, 100.dp)
    }
}