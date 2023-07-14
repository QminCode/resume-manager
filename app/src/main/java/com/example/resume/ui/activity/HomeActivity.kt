package com.example.resume.ui.activity

import android.os.Bundle
import com.example.resume.ui.fragment.viewmodel.ListViewModel
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.example.resume.R
import com.example.resume.base.BaseActivity
import com.example.resume.databinding.ActivityHomeBinding
import com.example.resume.ui.fragment.StatisticsFragment
import com.example.resume.ui.fragment.TalentProfileFragment



/**
 * @author: playboi_YzY
 * @date: 2023/5/21 16:18
 * @description: 首页
 * @version:
 */
class HomeActivity: BaseActivity<ListViewModel, ActivityHomeBinding>() {

    override fun initView(savedInstanceState: Bundle?) {
       // val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        //val navController=findNavController(R.id.nav_host_fragment)
        //mBind.bottomNavigationView.setupWithNavController(navController)
       // NavigationUI.setupWithNavController(mBind.bottomNavigationView,navController)
        mBind.viewPager2.initMain(this)
        mBind.bottomNavigationView.setOnItemSelectedListener{
            when(it.itemId){
                R.id.listFragment ->mBind.viewPager2.setCurrentItem(0,true)
                R.id.talentProfileFragment->mBind.viewPager2.setCurrentItem(1,true)
                R.id.statisticsFragment->mBind.viewPager2.setCurrentItem(2,true)
            }
            true
        }

        mBind.viewPager2.registerOnPageChangeCallback(object :
            ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                mBind.bottomNavigationView.selectedItemId=mBind.bottomNavigationView.menu.getItem(position).itemId
            }
        })

    }

    /**
     * 错误界面 空界面 点击重试触发的方法
     */
    override fun onLoadRetry() {
        mViewModel.getList(isRefresh = true, loadingXml = true)
    }


}
//初始化 viewpager 的adapter
fun ViewPager2.initMain(activity: HomeActivity): ViewPager2 {
    //是否可滑动
    this.isUserInputEnabled = true
    this.offscreenPageLimit = 2
    //设置适配器
    adapter = object : FragmentStateAdapter(activity) {
        override fun createFragment(position: Int): Fragment {
            return when (position) {+
                0 -> {
                    com.example.resume.ui.fragment.ListFragment()
                }
                1 -> {
                    TalentProfileFragment()
                }
                else -> {
                    StatisticsFragment()
                }
            }
        }
        override fun getItemCount() = 3
    }
    return this
}