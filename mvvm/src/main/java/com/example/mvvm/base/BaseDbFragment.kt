package com.example.mvvm.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import com.example.mvvm.ext.inflateBinding


abstract class BaseDbFragment<VM : com.example.mvvm.base.BaseViewModel,DB: ViewDataBinding> : com.example.mvvm.base.BaseVmFragment<VM>(),
    com.example.mvvm.base.BaseIView {

    //使用了 DataBinding 就不需要 layoutId了，因为 会从 VB 泛型 找到相关的view
    override val layoutId: Int = 0

    private var _binding: DB? = null
    val mBind: DB get() = _binding!!

    /**
     * 创建 DataBinding
     */
    override fun initViewDataBind(inflater: LayoutInflater, container: ViewGroup?): View? {
        _binding = inflateBinding(inflater, container, false)
        _binding?.lifecycleOwner = viewLifecycleOwner
        return mBind.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}