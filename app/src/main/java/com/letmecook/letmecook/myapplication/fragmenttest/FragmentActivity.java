package com.letmecook.letmecook.myapplication.fragmenttest;

import android.os.Bundle;


import com.google.android.material.tabs.TabLayout;
import com.letmecook.letmecook.myapplication.R;

import java.util.ArrayList;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

public class FragmentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

       TabLayout tab = findViewById(R.id.tab);
       ViewPager viewPager = findViewById(R.id.viewPager);

        ArrayList<Fragment>  list = new ArrayList<Fragment>();
        list.add(new Fragment0("Fragment0"));
        list.add(new Fragment1("Fragment1"));
        list.add(new Fragment2("Fragment2"));
        list.add(new Fragment3("Fragment3"));
        OutPagerAdapter outPagerAdapter = new OutPagerAdapter(getSupportFragmentManager(),list);
        viewPager.setAdapter(outPagerAdapter);
        tab.setupWithViewPager(viewPager);

    }

}
