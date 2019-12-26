package com.letmecook.letmecook.myapplication.net.scheduler;



import com.letmecook.letmecook.myapplication.C;
import com.letmecook.letmecook.myapplication.bean.ResultBean;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.annotations.NonNull;


/**
 * Created by niejiahuan on 16/8/1.
 */
public class RetroUtil {
    public static <T> Observable<T> flatResult(final ResultBean<T> result) {
        return (Observable<T>) Observable.create(new ObservableOnSubscribe<Object>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<Object> e) throws Exception {
                switch (result.getReturnCode()) {
                    case C.SUCCESS_CODE:
                        e.onNext(result);
                        break;
                    default:
                        e.onError(new ApiException(result.getReturnCode(), result.getMsg(), result));
                        break;
                }
                e.onComplete();
            }
        });
    }
}
