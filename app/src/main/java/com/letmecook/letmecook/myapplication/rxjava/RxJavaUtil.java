package com.letmecook.letmecook.myapplication.rxjava;


import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.letmecook.letmecook.myapplication.bean.HeaderBean;
import com.letmecook.letmecook.myapplication.bean.ResultBean;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;

/**
 * Author by wangze, Date on 2019/12/3.
 */

public class RxJavaUtil {

    public static final String TAG = RxJavaUtil.class.getSimpleName();

    public void simpleUse() {

        Observable.create(new ObservableOnSubscribe<ResultBean<HeaderBean>>() {
            @Override
            public void subscribe(ObservableEmitter<ResultBean<HeaderBean>> e) throws Exception {
                HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
                httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
                OkHttpClient okHttpClient = new OkHttpClient.Builder().addInterceptor(httpLoggingInterceptor).build();
                Request request = new Request.Builder().url("http://www.letmecook.net/index.php/api/welcome/banners3").build();

                Call newCall = okHttpClient.newCall(request);

                Response response = newCall.execute();
                Gson gson = new Gson();
                ResultBean<HeaderBean> resultBean = gson.fromJson(response.body().string(), new TypeToken<ResultBean<HeaderBean>>() {
                }.getType());
                e.onNext(resultBean);

            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResultBean<HeaderBean>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ResultBean<HeaderBean> headerBeanResultBean) {
                        HeaderBean data = headerBeanResultBean.getData();
                        Log.i(TAG, data.getComboAdvertisement().getUrl());

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });




    }


    /**
     * subscribeOn()方法的调用效果
     */

    public void subscribeOnMethodTest() {
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> e) throws Exception {
                e.onNext(111);

                Log.i("thread 000---",Thread.currentThread().getName());
            }
        }).observeOn(Schedulers.io())
                .map(new Function<Integer, String>() {
                    @Override
                    public String apply(Integer integer) throws Exception {
                        Log.i("thread 111---",Thread.currentThread().getName());
                        return integer + "";
                    }
                })

                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(String s) {
                        Log.i("thread 222---",Thread.currentThread().getName());
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void flatMapTest0(){

        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> e) throws Exception {

            }
        }).flatMap(new Function<Integer, ObservableSource<?>>() {
            @Override
            public ObservableSource<?> apply(Integer integer) throws Exception {
                return null;
            }
        });
    }


    public void flatMapTest1(){
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> e) throws Exception {

                Log.i("thread--000--result-- ",1+"");
                Log.i("thread--000---- ",Thread.currentThread().getName());

                e.onNext(1);
//                e.onNext(2);
//                e.onNext(3);


            }
        }).subscribeOn(Schedulers.io())

                .flatMap(new Function<Integer, ObservableSource<String>>() {
            @Override
            public ObservableSource<String> apply(final Integer integer) throws Exception {

             /*   return Observable.create(new ObservableOnSubscribe<String>() {
                    @Override
                    public void subscribe(ObservableEmitter<String> e) throws Exception {

                                e.onNext(integer+"--wz");
                        Log.i("thread--111---- ",Thread.currentThread().getName());
                    }
                });*/
                Log.i("thread--111--result-- ",integer+"");
                Log.i("thread--111---- ",Thread.currentThread().getName());
             return Observable.just(integer+"--wz");
            }
        }).observeOn(AndroidSchedulers.mainThread())
          .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(String s) {
                        Log.i(TAG,s);
                        Log.i("thread--222---- ",Thread.currentThread().getName());
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void flatMapTest2(){
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> e) throws Exception {
                e.onNext(1);
//                e.onNext(2);
//                e.onNext(3);
                Log.i("thread--000--result-- ",1+"");
                Log.i("thread--000---- ",Thread.currentThread().getName());
            }
        }).flatMap(new Function<Integer, ObservableSource<String>>() {
            @Override
            public ObservableSource<String> apply(final Integer integer) throws Exception {

                return Observable.create(new ObservableOnSubscribe<String>() {
                    @Override
                    public void subscribe(ObservableEmitter<String> e) throws Exception {

                                e.onNext(integer+"--wz");
                        Log.i("thread--111--result-- ",integer+"");
                        Log.i("thread--111---- ",Thread.currentThread().getName());
                    }
                });
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(String s) {
                        Log.i(TAG,s);
                        Log.i("thread--222---- ",Thread.currentThread().getName());
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }



}
