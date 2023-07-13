package com.example.resume.ui.fragment.viewmodel

import androidx.lifecycle.MutableLiveData
import com.example.mvvm.base.BaseViewModel
import com.example.mvvm.ext.rxHttpRequest
import com.example.mvvm.net.LoadingType
import com.example.resume.api.NetUrl
import com.example.resume.bean.Applicant
import com.example.resume.bean.DbResponse
import com.example.resume.bean.bean.Inform
import com.example.resume.bean.repository.UserRepository

/**
 * @author: playboi_YzY
 * @date: 2023/5/21 16:20
 * @description:
 * @version:
 */
class ListViewModel : BaseViewModel(){

//    var listData = MutableLiveData<Applicant>()
    var listData = MutableLiveData<List<Applicant>>()

    /**
     * 获取首页列表信息
     */
    fun getList(loadingXml: Boolean = false) {
//        listData.value = UserRepository.getList().await()
        rxHttpRequest {
            onRequest = {
                listData.value = UserRepository.getList().await()
            }
            requestCode = NetUrl.SIMILARITY_URL

        }
    }

    /**
     * 排序,获取排序后的结果
     */
    fun sortAge1() {
        rxHttpRequest {
            onRequest = {
                listData.value = UserRepository.sortAge1().await()
            }
        }
    }

    fun sortAge2() {
        rxHttpRequest {
            onRequest = {
                listData.value = UserRepository.sortAge2().await()
            }
        }
    }

    fun sortExp1() {
        rxHttpRequest {
            onRequest = {
                listData.value = UserRepository.sortExp1().await()
            }
        }
    }

    fun sortExp2() {
        rxHttpRequest {
            onRequest = {
                listData.value = UserRepository.sortExp1().await()
            }
        }
    }

    fun sortEdu1() {
        rxHttpRequest {
            onRequest = {
                listData.value = UserRepository.sortEdu1().await()
            }
        }
    }

    fun sortEdu2() {
        rxHttpRequest {
            onRequest = {
                listData.value = UserRepository.sortEdu2().await()
            }
        }
    }

}