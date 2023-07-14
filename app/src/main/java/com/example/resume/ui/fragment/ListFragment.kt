package com.example.resume.ui.fragment

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import com.example.mvvm.ext.divider
import com.example.mvvm.ext.getColorExt
import com.example.mvvm.ext.grid
import com.example.mvvm.ext.loadMore
import com.example.mvvm.ext.logD
import com.example.mvvm.ext.logV
import com.example.mvvm.ext.refresh
import com.example.mvvm.ext.toStartActivity
import com.example.mvvm.ext.toast
import com.example.mvvm.util.decoration.DividerOrientation
import com.example.resume.R
import com.example.resume.api.NetUrl
import com.example.resume.base.BaseFragment
import com.example.resume.databinding.FragmentListBinding
import com.example.resume.ui.activity.AddManuallyFragment
import com.example.resume.ui.activity.ImageActivity
import com.example.resume.ui.adapter.ListAdapter
import com.example.resume.ui.fragment.viewmodel.ListViewModel
import com.kennyc.bottomsheet.BottomSheetListener
import com.kennyc.bottomsheet.BottomSheetMenuDialogFragment
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import org.angmarch.views.OnSpinnerItemSelectedListener
import rxhttp.toFlow
import rxhttp.wrapper.param.RxHttp
import java.io.FileOutputStream
import java.io.InputStream
import java.io.OutputStream
import java.util.Arrays
import java.util.LinkedList


/**
 * @author: playboi_YzY
 * @date: 2023/7/6 14:22
 * @description:
 * @version:
 */
class ListFragment : BottomSheetListener, BaseFragment<ListViewModel, FragmentListBinding>(){
    private val listAdapter: ListAdapter by lazy { ListAdapter() }

    override fun initView(savedInstanceState: Bundle?) {
        //初始化ToolBar
        mBind.toolbar.apply {
            title = "简历"
            setOnMenuItemClickListener{
                when (it.itemId) {
                    //抽屉布局
                    R.id.toolbar_menu_add ->
                        activity?.let { it1 ->
                            BottomSheetMenuDialogFragment.Builder(
                                context = context,
                                sheet = R.menu.list_sheet,
                                listener = this@ListFragment,
                                `object` = "some object"
                            ).show(it1.supportFragmentManager)
                        }
                }
                false
            }
        }

        //下拉选择
        val spinner = mBind.spinner
        val dataset: List<String> = LinkedList(listOf("默认排序", "年龄排序(升序)", "年龄排序(降序)",
            "工作经验排序(升序)", "工作经验排序(降序)", "学历排序(升序)", "学历排序(降序)"))
        spinner.attachDataSource(dataset)
        var spinnerListener = OnSpinnerItemSelectedListener { parent, view, position, id ->
            val item = parent.selectedItem
//            item.toast()
            initSort(item.toString())
        }
        spinner.onSpinnerItemSelectedListener = spinnerListener

        listAdapter.run {
            setOnItemClickListener{ adapter, view, position ->
                var bundle = Bundle()

                bundle.putString("image",NetUrl.IMAGE_URL+getItem(position).row_num.toString())
                toStartActivity(ImageActivity::class.java,  bundle)
            }
        }

        mBind.listSmartRefresh.refresh {
            //下拉刷新
            mViewModel.getList(false)

        }.loadMore{
            //上拉加载
            mViewModel.getList(false)
        }
        //初始化 recycleView
        mBind.listRecyclerView.grid(1).divider {
            orientation = DividerOrientation.HORIZONTAL
            includeVisible = true
            setDivider(10,true)
            setColor(getColorExt(R.color.white))
        }.adapter = listAdapter
        onLoadRetry()
    }

    /**
     * 错误界面 空界面 点击重试触发的方法
     */
    override fun onLoadRetry() {
        mViewModel.getList( loadingXml = false)
    }

    /**
     * 请求成功
     */
    override fun onRequestSuccess() {
        mViewModel.listData.observe(this, Observer {
            //请求到列表数据
            listAdapter.setList(it)
        })
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

            //在menuitem中使用Navigation，此时menu的id要与nav中的fragment的id一致
            R.id.addManuallyFragment -> toStartActivity(AddManuallyFragment::class.java)
        }
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode === 1 && resultCode === AppCompatActivity.RESULT_OK) {
            if (data != null) {
                val fileUri: Uri? = data.data
                // 使用所选文件的URI执行所需操作
                var a="xiaolong"
                lifecycleScope.launch{
                    fileUri.toString().logD("fileUel")
                    val inputStream: InputStream = context?.contentResolver?.openInputStream(fileUri!!)!!
                    val mimeType: String? = context?.contentResolver?.getType(fileUri!!)//文件类型

                    val tempFile = createTempFile()
                    val outputStream: OutputStream = FileOutputStream(tempFile)

                    val buffer = ByteArray(4096)
                    var bytesRead: Int
                    while (inputStream.read(buffer).also { bytesRead = it } != -1) {
                        outputStream.write(buffer, 0, bytesRead)
                    }

                    outputStream.close()
                    inputStream.close()

                    val result=RxHttp.postForm("http://shuzhirecruit.nat300.top/uploader")
                        .addFile("file", tempFile)
                        .addHeader("TYPE",mimeType)
                        .toFlow<String>()
                        .catch {
                            it.toString().toast()
                        }
                        .collect{
                            "上传成功".toast()
                        }

                }

            }
        }
    }
    override fun onSheetShown(bottomSheet: BottomSheetMenuDialogFragment, `object`: Any?) {
        "onSheetShown with Object ".logV()
    }
    private fun initSort(item: String){
        when(item){
            "年龄排序(升序)" -> mViewModel.sortAge1()
            "年龄排序(降序)" -> mViewModel.sortAge2()
            "工作经验排序(升序)" -> mViewModel.sortExp1()
            "工作经验排序(降序)" -> mViewModel.sortExp2()
            "学历排序(升序)" -> mViewModel.sortEdu1()
            "学历排序(降序)" -> mViewModel.sortEdu2()
        }
    }
}