package com.letmecook.letmecook.myapplication;

import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.app.AppCompatActivity;

public class TouchActivity extends AppCompatActivity {

    public static final String TAG = TouchActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_touch);

        ViewGroup myViewGroup = findViewById(R.id.myViewGroup);
        View myView = findViewById(R.id.myView);
        myViewGroup.requestDisallowInterceptTouchEvent(false);

    }

    /**
     *  全部返回 super. 任何view 都不消耗事件
     *  事件分发顺序：TouchActivity--dispatchTouchEvent--ACTION_DOWN
     *          ->  MyViewGroup--dispatchTouchEvent--ACTION_DOWN
     *          ->  MyViewGroup--onInterceptTouchEvent--ACTION_DOWN
     *          ->  MyViewGroup--onInterceptTouchEvent--isConsume -- false
     *          ->  MyView--dispatchTouchEvent--ACTION_DOWN
     *          ->  MyView--onTouchEvent--ACTION_DOWN
     *          ->  MyView -- onTouchEvent -- isConsume -- false
     *          ->  MyView -- dispatchTouchEvent -- isConsume -- false
     *          ->  MyViewGroup -- onTouchEvent -- ACTION_DOWN
     *          ->  MyViewGroup -- onTouchEvent -- isConsume --
     *          ->  MyViewGroup--dispatchTouchEvent--isConsume -- false
     *          ->  TouchActivity--onTouchEvent--ACTION_DOWN
     *          ->  TouchActivity -- onTouchEvent -- isConsume --false
     *          ->  TouchActivity -- dispatchTouchEvent -- isConsume -- false
     *          ->  TouchActivity--dispatchTouchEvent--ACTION_UP
     *          ->  TouchActivity--onTouchEvent--ACTION_UP
     *          ->  TouchActivity -- onTouchEvent -- isConsume --false
     *          ->  TouchActivity -- dispatchTouchEvent -- isConsume --false
     *
     *
     *
     */


    /**
     *  全部返回 super. MyView的onTouch返回true
     *  事件分发顺序：TouchActivity--dispatchTouchEvent--ACTION_DOWN
     *          ->  MyViewGroup--dispatchTouchEvent--ACTION_DOWN
     *          ->  MyViewGroup--onInterceptTouchEvent--ACTION_DOWN
     *          ->  MyViewGroup--onInterceptTouchEvent--isConsume -- false
     *          ->  MyView--dispatchTouchEvent--ACTION_DOWN
     *          ->  MyView -- onTouch --ACTION_DOWN-- true
     *          ->  MyView -- dispatchTouchEvent -- isConsume -- true
     *          ->  MyViewGroup--dispatchTouchEvent--isConsume -- true
     *          ->  TouchActivity -- dispatchTouchEvent -- isConsume -- true
     *          ->  TouchActivity--dispatchTouchEvent--ACTION_UP
     *          ->  MyViewGroup--dispatchTouchEvent--ACTION_UP
     *          ->  MyViewGroup--onInterceptTouchEvent--ACTION_UP
     *          ->  MyViewGroup--onInterceptTouchEvent--isConsume -- false
     *          ->  MyView--dispatchTouchEvent--ACTION_UP
     *          ->  MyView -- onTouch --ACTION_UP-- true
     *          ->  MyViewGroup -- dispatchTouchEvent -- isConsume --true
     *          ->  TouchActivity -- dispatchTouchEvent -- isConsume --true
     *
     */

    /**
     *  全部返回 super. MyView的onTouch返回false
     *  事件分发顺序：TouchActivity--dispatchTouchEvent--ACTION_DOWN
     *          ->  MyViewGroup--dispatchTouchEvent--ACTION_DOWN
     *          ->  MyViewGroup--onInterceptTouchEvent--ACTION_DOWN
     *          ->  MyViewGroup--onInterceptTouchEvent--isConsume -- false
     *          ->  MyView--dispatchTouchEvent--ACTION_DOWN
     *          ->  MyView--dispatchTouchEvent--ACTION_DOWN
     *          ->  MyView -- onTouch --ACTION_DOWN
     *          ->  MyView -- onTouch -- isConsume --  false
     *          ->  MyView -- onTouchEvent -----------
     *          ->  MyView -- onTouchEvent -- ACTION_DOWN
     *          ->  MyView -- onTouchEvent -- isConsume -- false
     *          ->  MyView -- dispatchTouchEvent -- isConsume -- false
     *          ->  MyViewGroup -- onTouchEvent -----------
     *          ->  MyViewGroup -- onTouchEvent -- ACTION_DOWN
     *          ->  MyViewGroup -- onTouchEvent -- isConsume -- false
     *          ->  MyViewGroup -- dispatchTouchEvent--isConsume -- false
     *          ->  TouchActivity -- onTouchEvent -----------
     *          ->  TouchActivity -- onTouchEvent -- ACTION_DOWN
     *          ->  TouchActivity -- onTouchEvent -- isConsume -- false
     *          ->  TouchActivity -- dispatchTouchEvent -- isConsume -- false
     *          ->  TouchActivity -- dispatchTouchEvent -----------
     *          ->  TouchActivity--dispatchTouchEvent--ACTION_UP
     *          ->  TouchActivity -- onTouchEvent -----------
     *          ->  TouchActivity--onTouchEvent--ACTION_UP
     *          ->  TouchActivity -- onTouchEvent -- isConsume -- false
     *          ->  TouchActivity -- dispatchTouchEvent -- isConsume --false
     *
     */

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        Log.i(TAG,"TouchActivity -- dispatchTouchEvent ----------- ");
        switch (ev.getAction()){
            case MotionEvent.ACTION_DOWN:
                Log.i(TouchActivity.TAG,"TouchActivity -- dispatchTouchEvent -- ACTION_DOWN ");
                break;
            case MotionEvent.ACTION_UP:
                Log.i(TouchActivity.TAG,"TouchActivity -- dispatchTouchEvent -- ACTION_UP ");
                break;
            case MotionEvent.ACTION_MOVE:
                Log.i(TouchActivity.TAG,"TouchActivity -- dispatchTouchEvent -- ACTION_MOVE ");
                break;
            case MotionEvent.ACTION_CANCEL:
                Log.i(TouchActivity.TAG,"TouchActivity -- dispatchTouchEvent -- ACTION_CANCEL ");
                break;

        }

        boolean isConsume = super.dispatchTouchEvent(ev);
        Log.i(TouchActivity.TAG,"TouchActivity -- dispatchTouchEvent -- isConsume -- "+isConsume);
        return isConsume;
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.i(TAG,"TouchActivity -- onTouchEvent ----------- ");
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                Log.i(TouchActivity.TAG,"TouchActivity -- onTouchEvent -- ACTION_DOWN ");
                break;
            case MotionEvent.ACTION_UP:
                Log.i(TouchActivity.TAG,"TouchActivity -- onTouchEvent -- ACTION_UP ");
                break;
            case MotionEvent.ACTION_MOVE:
                Log.i(TouchActivity.TAG,"TouchActivity -- onTouchEvent -- ACTION_MOVE ");
                break;
            case MotionEvent.ACTION_CANCEL:
                Log.i(TouchActivity.TAG,"TouchActivity -- onTouchEvent -- ACTION_CANCEL ");
                break;

        }
        boolean isConsume = super.onTouchEvent(event);
        Log.i(TouchActivity.TAG,"TouchActivity -- onTouchEvent -- isConsume -- "+isConsume);
        return isConsume;


    }
}
