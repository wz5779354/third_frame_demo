package com.letmecook.letmecook.myapplication.net.scheduler;



import com.letmecook.letmecook.myapplication.bean.ResultBean;

import io.reactivex.Observable;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;

/**
 * Created by niejiahuan on 16/8/1.
 */
public class  RxFunc1<T extends ResultBean> implements Function<ResultBean<T>, Observable<?>> {

    @Override
    public Observable<?> apply(@NonNull ResultBean<T> tResultBean) throws Exception {
        return RetroUtil.flatResult(tResultBean);
    }
}
