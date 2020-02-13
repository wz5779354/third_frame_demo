package com.letmecook.letmecook.myapplication.fragmenttest;

import java.util.ArrayList;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

/**
 * Author by wangze, Date on 2020-02-12.
 */

public class OutPagerAdapter extends FragmentPagerAdapter {
    ArrayList<Fragment> fragmentList;
    String[] title = new String[]{"item0","item1","item2","item3"};
    public OutPagerAdapter(FragmentManager fm, ArrayList<Fragment> fragmentList) {
        super(fm,FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        this.fragmentList = fragmentList;
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }


    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return title[position];
    }
}
