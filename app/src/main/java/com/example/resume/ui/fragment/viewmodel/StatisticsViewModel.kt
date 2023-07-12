package com.example.resume.ui.fragment.viewmodel

import androidx.lifecycle.MutableLiveData
import com.example.mvvm.base.BaseViewModel
import com.example.resume.bean.Age
import com.example.resume.bean.Edu
import com.example.resume.bean.Exp

/**
 * @author: playboi_YzY
 * @date: 2023/7/11 14:41
 * @description: 统计
 * @version:
 */
class StatisticsViewModel : BaseViewModel(){

    var EduData = MutableLiveData<Edu>()
    var AgeData = MutableLiveData<Age>()
    var ExpData = MutableLiveData<Exp>()

    fun getEduData(){
        EduData.value = Edu(30,20,45)
    }
    fun getAgeData(){
        AgeData.value = Age(22,41,30,5)
    }
    fun getExpData(){
        ExpData.value = Exp(50,20,10,10)
    }
}