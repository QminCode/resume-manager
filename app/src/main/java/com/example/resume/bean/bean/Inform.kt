package com.example.resume.bean.bean

/**
 *@author :yinxiaolong
 *@describe : com.example.resume.bean.bean
 *@date :2023-07-11 16:32
 */
class Inform : ArrayList<InformItem>()

data class InformItem(
    val age: Int,
    val graduated_school: String,
    val highest_education_level: String,
    val key_info: String,
    val name: String,
    val position: String,
    val similarity: Double,
    val working_years: Int
)