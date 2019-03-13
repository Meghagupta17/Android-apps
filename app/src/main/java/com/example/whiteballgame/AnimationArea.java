package com.example.whiteballgame;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import java.util.ArrayList;
import java.util.List;

public class AnimationArea extends View {

    private Paint paint = new Paint();
    List<WhiteBalls> whiteBallsList = new ArrayList<WhiteBalls>();
    Balls blackBall = new Balls();

    public AnimationArea(Context context) {
        super(context);
    }

    public AnimationArea(Context context, AttributeSet attrs){
        super(context, attrs);
    }

    @Override
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        paint.setColor(Color.RED);
        for (WhiteBalls p : whiteBallsList) {
            if(p.y<canvas.getHeight()){
                p.y=p.y+2;
            }else{
                p.y = 0;
            }
            canvas.drawCircle(p.x, p.y, 30, paint);
        }
        invalidate();

    }
}
