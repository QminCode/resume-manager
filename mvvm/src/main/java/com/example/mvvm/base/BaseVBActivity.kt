package com.example.mvvm.base

import android.view.View
import androidx.viewbinding.ViewBinding
import com.example.mvvm.ext.inflateBinding
import com.noober.background.BackgroundLibrary

abstract class BaseVBActivity<VM : com.example.mvvm.base.BaseViewModel,VB: ViewBinding> : com.example.mvvm.base.BaseVmActivity<VM>(),
    com.example.mvvm.base.BaseIView {

    //使用了 ViewBinding 就不需要 layoutId了，因为 会从 VB 泛型 找到相关的view
    override val layoutId: Int = 0
    lateinit var mBind: VB

    override fun initViewDataBind(): View? {
        //利用反射 根据泛型得到 ViewDataBinding
        mBind = inflateBinding()
        BackgroundLibrary.inject(this)
        return mBind.root
    }
}