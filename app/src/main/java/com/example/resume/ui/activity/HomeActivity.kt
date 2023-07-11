package com.example.resume.ui.activity

import android.R.attr
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.MenuItem
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.onNavDestinationSelected
import androidx.navigation.ui.setupWithNavController
import com.example.mvvm.ext.logV
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
class HomeActivity: BottomSheetListener, BaseActivity<ListViewModel, ActivityHomeBinding>() {

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


    override fun onSheetDismissed(
        bottomSheet: BottomSheetMenuDialogFragment,
        `object`: Any?,
        dismissEvent: Int
    ) {
        "onSheetDismissed $dismissEvent".logV()
    }

    override fun onSheetItemSelected(
        bottomSheet: BottomSheetMenuDialogFragment,
        item: MenuItem,
        `object`: Any?
    ) {
        when(item.itemId) {
            R.id.add_byFile -> {
                val intent = Intent(Intent.ACTION_OPEN_DOCUMENT)
                intent.type = "*/*" // 可选：指定所需的MIME类型，例如 "image/*"（图片）或 "application/pdf"（PDF）
                intent.addCategory(Intent.CATEGORY_OPENABLE)
                startActivityForResult(intent,1)
            }
            R.id.addManuallyFragment -> item.onNavDestinationSelected(findNavController(R.id.nav_host_fragment))
        }
    }



    override fun onSheetShown(bottomSheet: BottomSheetMenuDialogFragment, `object`: Any?) {
        "onSheetShown with Object ".logV()
    }

//    fun toStartFragment(fragment: Fragment) {
//        val fragmentManager = supportFragmentManager
//        val fragmentTransaction = fragmentManager.beginTransaction()
//        fragmentTransaction.add(androidx.fragment.R.id.fragment_container_view_tag, fragment)
//        fragmentTransaction.commit()
//    }
}