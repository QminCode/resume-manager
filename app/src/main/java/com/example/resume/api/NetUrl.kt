package com.example.resume.api

import rxhttp.wrapper.annotation.DefaultDomain

/**
 * @author: playboi_YzY
 * @date: 2023/7/12 10:34
 * @description: url都写在这里
 * @version:
 */
object NetUrl {
    @DefaultDomain //设置为默认域名

    const val BASE_URL = "http://shuzhirecruit.nat300.top/"

    //简历列表
//    const val LIST_URL = ""
    //人岗匹配
    const val SIMILARITY_URL = "similarity_v2/str"

    //年龄统计
    const val AGE_URL = "analysis/age"

    //学历
    const val EDU_URL = "analysis/edu_level"

    //经验
    const val EXP_URL = "analysis/work_years"
}