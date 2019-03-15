package com.example.whiteballgame;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class AnimationArea extends View {

    private Paint paint = new Paint();
    List<WhiteBalls> whiteBallsList = new ArrayList<WhiteBalls>();
    Balls blackBall = new Balls();
    private long startTime=0;
    private long endTime=0;
    Context refContext;
    Boolean flagPause;

    public AnimationArea(Context context) {
        super(context);
        refContext= context;
        flagPause =true;

    }

    public AnimationArea(Context context, AttributeSet attrs){
        super(context, attrs);
        refContext=context;
        flagPause =true;
    }

    @Override
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawARGB(100, 0, 0, 0);
        paint.setColor(Color.BLACK);
        initializedBlackBall();
        //canvas.translate(getWidth()/2f,getHeight()/2f);
        canvas.drawCircle(blackBall.x, blackBall.y, blackBall.radius, paint);
        paint.setColor(Color.WHITE);
        for (WhiteBalls whiteBall : whiteBallsList) {
            if(whiteBall.y<canvas.getHeight()){
                if(!flagPause)
                    whiteBall.y=whiteBall.y+whiteBall.speed;
            }else{
                whiteBall.y = whiteBall.radius;
                whiteBall.speed =(int)(whiteBall.speed*1.25);
                whiteBall.score = whiteBall.score+1;
                //increment the score in main activity
            }
            canvas.drawCircle(whiteBall.x, whiteBall.y, whiteBall.radius, paint);
        }
        invalidate();
    }

    void initializedBlackBall(){
        blackBall.x = this.getWidth()/2;
        blackBall.y = this.getHeight()-70;
        blackBall.radius =50;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                WhiteBalls whiteBalls =new WhiteBalls();
                whiteBalls.x = (int) event.getX();
                whiteBalls.y = (int) event.getY();;
                whiteBalls.radius = 50;// (int) ((endTime-startTime)/50);
                whiteBalls.speed= 4;
                whiteBalls.score=1;
                whiteBallsList.add(whiteBalls);
                startTime = System.currentTimeMillis();

            case MotionEvent.ACTION_MOVE:// a pointer was moved
            case MotionEvent.ACTION_UP:
                startTime = System.currentTimeMillis();
            case MotionEvent.ACTION_CANCEL: {
                break;
            }
        }

        startTime = 0;
        endTime = 0;
        return true;

    }
}
