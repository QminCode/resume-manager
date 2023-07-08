package com.example.mvvm.loadsir;

import android.os.Looper;

import com.example.mvvm.loadsir.target.ITarget;

import java.util.List;

import com.example.mvvm.loadsir.target.ITarget;


/**
 * Description:TODO
 * Create Time:2017/9/4 16:24
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class LoadSirUtil {

    public static boolean isMainThread() {
        return Looper.myLooper() == Looper.getMainLooper();
    }

    public static ITarget getTargetContext(Object target, List<ITarget> targetContextList) {
        for (ITarget targetContext : targetContextList) {
            if (targetContext.equals(target)) {
                return targetContext;
            }

        }
        throw new IllegalArgumentException("No TargetContext fit it");
    }
}
