package com.example.resume.base

import android.view.LayoutInflater
import android.view.View
import androidx.viewbinding.ViewBinding
import com.example.mvvm.base.BaseVBActivity
import com.example.mvvm.base.BaseViewModel
import com.example.resume.R
import com.google.android.material.appbar.MaterialToolbar
import com.gyf.immersionbar.ImmersionBar

/**
 * @author: playboi_YzY
 * @date: 2023/5/21 12:18
 * @description:
 * @version:
 */
abstract class BaseActivity<VM : BaseViewModel, VB : ViewBinding> : BaseVBActivity<VM, VB>() {
//    lateinit var mToolBar: MaterialToolbar
//
//
//    override fun getTitleBarView(): View? {
//        val titleBarView = LayoutInflater.from(this).inflate(R.layout.layout_titlebar_view, null)
//        mToolBar = titleBarView.findViewById(R.id.toolbar)
//        return titleBarView
//    }
//    override fun initImmersionBar() {
//        //设置共同沉浸式样式
//        if (showToolBar()) {
//            mToolBar.setBackgroundResource(R.color.white)
//            ImmersionBar.with(this).titleBar(mToolBar).init()
//        }
//    }
}