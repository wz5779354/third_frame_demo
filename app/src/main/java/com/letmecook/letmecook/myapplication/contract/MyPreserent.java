package com.letmecook.letmecook.myapplication.contract;

import com.letmecook.letmecook.myapplication.C;
import com.letmecook.letmecook.myapplication.base.BaseMyPresenter;
import com.letmecook.letmecook.myapplication.util.MyTools;

import org.json.JSONObject;

import java.util.Map;

/**
 * Author by wangze, Date on 2019/12/3.
 */

public class MyPreserent extends BaseMyPresenter<MyContract.View,MyContract.Modle> implements MyContract.Preserent {

    public MyPreserent(MyContract.View view){
        super();
        mView = view;
        mModle = new MyModle();
    }


    @Override
    public void unsubscribe() {
        mSubscriptions.dispose();
    }

    @Override
    public void banner() {
        mModle.banner().subscribe(resultBeanObserver(C.BANNER));
    }

    @Override
    public void otherVersion() {
        mModle.otherVersion().subscribe(resultBeanObserver(C.OTHERVERSION));
    }

    @Override
    public void signin(Map<String, Object> map) {
        mView.showLoading();
        JSONObject jsonObject = new JSONObject();
        for (String key : map.keySet()) {
            MyTools.putJsonValue(jsonObject,key,map.get(key));
        }
        mModle.signin(MyTools.getBody(jsonObject.toString())).subscribe(resultBeanObserver(C.SIGNIN));
    }

    @Override
    public void signin2(Map<String, Object> map) {
        mModle.signin2(map).subscribe(resultBeanObserver(C.SIGNIN));
    }
}
