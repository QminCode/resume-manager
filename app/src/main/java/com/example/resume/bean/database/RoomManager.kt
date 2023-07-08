package com.example.resume.bean.database

import android.app.Application
import androidx.room.Room
import androidx.room.RoomDatabase

/**
 * @author: playboi_YzY
 * @date: 2023/5/26 11:39
 * @description:管理类
 * @version:
 */
object RoomManager {
    private var db: RoomDatabase? = null
    fun getDB(context: Application): AppDataBase{
        if (db == null){
            db = Room.databaseBuilder(context, AppDataBase::class.java, "resume_db").enableMultiInstanceInvalidation().build()
        }
        return db as AppDataBase
    }
}