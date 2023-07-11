package com.example.resume.ui.activity

import android.os.Bundle
import android.view.MenuItem
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.NavHostFragment.Companion.findNavController
import androidx.navigation.ui.onNavDestinationSelected
import androidx.navigation.ui.setupWithNavController
import com.example.mvvm.ext.*
import com.example.resume.R
import com.example.resume.base.BaseActivity
import com.example.resume.databinding.ActivityHomeBinding
import com.example.resume.ui.viewmodel.ListViewModel
import com.kennyc.bottomsheet.BottomSheetListener
import com.kennyc.bottomsheet.BottomSheetMenuDialogFragment

/**
 * @author: playboi_YzY
 * @date: 2023/5/21 16:18
 * @description: 首页
 * @version:
 */
class HomeActivity: BaseActivity<ListViewModel, ActivityHomeBinding>() {

    override fun initView(savedInstanceState: Bundle?) {
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        mBind.bottomNavigationView.setupWithNavController(navHostFragment.navController)

    }

    /**
     * 错误界面 空界面 点击重试触发的方法
     */
    override fun onLoadRetry() {
        mViewModel.getList(isRefresh = true, loadingXml = true)
    }

}