package com.letmecook.letmecook.myapplication.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Author by wangze, Date on 2020/1/4.
 */

public class RectView extends View {
    public RectView(Context context) {
        super(context);
    }

    public RectView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public RectView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public RectView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

/*       Rect rect =new  Rect(100,200,300,400);
        Paint paint = new Paint();
        paint.setColor(Color.CYAN);
        canvas.drawRect(rect,paint);*/


        Path path = new Path();
        path.moveTo(100,200);
        path.lineTo(300,400);
     /*   path.moveTo(10,10);
        path.lineTo(10,100);
        path.lineTo(300,100);*/
        path.close();

        Paint linePaint = new Paint();
        linePaint.setColor(Color.BLACK);
        linePaint.setStyle(Paint.Style.FILL_AND_STROKE);//对文字和几何图形有效
        linePaint.setStrokeWidth(5);

        canvas.drawPath(path,linePaint);
    }
}
