package com.example.resume.bean

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * @author: playboi_YzY
 * @date: 2023/5/24 10:28
 * @description: 应聘者类，使用 Room 实体定义数据
 * @version:
 */

@Entity
data class Applicant(
    @PrimaryKey(autoGenerate = true) val id: Int,

    @ColumnInfo(name = "name") val name: String?,
    @ColumnInfo(name = "age") val age: Int?,
    @ColumnInfo(name = "academic_qualifications") val aq: String?,
    @ColumnInfo(name = "college") val college: String?,
    @ColumnInfo(name = "experience") val exp: Int?
)
