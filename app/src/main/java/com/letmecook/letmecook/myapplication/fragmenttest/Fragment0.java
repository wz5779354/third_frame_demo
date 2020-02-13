package com.letmecook.letmecook.myapplication.fragmenttest;

import android.view.View;
import com.google.android.material.tabs.TabLayout;
import com.letmecook.letmecook.myapplication.R;
import java.util.ArrayList;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

/**
 * Author by wangze, Date on 2020-02-12.
 */

public class Fragment0 extends BaseLazyFragment {


    private TabLayout sub_tab;
    private ViewPager sub_viewPager;

    public Fragment0(String name) {
        super(name);
    }

    @Override
    public int getResLayout() {
        return R.layout.fragment_0;
    }

    @Override
    protected void initView(View view) {
        sub_tab = view.findViewById(R.id.sub_tab);
        sub_viewPager = view.findViewById(R.id.sub_viewPager);
        ArrayList<Fragment> list = new ArrayList<Fragment>();
        list.add(new Fragment0_0("Fragment0_0"));
        list.add(new Fragment0_1("Fragment0_1"));
        list.add(new Fragment0_2("Fragment0_2"));
        list.add(new Fragment0_3("Fragment0_3"));
        OutPagerAdapter outPagerAdapter = new OutPagerAdapter(getChildFragmentManager(),list);
        sub_viewPager.setAdapter(outPagerAdapter);
        sub_tab.setupWithViewPager(sub_viewPager);
    }


}
