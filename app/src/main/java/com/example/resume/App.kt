package com.example.resume

import android.app.Application
import com.effective.android.anchors.AnchorsManager
import com.effective.android.anchors.Project
import com.example.mvvm.base.MvvmHelper
import com.example.mvvm.ext.currentProcessName

/**
 * @author: playboi_YzY
 * @date: 2023/6/3 19:56
 * @description:
 * @version:
 */
open class App : Application(){
    override fun onCreate() {
        super.onCreate()
        MvvmHelper.init(this,BuildConfig.DEBUG)
        val processName = currentProcessName
        if (currentProcessName == packageName) {
            // 主进程初始化
            onMainProcessInit()
        } else {
            // 其他进程初始化
            processName?.let { onOtherProcessInit(it) }
        }
    }

    /**
     * @description  代码的初始化请不要放在onCreate直接操作，按照下面新建异步方法
     */
    private fun onMainProcessInit() {
        AnchorsManager.getInstance()
            .debuggable(BuildConfig.DEBUG)
            //设置锚点
            .addAnchor(InitNetWork.TASK_ID, InitUtils.TASK_ID, InitComm.TASK_ID).start(
                Project.Builder("app", AppTaskFactory())
                    .add(InitNetWork.TASK_ID)
                    .add(InitComm.TASK_ID)
                    .add(InitUtils.TASK_ID)
                    .build()
            )
    }

    /**
     * 其他进程初始化，[processName] 进程名
     */
    private fun onOtherProcessInit(processName: String) {}
}