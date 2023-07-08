package com.example.mvvm.loadsir.target;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;

import com.example.mvvm.loadsir.callback.Callback;
import com.example.mvvm.loadsir.core.LoadLayout;
import com.example.mvvm.loadsir.callback.Callback;
import com.example.mvvm.loadsir.callback.SuccessCallback;
import com.example.mvvm.loadsir.core.LoadLayout;


/**
 * Description:TODO
 * Create Time:2019/8/29 0029 下午 2:44
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class ActivityTarget implements ITarget {

    @Override
    public boolean equals(Object target) {
        return target instanceof Activity;
    }

    @Override
    public LoadLayout replaceView(Object target, Callback.OnReloadListener onReloadListener) {
        Activity activity = (Activity) target;
        ViewGroup contentParent = activity.findViewById(android.R.id.content);
        int childIndex = 0;
        View oldContent = contentParent.getChildAt(childIndex);
        contentParent.removeView(oldContent);

        ViewGroup.LayoutParams oldLayoutParams = oldContent.getLayoutParams();
        LoadLayout loadLayout = new LoadLayout(activity, onReloadListener);
        loadLayout.setupSuccessLayout(new SuccessCallback(oldContent, activity,
                onReloadListener));
        contentParent.addView(loadLayout, childIndex, oldLayoutParams);
        return loadLayout;
    }
}
