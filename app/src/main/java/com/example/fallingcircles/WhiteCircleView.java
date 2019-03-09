package com.example.fallingcircles;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import java.util.ArrayList;
import java.util.List;

public class WhiteCircleView extends View {
    private Paint paint = new Paint();
    List<Point> points = new ArrayList<Point>();

    public WhiteCircleView(Context context) {
        super(context);
    }

   public WhiteCircleView(Context context, AttributeSet attributeSet){
        super(context, attributeSet);
   }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        paint.setColor(Color.WHITE);
        for (Point p : points) {
            canvas.drawCircle(p.x, p.y, 30, paint);
        }
        invalidate();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                Point p = new Point();
                p.x = (int) event.getX();
                p.y = (int) event.getY();
                points.add(p);
                invalidate();
            case MotionEvent.ACTION_MOVE:  // a pointer was moved
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL: {
                break;
            }
        }
        invalidate();
        return true;

    }

}

    /*private final SurfaceHolder surfaceHolder;
    private final Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);

    public CircleView(Context context) {
        super(context);
        surfaceHolder = getHolder();
    }
    @Override
    protected void onDraw(Canvas canvas){
        super.onDraw(canvas);
        int x = getWidth();
        int y = getHeight();
        int radius;
        radius=100;
        Paint paint = new Paint();
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.WHITE);
        canvas.drawPaint(paint);
        //paint.setColor(Color.parseColor("WHITE"));
        paint.setStrokeWidth(5f);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(event.getAction() == MotionEvent.ACTION_DOWN) {
            if (surfaceHolder.getSurface().isValid()) {
                Canvas canvas = surfaceHolder.lockCanvas();
                canvas.drawColor(Color.WHITE);
                canvas.drawCircle(event.getX(), event.getY(), 50, paint);
                surfaceHolder.unlockCanvasAndPost(canvas);
            }
        }
        return false;
    }
}
*/
