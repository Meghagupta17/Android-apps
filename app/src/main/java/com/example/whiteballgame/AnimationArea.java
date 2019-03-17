package com.example.whiteballgame;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class AnimationArea extends View{

    public interface ChangeLifeScore{
        public void changeLife();

        public void changeScore(int score);
    }

    private Paint paint = new Paint();
    List<WhiteBalls> whiteBallsList;
    Balls blackBall = new Balls();
    private long startTime=0;
    private long endTime=0;
    Context refContext;
    Boolean flagPause;
    double distance;
    Boolean flagStartState;
    ChangeLifeScore changeLifeScore;

    public AnimationArea(Context context) {
        super(context);
        refContext= context;
        flagPause =true; // should be true, false done for testing purpose only
        flagStartState = false; // it should be false, true only for testing purpose
        changeLifeScore = (ChangeLifeScore) context;
        startTime = 0;
        endTime = 0;
        whiteBallsList = new ArrayList<>();
    }

    public AnimationArea(Context context, AttributeSet attrs){
        super(context, attrs);
        refContext=context;
        flagPause =true; // should be true, false done for testing purpose only
        flagStartState = false;// it should be false, true only for testing purpose
        changeLifeScore = (ChangeLifeScore) context;
        startTime = 0;
        endTime = 0;
        whiteBallsList = new ArrayList<>();
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
                changeLifeScore.changeScore(whiteBall.score);
            }
            canvas.drawCircle(whiteBall.x, whiteBall.y, whiteBall.radius, paint);

            distance = Math.sqrt((whiteBall.x-blackBall.x)*(whiteBall.x-blackBall.x) + (whiteBall.y-blackBall.y)*(whiteBall.y-blackBall.y));
            if (distance<whiteBall.radius+blackBall.radius){
                whiteBall.y = whiteBall.radius;
                changeLifeScore.changeLife();
            }
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
                startTime = System.currentTimeMillis();

                if(flagStartState){
                    int x = (int) event.getX();
                    if (x<this.getWidth()/2){
                        blackBall.x=blackBall.x-11;
                    }else{
                        blackBall.x=blackBall.x+11;
                    }

                }else {
                    WhiteBalls whiteBalls =new WhiteBalls();
                    whiteBalls.x = (int) event.getX();
                    whiteBalls.y = (int) event.getY();;
                    whiteBalls.radius = 50;//(int) ((endTime-startTime)/50);
                    whiteBalls.speed= 4;
                    whiteBalls.score=1;
                    whiteBallsList.add(whiteBalls);
                }

            case MotionEvent.ACTION_MOVE:
            case MotionEvent.ACTION_UP:
                endTime = System.currentTimeMillis();
            case MotionEvent.ACTION_CANCEL: {
                break;
            }
        }
        startTime = 0;
        endTime = 0;
        return true;
    }

    public void pauseAnimation(){
        flagPause = true;
    }

    public void startAnimation(){
        flagPause = false;
    }

    public void newGame(){
        whiteBallsList = new ArrayList<WhiteBalls>();
        initializedBlackBall();
        flagStartState =false;
        flagPause = true;
    }

    public void changeStartState(){
        flagStartState = true;
    }
}
