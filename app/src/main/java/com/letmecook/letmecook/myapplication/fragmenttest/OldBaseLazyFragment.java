package com.letmecook.letmecook.myapplication.fragmenttest;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

/**
 * Author by wangze, Date on 2020-02-12.
 */

public abstract class OldBaseLazyFragment extends Fragment {

    public String fragmentName = "";
    public boolean isViewCreated = false;
    public boolean isCurrentVisivable = false;
    private View rootView;
   public OldBaseLazyFragment(String name){
       fragmentName = name;
    }


    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isViewCreated) {
            if (isVisibleToUser && !isCurrentVisivable) {
                dispatchUserVisivableHint(true);
            } else if (!isVisibleToUser && isCurrentVisivable){
                dispatchUserVisivableHint(false);
            }
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        if (rootView == null) {
            rootView = inflater.inflate(getResLayout(), container, false);
        }
        initView(rootView);
        isViewCreated = true;
        if (getUserVisibleHint()) {
            setUserVisibleHint(true);
        }
        return rootView;
    }

    protected abstract void initView(View view);

    public abstract int getResLayout();


    @Override
    public void onResume() {
        super.onResume();
        Log.i(fragmentName, "onResume");
        if (!isCurrentVisivable && getUserVisibleHint()) {
            dispatchUserVisivableHint(true);
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.i(fragmentName, "onPause");
        if (isCurrentVisivable && getUserVisibleHint()){
            dispatchUserVisivableHint(false);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i(fragmentName, "onDestroy");
    }

    public void onFragmentLoad() {
        Log.i(fragmentName, "真正的更新页面");
    }

    public void onStopFragmentLoad() {
        Log.i(fragmentName, "停止一切更新");
    }

    public void dispatchUserVisivableHint(boolean isVisivableToUser) {
        isCurrentVisivable = isVisivableToUser;
        if (isVisivableToUser) {
            onFragmentLoad();
        } else {
            onStopFragmentLoad();
        }

    }
}
