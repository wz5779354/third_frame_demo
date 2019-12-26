package com.letmecook.letmecook.myapplication.net;


import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.ArrayMap;


/**
 * Created by xdj on 16/3/14.
 * 接口管理
 */
@RequiresApi(api = Build.VERSION_CODES.KITKAT)
public class ServiceManager {
    private static final ArrayMap<Class, Object> mServiceMap = new ArrayMap<>();

    public static <T> T create(Class<T> serviceClass) {
        Object service = mServiceMap.get(serviceClass);
        if (service == null) {
            service = RetrofitManager.INSTANCE.net().create(serviceClass);
            mServiceMap.put(serviceClass, service);
        }
        return (T) service;
    }


}
