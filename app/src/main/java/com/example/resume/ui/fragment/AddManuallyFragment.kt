package com.example.resume.ui.fragment

import android.os.Bundle
import com.example.resume.base.BaseFragment
import com.example.resume.databinding.FragmentAddBinding
import com.example.resume.ui.viewmodel.AddViewModel


class AddManuallyFragment : BaseFragment<AddViewModel, FragmentAddBinding>() {

    companion object{
        fun newInstance():AddManuallyFragment {
            val args = Bundle()
            val fragment = AddManuallyFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun initView(savedInstanceState: Bundle?) {
        mBind.customToolbar.setCenterTitle("添加简历")
    }
}