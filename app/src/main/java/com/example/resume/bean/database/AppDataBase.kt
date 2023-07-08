package com.example.resume.bean.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.resume.bean.Applicant

/**
 * @author: playboi_YzY
 * @date: 2023/5/26 11:36
 * @description:DataBase抽象类
 * @version:
 */
@Database(entities = arrayOf(Applicant::class),version = 1)
abstract class AppDataBase :RoomDatabase(){
    abstract fun appData(): AppDao
}