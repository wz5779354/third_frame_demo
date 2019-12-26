package com.letmecook.letmecook.myapplication.view;

import android.content.Context;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.letmecook.letmecook.myapplication.TouchActivity;

/**
 * Author by wangze, Date on 2019/12/17.
 */

public class MyView extends View {
    public MyView(Context context) {
        super(context);
        init();
    }

    public MyView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MyView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public MyView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        Log.i(TouchActivity.TAG,"MyView -- dispatchTouchEvent ----------- ");
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
//                getParent().requestDisallowInterceptTouchEvent(true);
                Log.i(TouchActivity.TAG,"MyView -- dispatchTouchEvent -- ACTION_DOWN ");
                break;
            case MotionEvent.ACTION_UP:
                Log.i(TouchActivity.TAG,"MyView -- dispatchTouchEvent -- ACTION_UP ");
                break;
            case MotionEvent.ACTION_MOVE:
                Log.i(TouchActivity.TAG,"MyView -- dispatchTouchEvent -- ACTION_MOVE ");
                break;
            case MotionEvent.ACTION_CANCEL:
                Log.i(TouchActivity.TAG,"MyView -- dispatchTouchEvent -- ACTION_CANCEL ");
                break;

        }
        boolean isConsume = super.dispatchTouchEvent(event);
        Log.i(TouchActivity.TAG,"MyView -- dispatchTouchEvent -- isConsume -- "+isConsume);
        return isConsume;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.i(TouchActivity.TAG,"MyView -- onTouchEvent ----------- ");
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                Log.i(TouchActivity.TAG,"MyView -- onTouchEvent -- ACTION_DOWN ");
                break;
            case MotionEvent.ACTION_UP:
                Log.i(TouchActivity.TAG,"MyView -- onTouchEvent -- ACTION_UP ");
                ((View)getParent()).onTouchEvent(event);
                break;
            case MotionEvent.ACTION_MOVE:
                Log.i(TouchActivity.TAG,"MyView -- onTouchEvent -- ACTION_MOVE ");
                break;
            case MotionEvent.ACTION_CANCEL:
                Log.i(TouchActivity.TAG,"MyView -- onTouchEvent -- ACTION_CANCEL ");
                break;

        }
        boolean isConsume = super.onTouchEvent(event);
        Log.i(TouchActivity.TAG,"MyView -- onTouchEvent -- isConsume -- "+isConsume);
        return isConsume;
    }
     public void init(){

       /* setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
//                Log.i(TouchActivity.TAG,"MyView -- onTouch ----------- ");
                switch (event.getAction()){
                    case MotionEvent.ACTION_DOWN:
                        Log.i(TouchActivity.TAG,"MyView -- onTouch -- ACTION_DOWN ");
                        break;
                    case MotionEvent.ACTION_UP:
                        Log.i(TouchActivity.TAG,"MyView -- onTouch -- ACTION_UP ");
                        break;
                    case MotionEvent.ACTION_MOVE:
                        Log.i(TouchActivity.TAG,"MyView -- onTouch -- ACTION_MOVE ");
                        break;
                    case MotionEvent.ACTION_CANCEL:
                        Log.i(TouchActivity.TAG,"MyView -- onTouch -- ACTION_CANCEL ");
                        break;

                }
                boolean isConsume = false;
                Log.i(TouchActivity.TAG,"MyView -- onTouch -- isConsume --  "+isConsume);
                return isConsume;
            }
        });*/

       setOnClickListener(new OnClickListener() {
           @Override
           public void onClick(View v) {
               Log.i(TouchActivity.TAG,"MyView -- onClick ------- ");
           }
       });

     }

}
