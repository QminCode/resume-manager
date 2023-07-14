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

    //年龄升排序
    const val SORT_AGE1_URL = "sort/age/ASC"

    //年龄降排序
    const val SORT_AGE2_URL = "sort/age/DESC"

    //经验升排序
    const val SORT_EXP1_URL = "sort/work_years/ASC"

    //经验降排序
    const val SORT_EXP2_URL = "sort/work_years/DESC"

    //学历升排序
    const val SORT_EDU1_URL = "sort/edu_level/ASC"

    //学历降排序
    const val SORT_EDU2_URL = "sort/edu_level/DESC"

    //简历列表
    const val LIST_URL = "display"
    //人岗匹配
    const val SIMILARITY_URL = "similarity_v2/str"

    //年龄统计
    const val AGE_URL = "analysis/age"

    //学历
    const val EDU_URL = "analysis/edu_level"

    //经验
    const val EXP_URL = "analysis/work_years"

    //图测试
    const val TEST_URL = "http://shuzhirecruit.nat300.top/display_picture/11"

    //图
    const val IMAGE_URL = "http://shuzhirecruit.nat300.top/display_picture/"
}