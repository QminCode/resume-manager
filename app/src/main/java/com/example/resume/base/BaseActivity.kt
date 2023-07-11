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

}