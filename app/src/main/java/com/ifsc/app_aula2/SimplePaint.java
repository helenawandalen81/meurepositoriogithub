package com.ifsc.app_aula2;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;

public class SimplePaint extends View {
    Path mpath;
    Paint mpaint;
    ArrayList<Paint> paintList;
    ArrayList<Path> pathList;

    public SimplePaint(Context context, AttributeSet attrs) {
        super(context, attrs);
        mpath = new Path();
        mpaint = new Paint();
        mpaint.setStrokeWidth(5);
        mpaint.setColor(0xFF000000);
        mpaint.setStyle(Paint.Style.STROKE);
        mpaint.setAntiAlias(true);
        pathList = new ArrayList<>();
        paintList = new ArrayList<>();
    }

    @Override
    protected void onDraw(Canvas canvas){
        super.onDraw(canvas);
        for (int i=0;i<paintList.size(); i++){
            canvas.drawPath(pathList.get(i), paintList.get(i));
        }
        canvas.drawPath(mpath,mpaint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                mpath.moveTo(x,y);
                return true;
            case MotionEvent.ACTION_MOVE:
                mpath.lineTo(x,y);
                break;
            case MotionEvent.ACTION_UP:
                break;
            default:
                return false;
        }

        invalidate();
        return true;
    }
    public void setColor(int color){
        //criar nova camada de path  paint
        paintList.add(mpaint);
        pathList.add(mpath);
        mpaint = new Paint(mpaint);
        mpath = new Path();
        mpaint.setColor(color);
    }
}
