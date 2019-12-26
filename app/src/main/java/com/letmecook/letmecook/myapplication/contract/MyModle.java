package com.letmecook.letmecook.myapplication.contract;

import com.letmecook.letmecook.myapplication.net.ApiService;
import com.letmecook.letmecook.myapplication.net.ServiceManager;
import com.letmecook.letmecook.myapplication.net.scheduler.RxSchedulers;

import java.util.Map;

import io.reactivex.Observable;
import okhttp3.RequestBody;


/**
 * Author by wangze, Date on 2019/12/3.
 */

public class MyModle implements MyContract.Modle{
    @Override
    public Observable banner() {
        return ServiceManager.create(ApiService.class)
                             .banner()
                             .compose(RxSchedulers.toMain());
    }

    @Override
    public Observable otherVersion() {
        return ServiceManager.create(ApiService.class)
                .otherVersion()
                .compose(RxSchedulers.toMain());
    }

    @Override
    public Observable signin(RequestBody body) {
        return ServiceManager.create(ApiService.class)
                .signin(body)
                .compose(RxSchedulers.toMain());
    }

    @Override
    public Observable signin2(Map<String, Object> map) {
        return ServiceManager.create(ApiService.class)
                             .signin2(map)
                             .compose(RxSchedulers.toMain());
    }


}
