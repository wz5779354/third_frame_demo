package com.letmecook.letmecook.myapplication.base;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import com.letmecook.letmecook.myapplication.bean.ResultBean;

import androidx.appcompat.app.AppCompatActivity;

public abstract class BaseActivity<T extends BasePresenter> extends AppCompatActivity implements Baseview  {

    public  T mPresenter;
    public Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_base);

        if (getLayoutId() != 0) {
            setContentView(getLayoutId());
            mContext = this;
//            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);//实现全局禁用横竖屏切换
//            AppManager.getAppManager().addActivity(this);
            Log.i("onCreate===",getClass().getSimpleName());

            initData();
            initView();
            initEvent();
            initNetData();
        } else {
            throw new RuntimeException(
                    "Class need add LayoutId");
        }

    }

    public abstract int getLayoutId();

    public void initData(){

    }

    public void initView(){

    }
    public void initEvent(){

    }
    public void initNetData(){

    }
    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void getSuccess(int type, ResultBean o) {

    }

    @Override
    public void showError(Throwable e) {

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (mPresenter != null) {
            mPresenter.unsubscribe();
        }
    }
}
