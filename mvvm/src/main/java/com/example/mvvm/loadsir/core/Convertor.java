package com.example.mvvm.loadsir.core;


import com.example.mvvm.loadsir.callback.Callback;
import com.example.mvvm.loadsir.callback.Callback;

/**
 * Description:TODO
 * Create Time:2017/9/4 8:58
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public interface Convertor<T> {
   Class<?extends Callback> map(T t);
}
