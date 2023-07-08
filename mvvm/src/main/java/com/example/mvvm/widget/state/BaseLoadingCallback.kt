package com.example.mvvm.widget.state

import android.content.Context
import android.view.View
import com.example.mvvm.loadsir.callback.Callback
import com.example.namespace.R

/**
 * 作者　: hegaojian
 * 时间　: 2020/12/14
 * 描述　:
 */
class BaseLoadingCallback: Callback() {

    override fun onCreateView(): Int {
        return R.layout.layout_base_loading
    }

    /**
     * 是否是 点击不可重试
     */
    override fun onReloadEvent(context: Context?, view: View?): Boolean {
        return true
    }
}