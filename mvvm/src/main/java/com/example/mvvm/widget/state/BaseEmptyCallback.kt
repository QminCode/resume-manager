package com.example.mvvm.widget.state

import com.example.mvvm.loadsir.callback.Callback
import com.example.namespace.R


/**
 * 作者　: hegaojian
 * 时间　: 2020/12/14
 * 描述　:
 */
class BaseEmptyCallback() : Callback() {

    override fun onCreateView(): Int {
        return R.layout.layout_base_empty
    }

}