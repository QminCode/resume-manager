package com.example.resume.bean.repository

import com.example.resume.api.NetUrl
import com.example.resume.bean.Age
import com.example.resume.bean.Applicant
import com.example.resume.bean.Edu
import com.example.resume.bean.Exp
import com.example.resume.bean.bean.Inform
import rxhttp.toAwait
import rxhttp.wrapper.coroutines.Await
import rxhttp.wrapper.param.RxHttp

/**
 * @author: playboi_YzY
 * @date: 2023/7/12 10:46
 * @description: 数据仓库
 * @version:
 */
object UserRepository {
    /**
     *获取列表信息
     */
    fun getList(): Await<List<Applicant>>{
        return RxHttp.get(NetUrl.BASE_URL+NetUrl.SIMILARITY_URL).toAwait()
    }

    /**
     * 获取年龄统计
     */
    fun getAgeData(): Await<Age>{
        return RxHttp.get(NetUrl.BASE_URL+NetUrl.AGE_URL).toAwait()
    }

    /**
     * 获取学历统计
     */
    fun getEduData(): Await<Edu>{
        return RxHttp.get(NetUrl.BASE_URL+NetUrl.EDU_URL).toAwait()
    }

    /**
     * 获取经验统计
     */
    fun getExpData(): Await<Exp>{
        return RxHttp.get(NetUrl.BASE_URL+NetUrl.EXP_URL).toAwait()
    }
}