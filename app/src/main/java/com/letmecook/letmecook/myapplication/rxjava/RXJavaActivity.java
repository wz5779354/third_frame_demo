package com.letmecook.letmecook.myapplication.rxjava;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.letmecook.letmecook.myapplication.R;

import androidx.appcompat.app.AppCompatActivity;

public class RXJavaActivity extends AppCompatActivity {

    private static final String TAG = RXJavaActivity.class.getSimpleName();
    private DownLoadUtil downLoadUtil;
    private RxJavaUtil rxJavaUtil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rxjava);
        downLoadUtil = new DownLoadUtil();

        rxJavaUtil = new RxJavaUtil();


    }

    public void getBanner(View view) {
        downLoadUtil.downLoad();
    }

    public void test(View view) {
        downLoadUtil.testDisposeEvent();
    }

    public void mapTest(View view) {
        downLoadUtil.mapTest();
    }

    public void zipTest(View view) {
        downLoadUtil.zipTest();
    }

    public void concatTest(View view) {
        downLoadUtil.concatTest();
    }

    public void flatMapTest(View view) {
        downLoadUtil.flatMapTest();
    }

    public void concatMapTest(View view) {
        downLoadUtil.concatMapTest();
    }

    public void distinctTest(View view) {
        downLoadUtil.distinctTest();
    }

    public void filterTest(View view) {
        downLoadUtil.filterTest();
    }

    public void bufferTest(View view) {
        downLoadUtil.bufferTest();
    }

    public void RxJavaUtil(View view) {
        Log.i(TAG,"RxJavaUtil--SimpleUse");
        rxJavaUtil.simpleUse();
    }

    public void subscribeOnMethodTest(View view) {
        rxJavaUtil.subscribeOnMethodTest();
    }

    public void flatMapTest2(View view) {
        rxJavaUtil.flatMapTest2();
    }

    public void flatMapTest1(View view) {
        rxJavaUtil.flatMapTest1();
    }
}
