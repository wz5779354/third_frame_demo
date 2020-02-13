package com.letmecook.letmecook.myapplication.fragmenttest;

import android.view.View;

import com.letmecook.letmecook.myapplication.R;

/**
 * Author by wangze, Date on 2020-02-12.
 */

public class Fragment3 extends BaseLazyFragment {


    public Fragment3(String name) {
        super(name);
    }

    @Override
    public int getResLayout() {
        return R.layout.fragment_3;
    }

    @Override
    protected void initView(View view) {

    }

}
