package com.example.resume.bean

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * @author: playboi_YzY
 * @date: 2023/5/24 10:28
 * @description: 应聘者类
 * @version:
 */

data class Applicant(
//    var name: String,
//    var age: String,
//    var highest_education_level: String,
//    var graduated_school: String,
//    var working_years: String,
//    var position: String,
//    var key_info: String,
//    var similarity: String,
//    var undergraduate: String,
//    var master: String,
//    var doctor: String
    val age: Int,
    val graduated_school: String,
    val highest_education_level: String,
    val key_info: String,
    val name: String,
    val position: String,
//    val similarity: Double,
    val working_years: Int
)
