package com.letmecook.letmecook.myapplication.base;

import com.letmecook.letmecook.myapplication.bean.ResultBean;

/**
 * Author by wangze, Date on 2019/12/3.
 */

public interface Baseview {

     void showLoading();
     void hideLoading();
     void getSuccess(int type, ResultBean o);
     void showError(Throwable e);

}
