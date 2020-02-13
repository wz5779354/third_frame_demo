package com.letmecook.letmecook.myapplication.view;

import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.FrameLayout;

import com.letmecook.letmecook.myapplication.TouchActivity;

import androidx.annotation.RequiresApi;


/**
 * Author by wangze, Date on 2019/12/17.
 */

public class MyViewGroup extends FrameLayout {
    public MyViewGroup(Context context) {
        super(context);
        init();
    }

    public MyViewGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MyViewGroup(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public MyViewGroup(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }


    public void init(){
//        requestDisallowInterceptTouchEvent(true);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
            Log.i(TouchActivity.TAG,"MyViewGroup -- dispatchTouchEvent ----------- ");
        switch (ev.getAction()){
            case MotionEvent.ACTION_DOWN:
                Log.i(TouchActivity.TAG,"MyViewGroup -- dispatchTouchEvent -- ACTION_DOWN ");
                break;
            case MotionEvent.ACTION_UP:
                Log.i(TouchActivity.TAG,"MyViewGroup -- dispatchTouchEvent -- ACTION_UP ");
                break;
            case MotionEvent.ACTION_MOVE:
                Log.i(TouchActivity.TAG,"MyViewGroup -- dispatchTouchEvent -- ACTION_MOVE ");
                break;
            case MotionEvent.ACTION_CANCEL:
                Log.i(TouchActivity.TAG,"MyViewGroup -- dispatchTouchEvent -- ACTION_CANCEL ");
                break;

        }

        boolean isConsume = super.dispatchTouchEvent(ev);
        Log.i(TouchActivity.TAG,"MyViewGroup -- dispatchTouchEvent -- isConsume -- "+isConsume);
        return isConsume;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        Log.i(TouchActivity.TAG,"MyViewGroup -- onInterceptTouchEvent ----------- ");
        switch (ev.getAction()){
            case MotionEvent.ACTION_DOWN:
                Log.i(TouchActivity.TAG,"MyViewGroup -- onInterceptTouchEvent -- ACTION_DOWN ");
                break;
            case MotionEvent.ACTION_UP:
                Log.i(TouchActivity.TAG,"MyViewGroup -- onInterceptTouchEvent -- ACTION_UP ");
                break;
            case MotionEvent.ACTION_MOVE:
                Log.i(TouchActivity.TAG,"MyViewGroup -- onInterceptTouchEvent -- ACTION_MOVE ");
                break;
            case MotionEvent.ACTION_CANCEL:
                Log.i(TouchActivity.TAG,"MyViewGroup -- onInterceptTouchEvent -- ACTION_CANCEL ");
                break;

        }
        boolean isConsume = super.onInterceptTouchEvent(ev);
        Log.i(TouchActivity.TAG,"MyViewGroup -- onInterceptTouchEvent -- isConsume -- "+isConsume);
        return isConsume;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.i(TouchActivity.TAG,"MyViewGroup -- onTouchEvent ----------- ");

        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                Log.i(TouchActivity.TAG,"MyViewGroup -- onTouchEvent -- ACTION_DOWN ");
                break;
            case MotionEvent.ACTION_UP:
                Log.i(TouchActivity.TAG,"MyViewGroup -- onTouchEvent -- ACTION_UP ");
                break;
            case MotionEvent.ACTION_MOVE:
                Log.i(TouchActivity.TAG,"MyViewGroup -- onTouchEvent -- ACTION_MOVE ");
                break;
            case MotionEvent.ACTION_CANCEL:
                Log.i(TouchActivity.TAG,"MyViewGroup -- onTouchEvent -- ACTION_CANCEL ");
                break;

        }

        boolean isConsume = super.onTouchEvent(event);
        Log.i(TouchActivity.TAG,"MyViewGroup -- onTouchEvent -- isConsume -- "+isConsume);
        return isConsume;
    }
}
