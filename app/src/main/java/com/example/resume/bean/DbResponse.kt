package com.example.resume.bean

import com.example.mvvm.entity.BasePage

/**
 * @author: playboi_YzY
 * @date: 2023/6/3 17:33
 * @description:
 * @version:
 */
data class DbResponse<T>(
    var datas: ArrayList<T>
) : BasePage<T>(){
    override fun getPageData()=datas

    override fun isRefresh() = true

    override fun isEmpty()=datas.isEmpty()

    override fun hasMore() = false

}
