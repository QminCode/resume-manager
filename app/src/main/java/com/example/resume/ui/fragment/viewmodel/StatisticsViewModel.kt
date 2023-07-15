package com.example.resume.ui.fragment.viewmodel

import androidx.lifecycle.MutableLiveData
import com.example.mvvm.base.BaseViewModel
import com.example.mvvm.ext.rxHttpRequest
import com.example.resume.bean.Age
import com.example.resume.bean.Edu
import com.example.resume.bean.Exp
import com.example.resume.bean.repository.UserRepository

/**
 * @author: playboi_YzY
 * @date: 2023/7/11 14:41
 * @description: 统计
 * @version:
 */
class StatisticsViewModel : BaseViewModel(){

    var eduData = MutableLiveData<Edu>()
    var ageData = MutableLiveData<Age>()
    var expData = MutableLiveData<Exp>()

    fun getEduData(){
        rxHttpRequest {
            onRequest = {
                eduData.value = UserRepository.getEduData().await()
            }
        }
//        eduData.value = Edu(10,50,30,0,0,0,0,0)
    }
    fun getAgeData(){
        rxHttpRequest {
            onRequest = {
                ageData.value = UserRepository.getAgeData().await()
            }
        }
    }
    fun getExpData(){
        rxHttpRequest {
            onRequest = {
                expData.value = UserRepository.getExpData().await()
            }
        }
    }
}