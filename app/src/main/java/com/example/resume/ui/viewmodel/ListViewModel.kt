package com.example.resume.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import com.example.mvvm.base.BaseViewModel
import com.example.resume.bean.Applicant
import com.example.resume.bean.DbResponse

/**
 * @author: playboi_YzY
 * @date: 2023/5/21 16:20
 * @description:
 * @version:
 */
class ListViewModel : BaseViewModel(){

    var listData = MutableLiveData<DbResponse<Applicant>>()

    /**
     * 获取列表信息
     */
    fun getList(isRefresh: Boolean, loadingXml: Boolean = false) {

    }

}