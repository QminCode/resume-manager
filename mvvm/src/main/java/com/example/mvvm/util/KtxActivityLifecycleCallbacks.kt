package com.example.mvvm.util

import android.app.Activity
import android.app.Application
import android.os.Bundle
import com.example.mvvm.ext.addActivity
import com.example.mvvm.ext.removeActivity

class KtxActivityLifecycleCallbacks : Application.ActivityLifecycleCallbacks {

    override fun onActivityPaused(activity: Activity) {
        XLog.d(activity.javaClass.simpleName)
    }

    override fun onActivityStarted(p0: Activity) {

    }

    override fun onActivityDestroyed(activity: Activity) {
        removeActivity(activity)
    }

    override fun onActivitySaveInstanceState(p0: Activity, p1: Bundle) {
    }

    override fun onActivityStopped(p0: Activity) {
    }

    override fun onActivityCreated(activity: Activity, p1: Bundle?) {
        XLog.d(activity.javaClass.simpleName)
        addActivity(activity)
    }

    override fun onActivityResumed(activity: Activity) {
        XLog.d(activity.javaClass.simpleName)
    }

}