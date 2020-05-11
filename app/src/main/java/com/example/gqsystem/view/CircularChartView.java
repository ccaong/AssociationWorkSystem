package com.example.gqsystem.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import com.example.gqsystem.R;
import com.example.gqsystem.util.DensityUtils;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * @author : devel
 * @date : 2020/4/2 11:28
 * @desc : 环形统计图
 */
public class CircularChartView extends View {

    private Paint mPaint;
    private Paint mTextPaint;
    /**
     * 字体的大小
     */
    private float mTextSize = 23.5f;

    /**
     * 半径
     */
    private float radius = 80;
    /**
     * 中心点
     */
    private float centerX = 0f;
    private float centerY = 0f;
    /**
     * 开始角度
     */
    private float startAngel = -90f;

    /**
     * 每个扫过角度
     */
    private List<Float> sweepAngle = new ArrayList<>();

    private int[] mColors = {getResources().getColor(R.color.app_color_theme_1),
            getResources().getColor(R.color.app_color_theme_2), getResources().getColor(R.color.app_color_theme_3),
            getResources().getColor(R.color.app_color_theme_4), getResources().getColor(R.color.app_color_theme_5),
            getResources().getColor(R.color.app_color_theme_6), getResources().getColor(R.color.app_color_theme_7)};

    /**
     * 圆点的半径
     */
    private float mDotRadius = 6f;

    public CircularChartView(Context context) {
        super(context);
        init(context);
    }

    public CircularChartView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        // 圆环画笔
        mPaint = new Paint();
        // 消除锯齿
        mPaint.setAntiAlias(true);
        // 设置填充
        mPaint.setStyle(Paint.Style.FILL);
        // 绘制文本的画笔
        mTextPaint = new Paint();
        // 设置线和字体的宽度
        mTextPaint.setStrokeWidth(2);
        // 消除锯齿
        mTextPaint.setAntiAlias(true);
        // 设置字体大小
        mTextPaint.setTextSize(mTextSize);

        sweepAngle.add(1f);
    }

    /**
     * 更新数据
     * @param floats
     */
    public void updateChart(List<Float> floats) {
        sweepAngle = floats;
        postInvalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        centerX = getWidth() / 2f;
        // 获取圆心的y坐
        centerY = getHeight() / 2f;
        initArc(canvas);
        initCircle(canvas);
    }

    /**
     * 绘制扇形图
     */
    private void initArc(Canvas canvas) {
        // 矩形区域
        RectF rect = new RectF(centerX - DensityUtils.dp2px(radius),
                centerY - DensityUtils.dp2px(radius),
                centerX + DensityUtils.dp2px(radius),
                centerY + DensityUtils.dp2px(radius));
        mPaint.setAntiAlias(true);
        for (int i = 0; i < sweepAngle.size(); i++) {
            mPaint.setColor(mColors[i]);
            canvas.drawArc(rect, startAngel, 360 * sweepAngle.get(i), true, mPaint);
            // 获取进度圆弧的中心点
            float textX = (float) (centerX + DensityUtils.dp2px(radius) * Math.cos((360 * sweepAngle.get(i) / 2 + startAngel) * Math.PI / 180));
            float textY1 = (float) (centerY + DensityUtils.dp2px(radius) * Math.sin((360 * sweepAngle.get(i) / 2 + startAngel) * Math.PI / 180));
            // 设置画笔颜色
            mTextPaint.setColor(mColors[i]);
            drawText(canvas, getPer(sweepAngle.get(i)), textX, textY1, mTextPaint);
            startAngel += 360 * sweepAngle.get(i);
        }
    }

    /**
     * 中心圆
     */
    private void initCircle(Canvas canvas) {
        Paint mPaint = new Paint();
        mPaint.setColor(Color.parseColor("#ffffff"));
        canvas.drawCircle(centerX, centerY, DensityUtils.dp2px(55), mPaint);
    }


    private float mOffset1X = 20;
    private float mOffset1Y = 20;
    private float mOffset2X = 150;
    private float mOffset2Y = 20;
    private float mPointOffset = 10;

    /**
     * 绘制文字
     *
     * @param canvas 画布
     * @param string 绘制的内容
     * @param firstX 起始点X坐标
     * @param firstY 起始点Y坐标
     * @param paint  文字的画笔
     */
    private void drawText(Canvas canvas, String string, float firstX, float firstY, Paint paint) {
        float endX1 = 0;
        float endY1 = 0;
        float endX2 = 0;
        float endY2 = 0;
        float textX = 0;
        float textY = 0;
        //初始点位于第四象限
        if (firstX <= centerX && firstY <= centerY) {
            firstX = firstX - mPointOffset;
            firstY = firstY - mPointOffset;
            endX1 = firstX - mOffset1X;
            endY1 = firstY - mOffset1Y;
            endX2 = firstX - mOffset2X;
            endY2 = firstY - mOffset2Y;
            textX = endX2;
            textY = endY2 - 10;
        }
        //初始点位于第三象限
        if (firstX < centerX && firstY > centerY) {
            firstX = firstX - mPointOffset;
            firstY = firstY + mPointOffset;
            endX1 = firstX - mOffset1X;
            endY1 = firstY + mOffset1Y;
            endX2 = firstX - mOffset2X;
            endY2 = firstY + mOffset2Y;
            textX = endX2;
            textY = endY2 + 30;
        }
        //初始点位于第一象限
        if (firstX > centerX && firstY < centerY) {
            firstX = firstX + mPointOffset;
            firstY = firstY - mPointOffset;
            endX1 = firstX + mOffset1X;
            endY1 = firstY - mOffset1Y;
            endX2 = firstX + mOffset2X;
            endY2 = firstY - mOffset2Y;
            textX = endX1;
            textY = endY2 - 10;
        }
        //初始点位于第二象限
        if (firstX >= centerX && firstY >= centerY) {
            firstX = firstX + mPointOffset;
            firstY = firstY + mPointOffset;
            endX1 = firstX + mOffset1X;
            endY1 = firstY + mOffset1Y;
            endX2 = firstX + mOffset2X;
            endY2 = firstY + mOffset2Y;
            textX = endX1;
            textY = endY2 + 30;
        }
        canvas.drawCircle(firstX, firstY, mDotRadius, paint);
        canvas.drawLine(firstX, firstY, endX1, endY1, paint);
        canvas.drawLine(endX1, endY1, endX2, endY2, paint);
        canvas.drawText(string, textX, textY, paint);
    }


    /**
     * 计算百分比
     *
     * @return 返回比例
     */
    private String getPer(float now) {
        // 创建一个数值格式化对象
        NumberFormat numberFormat = NumberFormat.getInstance();
        // 设置精确到小数点后2位
        numberFormat.setMaximumFractionDigits(2);
        String result = numberFormat.format(now * 100);
        return result + "%";
    }
}
