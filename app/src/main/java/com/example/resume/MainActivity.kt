package com.example.resume

import android.os.Bundle
import android.view.MenuItem
import com.example.resume.base.BaseActivity
import com.example.resume.databinding.FragmentListBinding
import com.example.resume.ui.viewmodel.ListViewModel
import com.kennyc.bottomsheet.BottomSheetListener
import com.kennyc.bottomsheet.BottomSheetMenuDialogFragment

/**
 * 此activity只是用来测试的，启动的activity是HomeActivity
 */
class MainActivity : BottomSheetListener, BaseActivity<ListViewModel, FragmentListBinding>() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
    }

    override fun initView(savedInstanceState: Bundle?) {
//        mToolBar.apply {
//            title = "简历"
//            setOnMenuItemClickListener{
//                when (it.itemId) {
//                    R.id.toolbar_menu_add ->
//                        BottomSheetMenuDialogFragment.Builder(
//                            context = this@MainActivity,
//                            sheet = R.menu.list_sheet,
//                            listener = this@MainActivity,
//                            `object` = "some object"
//                        ).show(supportFragmentManager)
//                }
//                false
//            }
//        }

    }

    override fun onSheetDismissed(
        bottomSheet: BottomSheetMenuDialogFragment,
        `object`: Any?,
        dismissEvent: Int
    ) {
        TODO("Not yet implemented")
    }

    override fun onSheetItemSelected(
        bottomSheet: BottomSheetMenuDialogFragment,
        item: MenuItem,
        `object`: Any?
    ) {
        TODO("Not yet implemented")
    }

    override fun onSheetShown(bottomSheet: BottomSheetMenuDialogFragment, `object`: Any?) {
        TODO("Not yet implemented")
    }
}