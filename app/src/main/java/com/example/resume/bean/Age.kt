package com.example.resume.bean

import com.google.gson.annotations.SerializedName

/**
 * @author: playboi_YzY
 * @date: 2023/7/11 17:34
 * @description: 年龄统计数据
 * @version:
 */
class Age : ArrayList<Age.AgeItem>(){
    data class AgeItem(
        @SerializedName("age18~25")
        val age1825: Int,
        @SerializedName("age26~30")
        val age2630: Int,
        @SerializedName("age31~35:")
        val age3135: Int,
        @SerializedName("age>35")
        val age35: Int,
        @SerializedName("ave_age")
        val aveAge: Double,
        @SerializedName("max_age")
        val maxAge: Int,
        @SerializedName("min_age")
        val minAge: Int
    )
}