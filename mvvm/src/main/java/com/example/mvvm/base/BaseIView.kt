package com.example.mvvm.base

import android.view.View
import com.example.mvvm.loadsir.callback.Callback
import com.example.mvvm.net.LoadStatusEntity
import com.example.mvvm.net.LoadingDialogEntity

/**
 * 作者　: hegaojian
 * 时间　: 2020/11/4
 * 描述　:
 */
interface BaseIView {

    /**
     * 子类可传入需要被包裹的View，做状态显示-空、错误、加载
     * 如果子类不覆盖该方法 那么会将整个当前Activity(toolbar头部除外)/Fragment界面都当做View包裹
     */
    fun getLoadingView(): View? {
        return null
    }

    /**
     * 子类可传入自己的标题栏 不给默认是null
     * @return View?
     */
    fun getTitleBarView(): View? {
        return null
    }

    /**
     * 展示加载中界面
     */
    fun showEmptyUi(message: String = "暂无数据")

    /**
     * 展示加载中界面
     */
    fun showLoadingUi(message: String = "请求网络中...")

    /**
     * 展示错误界面
     * @param message String
     */
    fun showErrorUi(message: String = "加载失败")

    /**
     * 界面显示加载成功
     */
    fun showSuccessUi()

    /**
     * 请求成功
     */
    fun onRequestSuccess()

    /**
     * 请求失败
     * @param loadStatus LoadStatusEntity
     */
    fun onRequestError(loadStatus: LoadStatusEntity)

    /**
     * 请求数据为空时 在 ResponseParser 中判断了如果是列表数据，是第一页，且没有数据时 回调这个方法
     * @param loadStatus LoadStatusEntity
     */
    fun onRequestEmpty(loadStatus: LoadStatusEntity)

    /**
     * 当界面是错误界面，空界面时，点击触发重试
     */
    fun onLoadRetry()

    /**
     * 显示通用loading弹窗dialog
     */
    fun showLoading(setting: LoadingDialogEntity)

    /**
     * 隐藏通用loading弹窗dialog
     */
    fun dismissLoading(setting: LoadingDialogEntity)

    /**
     * 显示自定义loading弹窗dialog
     */
    fun showCustomLoading(setting: LoadingDialogEntity)

    /**
     * 隐藏自定义loading弹窗dialog
     */
    fun dismissCustomLoading(setting: LoadingDialogEntity)

    /**
     *  返回当前Activity/Fragment 自定义 空状态布局
     */
    fun getEmptyStateLayout(): com.example.mvvm.loadsir.callback.Callback?

    /**
     *  返回当前Activity/Fragment 自定义 加载中状态布局
     */
    fun getLoadingStateLayout(): com.example.mvvm.loadsir.callback.Callback?

    /**
     *  返回当前Activity/Fragment 自定义 错误状态布局
     */
    fun getErrorStateLayout(): com.example.mvvm.loadsir.callback.Callback?

    /**
     *  返回当前Activity/Fragment 自定义 的其他状态布局集合
     */
    fun getCustomStateLayout(): List<com.example.mvvm.loadsir.callback.Callback>?

}