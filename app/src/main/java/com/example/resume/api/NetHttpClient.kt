package com.example.resume.api

import okhttp3.OkHttpClient
import rxhttp.wrapper.cookie.CookieStore
import rxhttp.wrapper.ssl.HttpsUtils
import java.util.concurrent.TimeUnit

/**
 * @author: playboi_YzY
 * @date: 2023/6/3 19:59
 * @description:
 * @version:
 */
object NetHttpClient {
    fun getDefaultOkHttpClient():  OkHttpClient.Builder {
        val sslParams = HttpsUtils.getSslSocketFactory()
        return OkHttpClient.Builder()

    }
}