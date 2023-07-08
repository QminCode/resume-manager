package com.example.mvvm.base

import android.view.View
import androidx.databinding.ViewDataBinding
import com.example.mvvm.ext.inflateBinding
import com.noober.background.BackgroundLibrary


abstract class BaseDbActivity<VM : com.example.mvvm.base.BaseViewModel,DB: ViewDataBinding> : com.example.mvvm.base.BaseVmActivity<VM>(),
    com.example.mvvm.base.BaseIView {

    //使用了DataBinding 就不需要 layoutId了，因为 会从DB泛型 找到相关的view
    override val layoutId: Int = 0

    lateinit var mBind: DB

    /**
     * 创建DataBinding
     */
    override fun initViewDataBind(): View? {
        //利用反射 根据泛型得到 ViewDataBinding
        mBind = inflateBinding()
        BackgroundLibrary.inject(this)
        mBind.lifecycleOwner = this
        return mBind.root
    }

}