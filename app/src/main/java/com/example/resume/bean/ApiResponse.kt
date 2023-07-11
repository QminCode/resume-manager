package com.example.resume.bean

import com.example.mvvm.entity.BasePage

/**
 * @author: playboi_YzY
 * @date: 2023/7/11 15:37
 * @description: 包装回调结果
 * @version:
 */
data class ApiResponse<T>(
    var datas: ArrayList<T>,
    var code: Int,
    var offset: Int,
) : BasePage<T>(){
    override fun getPageData() = datas

    override fun isRefresh() = offset == 0
    override fun isEmpty() = datas.isEmpty()
    override fun hasMore() = false
}
