package com.example.resume.base

import android.os.Bundle
import android.view.View
import androidx.viewbinding.ViewBinding
import com.example.mvvm.base.BaseVbFragment
import com.example.mvvm.base.BaseViewModel
import com.example.resume.widget.CustomToolBar

/**
 * @author: playboi_YzY
 * @date: 2023/5/21 12:18
 * @description:
 * @version:
 */
abstract class BaseFragment<VM : BaseViewModel,VB: ViewBinding> : BaseVbFragment<VM, VB>(){

    abstract override fun initView(savedInstanceState: Bundle?)

    /**
     * 懒加载 只有当前fragment视图显示时才会触发该方法
     */
    override fun lazyLoadData() {}

    /**
     * 创建LiveData观察者 Fragment执行onViewCreated后触发
     */
    override fun initObserver() {}


}