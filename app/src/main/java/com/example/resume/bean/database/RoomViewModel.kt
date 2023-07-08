package com.example.resume.bean.database

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.resume.bean.Applicant
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * @author: playboi_YzY
 * @date: 2023/5/26 16:23
 * @description:
 * @version:
 */
class RoomViewModel(val context: Application) :AndroidViewModel(context){
    var applicant = MutableLiveData<List<Applicant>>() //LiveData储存user
    fun queryApplicants(){
        viewModelScope.launch{ //使用协程处理数据库请求
            val list = withContext(Dispatchers.IO){
                RoomManager.getDB(context).appData().getAll()
            }
            applicant.value = list
        }
    }

    fun insertUser(applicant: Applicant){
        viewModelScope.launch{ //使用协程处理数据库请求
            withContext(Dispatchers.IO){
                RoomManager.getDB(context).appData().insertAll(applicant)
            }
        }
    }

    fun deleteUser(applicant: Applicant){
        viewModelScope.launch{ //使用协程处理数据库请求
            withContext(Dispatchers.IO){
                RoomManager.getDB(context).appData().deleteUsers(applicant)
            }
        }
    }

}