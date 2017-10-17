package com.example.yizhan.pathdemo;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * 主要就是使用贝赛尔曲线的形式画出一个圆，控制点的坐标是查资料所得 4 / 3 * tan(pi / (2 * n)) * r
 * 其中n为 将圆分成几段三阶贝赛尔曲线来画，r为半径
 * Created by yizhan on 2017/10/17.
 */

public class Circle extends View {

    private final static float RADIUS = 100;
    private final static float CNT = 4;
    private float mCenterX = 0;
    private float mCenterY = 0;

    private Paint mPaint;

    // 这里需要注意的一点是，
    private float cLength = (float) (4 * RADIUS * Math.tan(Math.PI / (2 * CNT)) / 3);
    private double eachRad = 2 * Math.PI / CNT;

//    private float cLength = RADIUS * 0.552284749831f;

    public Circle(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {

        mPaint = new Paint();
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(4);
        mPaint.setColor(Color.RED);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        mCenterX = w * 1.0f / 2;
        mCenterY = h * 1.0f / 2;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.translate(mCenterX, mCenterY);

        for (int i = 0; i < CNT; i++) {

            //起始数据点
            PointF startPoint = new PointF(RADIUS, 0);
//            PointF startPoint = new PointF(RADIUS, 0);

            //终止数据点
            float endPointX = (float) (RADIUS * Math.cos(eachRad));
            float endPointY = (float) (RADIUS * Math.sin(eachRad));
            PointF endPoint = new PointF(endPointX, endPointY);
//            PointF endPoint = new PointF(0, RADIUS);

            //控制点1
            PointF controlOnePoint = new PointF(RADIUS, cLength);

            //控制点2
            float controlTwoPointX = (float) (RADIUS * Math.cos(eachRad) + cLength * Math.sin(eachRad));
            float controlTwoPointY = (float) (RADIUS * Math.sin(eachRad) - cLength * Math.cos(eachRad));
            PointF controlTwoPoint = new PointF(controlTwoPointX, controlTwoPointY);
//            PointF controlTwoPoint = new PointF(cLength, RADIUS);

            Path path = new Path();
            path.moveTo(startPoint.x, startPoint.y);
            path.cubicTo(controlOnePoint.x, controlOnePoint.y, controlTwoPoint.x, controlTwoPoint.y, endPoint.x, endPoint.y);
            canvas.drawPath(path, mPaint);

            //弧度转换成角度
            canvas.rotate((float) (eachRad * 180 / Math.PI));
//            canvas.rotate(90);
        }
    }
}
