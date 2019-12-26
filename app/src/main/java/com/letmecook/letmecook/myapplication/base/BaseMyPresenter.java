package com.letmecook.letmecook.myapplication.base;

import com.letmecook.letmecook.myapplication.bean.ResultBean;
import io.reactivex.Observer;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * Author by wangze, Date on 2019/12/3.
 */

public class BaseMyPresenter<V extends Baseview, T extends BaseModle> {
    public V mView;
    public T mModle;
    public CompositeDisposable mSubscriptions;

    public BaseMyPresenter(){
        mSubscriptions = new CompositeDisposable();
    }

    public Observer<ResultBean> resultBeanObserver (final int type){
        return new Observer<ResultBean>() {
            @Override
            public void onSubscribe(Disposable d) {
                mSubscriptions.add(d);
            }

            @Override
            public void onNext(ResultBean resultBean) {
            mView.hideLoading();
            mView.getSuccess(type,resultBean);
            }

            @Override
            public void onError(Throwable e) {
             mView.showError(e);
            }

            @Override
            public void onComplete() {

            }
        };

    }
}
