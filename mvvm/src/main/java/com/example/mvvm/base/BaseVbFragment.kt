package com.example.mvvm.base

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding
import com.example.mvvm.ext.inflateBinding

abstract class BaseVbFragment<VM : com.example.mvvm.base.BaseViewModel, VB : ViewBinding> : com.example.mvvm.base.BaseVmFragment<VM>(),
    com.example.mvvm.base.BaseIView {

    //使用了 ViewBinding 就不需要 layoutId了，因为 会从 VB 泛型 找到相关的view
    override val layoutId: Int = 0

    private var _binding: VB? = null
    val mBind: VB get() = _binding!!

    /**
     * 创建 ViewBinding
     */
    override fun initViewDataBind(inflater: LayoutInflater, container: ViewGroup?): View? {
        _binding = inflateBinding(inflater, container, false)
        return mBind.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}