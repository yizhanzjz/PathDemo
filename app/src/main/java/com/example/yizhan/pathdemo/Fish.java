package com.example.yizhan.pathdemo;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * 太极的阴阳鱼
 * Created by yizhan on 2017/10/17.
 */

public class Fish extends View {

    private final static int RADIUS = 150;

    private int mCenterX = 0;
    private int mCenterY = 0;
    private Paint mPaint;

    public Fish(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        init();
    }

    private void init() {
        mPaint = new Paint();
        mPaint.setColor(Color.BLACK);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        mCenterX = w / 2;
        mCenterY = h / 2;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.translate(mCenterX, mCenterY);

        mPaint.setStyle(Paint.Style.FILL);

        // 1. 左
        Path path1 = new Path();
        path1.addCircle(0, 0, RADIUS, Path.Direction.CW);

        // 2. 左
        Path path2 = new Path();
        path2.addRect(0, -RADIUS, RADIUS, RADIUS, Path.Direction.CW);

        // 3. 左
        Path path3 = new Path();
        path3.addCircle(0, -RADIUS / 2, RADIUS / 2, Path.Direction.CW);

        // 4. 左
        Path path4 = new Path();
        path4.addCircle(0, RADIUS / 2, RADIUS / 2, Path.Direction.CW);

        // 5. 左
        Path path5 = new Path();
        path5.addCircle(0, -RADIUS / 2, RADIUS / 6, Path.Direction.CW);

        path1.op(path2, Path.Op.DIFFERENCE);//差集出圆的左半部分
        path1.op(path3, Path.Op.UNION);
        path1.op(path4, Path.Op.DIFFERENCE);
        path1.op(path5, Path.Op.DIFFERENCE);

        canvas.drawPath(path1, mPaint);//画左边的太极鱼

        Path pathR = new Path();
        path1.addCircle(0, 0, RADIUS, Path.Direction.CW);


        //画圆
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(1);
        canvas.drawCircle(0, 0, RADIUS, mPaint);

        //画下面的黑色圆
        mPaint.setStyle(Paint.Style.FILL);
        canvas.drawCircle(0, RADIUS / 2, RADIUS / 6, mPaint);
    }

}
