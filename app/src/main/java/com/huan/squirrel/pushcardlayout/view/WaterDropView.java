package com.huan.squirrel.pushcardlayout.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Squirrel桓 on 2018/8/30.
 */
public class WaterDropView extends View {

    public WaterDropView(Context context) {
        super(context);
    }

    public WaterDropView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public WaterDropView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private int center_x, center_y, mwidth, width, height;

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        width = w;
        height = h;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //绘制背景
        drawBackground(canvas);
    }

    private void drawBackground(Canvas canvas) {

        //绘制圆
        Paint p = new Paint();
        p.setColor(Color.BLUE);
        //p.setColor(Color.BLACK);//p.setARGB(200, 255, 215, 0);//设置封闭路径的填充色为金
        p.setAntiAlias(true);// 设置画笔的锯齿效果。 true是去除，大家一看效果就明白了
        //RectF oval = new RectF(150, 200, 500, 550);// 画一个椭圆
        // p.setStrokeWidth(2);

        //p.setStyle(Paint.Style.STROKE);
        Path path2 = new Path();
        path2.moveTo(0, 0);//设置Path的起点

        if (percent < .66) {//下拉阶段

            path2.lineTo(0, height * (1 - percent));
            /**
             * 参数1、2：x1，y1为控制点的坐标值
             * 参数3、4：x2，y2为终点的坐标值
             */
            path2.quadTo(width / 2, height * (1 - percent) + height* percent, width, height * (1 - percent));//设置贝塞尔曲线的控制点坐标和终点坐标
            path2.lineTo(width, 0);
            path2.close();
            //path2.lineTo(0,0);
            canvas.drawPath(path2, p);//画出贝塞尔曲线}
        }

        if (percent > .60&&percent<80) {//分离阶段
            int drop_width = 40;
            path2.lineTo(0, height * (1 - percent));
            path2.quadTo(width / 2, height * (1 - percent) + height  * percent, width, height * (1 - percent));//设置贝塞尔曲线的控制点坐标和终点坐标
            path2.lineTo(width, 0);
            path2.close();
            //path2.lineTo(0,0);
            canvas.drawPath(path2, p);//画出贝塞尔曲线}
        }
        if (percent > .80) {//独立阶段

        }
    }

    private float percent = 0;

    public float getPercent() {
        return percent;
    }

    public void setPercent(float percent) {
        this.percent = percent;
        postInvalidate();
    }
}
