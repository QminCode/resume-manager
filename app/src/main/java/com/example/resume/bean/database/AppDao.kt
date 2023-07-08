package com.example.resume.bean.database

import androidx.room.*
import com.example.resume.bean.Applicant

/**
 * @author: playboi_YzY
 * @date: 2023/5/26 10:44
 * @description: 数据库操作的方法
 * @version:
 */
@Dao
interface AppDao {
    @Insert
    suspend fun insertAll(vararg applicants: Applicant)//插入

    @Insert
    suspend fun insertBothUsers(applicant1: Applicant, applicant2: Applicant)

    @Insert
    suspend fun insertUsersAndFriends(applicant: Applicant, friends: List<Applicant>)

    @Query("SELECT * FROM applicant")
    suspend fun getAll(): List<Applicant>//查询全部

    @Query("SELECT * FROM applicant WHERE name LIKE :search")
    suspend fun getByName(search: String): List<Applicant>//按姓名查询

    @Query("SELECT * FROM applicant WHERE college LIKE :search")
    suspend fun getByCollege(search: String): List<Applicant>//按大学查询

    @Update
    suspend fun updateUsers(vararg applicants: Applicant)//更新

    @Delete
    suspend fun deleteUsers(vararg applicants: Applicant)//删除
}