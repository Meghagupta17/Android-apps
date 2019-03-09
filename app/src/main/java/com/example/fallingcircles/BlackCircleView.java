package com.example.fallingcircles;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class BlackCircleView extends View {
    private float dX, dY;

    public BlackCircleView(Context context) {
        super(context);
    }

    public BlackCircleView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }
    public BlackCircleView(Context context, AttributeSet attrs, int defStyle){
        super(context, attrs, defStyle);
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint paint = new Paint();
        paint.setColor(Color.BLACK);
        int x =(this.getWidth()/2);
        int y = (this.getHeight()/2);
        int radius;

        canvas.drawCircle(x, y, 50, paint);
        invalidate();
    }

    public boolean onTouch(View view, MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                dX = view.getX() - event.getRawX();
                dY = view.getY() - event.getRawY();
                break;
            case MotionEvent.ACTION_MOVE:
                view.animate()
                        .x(event.getRawX() + dX)
                        .y(event.getRawY() + dY)
                        .setDuration(0)
                        .start();
                break;
            default:
                return false;
        }
        return true;
    }
}
    /*private void move() {
        if (ScriptGroup.Input.touchCount > 0)
        {
            Touch touch = ScriptGroup.Input.GetTouch(0);
            if (touch.position.x > (Screen.width / 2))
            {
                GoRight();
            }
            if (touch.position.x < (Screen.width / 2))
            {
                GoLeft();
            }
        }*/



   /* @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawColor(Color.WHITE
        );
        canvas.drawCircle(200, 200, 100, paint);
    }/*

}


    /*private Paint paint = new Paint();

    public BlackCircleView(Context context) {
        super(context);
    }

    public BlackCircleView(Context context, AttributeSet attributeSet){
        super(context, attributeSet);
    }

    @Override
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int x = getWidth();
        int y = getHeight();
        int radius;
        radius = 100;
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.WHITE);

        canvas.drawPaint(paint);
        // Use Color.parseColor to define HTML colors
        //paint.setColor(Color.parseColor("#CD5C5C"));
        canvas.drawCircle(x, y, 100, paint);

    }
}*/
