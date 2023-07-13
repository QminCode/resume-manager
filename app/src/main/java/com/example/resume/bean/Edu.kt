package com.example.resume.bean

/**
 * @author: playboi_YzY
 * @date: 2023/7/11 14:34
 * @description: 学历统计数据
 * @version:
 */
data class Edu(
    var primary : Int,

    var middle : Int,

    /**
     * 职高
     */
    var polytechnic : Int,

    var highSchool : Int,

    /**
     * 大专
     */
    var juniorCollege : Int,

    var undergraduate : Int,

    var master : Int,

    var doctor : Int

)
