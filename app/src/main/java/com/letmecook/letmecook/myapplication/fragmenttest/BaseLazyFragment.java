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

public abstract class BaseLazyFragment extends Fragment {

    public String fragmentName = "";
    private View rootView;
   public BaseLazyFragment(String name){
       fragmentName = name;
    }



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        if (rootView == null) {
            rootView = inflater.inflate(getResLayout(), container, false);
        }
        initView(rootView);
        Log.i(fragmentName, "---onCreateView");
        return rootView;
    }

    protected abstract void initView(View view);

    public abstract int getResLayout();



    @Override
    public void onResume() {
        super.onResume();
        Log.i(fragmentName, "---onResume");

    }

    @Override
    public void onPause() {
        super.onPause();
        Log.i(fragmentName, "---onPause");

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i(fragmentName, "---onDestroy");
    }

}
