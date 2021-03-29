package com.mackwu.component.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.BatteryManager;
import android.util.AttributeSet;
import android.view.View;

import androidx.lifecycle.LifecycleOwner;

import com.mackwu.base.util.LogUtil;
import com.mackwu.component.R;
import com.mackwu.component.ui.livedata.BatteryChangeLiveData;


/**
 * ===================================================
 * Created by MackWu on 2021/6/19 17:01
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 */
public class BatteryView extends View {

    private final Context context;
    // 电池边框颜色
    @SuppressWarnings("FieldCanBeLocal")
    private int batteryBorderColor;
    // 电池电量。0-100
    private int batteryPowerLevel = 100;
    // 电量平常颜色
    private int batteryPowerNormalColor;
    // 低电量时颜色
    private int batteryPowerLowColor;
    // 电量充电中颜色
    private int batteryPowerChargingColor;
    // 电池边框画笔
    private Paint batteryBorderPaint;
    private Paint batteryHeaderPaint;
    // 电池电量画笔
    private Paint batteryPowerPaint;
    private int width;
    private int height;

    public BatteryView(Context context) {
        this(context, null);
    }

    public BatteryView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BatteryView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.context = context;
        initView(attrs);
        initData();
    }

    private void initView(AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.BatteryView);
        batteryBorderColor = typedArray.getColor(R.styleable.BatteryView_batteryBorderColor, Color.WHITE);
        batteryPowerLevel = typedArray.getColor(R.styleable.BatteryView_batteryPowerLevel, 50);
        batteryPowerNormalColor = typedArray.getInt(R.styleable.BatteryView_batteryPowerNormalColor, Color.WHITE);
        batteryPowerLowColor = typedArray.getInt(R.styleable.BatteryView_batteryPowerLowColor, Color.RED);
        batteryPowerChargingColor = typedArray.getInt(R.styleable.BatteryView_batteryPowerChargingColor, Color.GREEN);
        typedArray.recycle();

        // batteryBorderPaint
        batteryBorderPaint = new Paint();
        batteryBorderPaint.setColor(batteryBorderColor);
        batteryBorderPaint.setStyle(Paint.Style.STROKE);

        // batteryHeaderPaint
        batteryHeaderPaint = new Paint();
        batteryHeaderPaint.setColor(batteryBorderColor);
        batteryHeaderPaint.setStyle(Paint.Style.FILL);

        // batteryPowerPaint
        batteryPowerPaint = new Paint();
        batteryPowerPaint.setColor(batteryPowerNormalColor);
        batteryPowerPaint.setStyle(Paint.Style.FILL);
    }

    private void initData() {
        // BatteryChangeLiveData
        if (context instanceof LifecycleOwner) {
            BatteryChangeLiveData.getInstance(context).observe((LifecycleOwner) context, batteryInfo -> {
                int level = batteryInfo.getLevel();
                int status = batteryInfo.getStatus();
                setPowerLevel(level, status);
            });
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        width = getMeasuredWidth();
        height = getMeasuredHeight();
        LogUtil.d("width: " + width + ", height: " + height);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawHorizontalBattery(canvas);
    }

    /**
     * 绘制水平电池
     */
    @SuppressWarnings("SuspiciousNameCombination")
    private void drawHorizontalBattery(Canvas canvas) {
        float strokeWidth = width / 30.f;
        float radius = 1.8f;
        float innerOffset = 3f;

        // 画电池边框
        batteryBorderPaint.setStrokeWidth(strokeWidth);
        RectF r1 = new RectF(strokeWidth / 2, strokeWidth / 2, width - strokeWidth * 2, height - strokeWidth / 2);
        canvas.drawRoundRect(r1, radius, radius, batteryBorderPaint);

        // 画电池头
        batteryHeaderPaint.setStrokeWidth(strokeWidth);
        RectF r2 = new RectF(width - strokeWidth * 2, height * 0.35f, width + strokeWidth, height * 0.65f);
        canvas.drawRect(r2, batteryHeaderPaint);

        // 画电池电量
        strokeWidth = strokeWidth + innerOffset;
        float offset = (width - strokeWidth * 2) * batteryPowerLevel / 100.f;
        RectF r3 = new RectF(strokeWidth, strokeWidth, offset + innerOffset / 1.5f, height - strokeWidth);
        LogUtil.d(r3.toString());
        canvas.drawRoundRect(r3, radius, 0, batteryPowerPaint);
    }

    /**
     * 设置电池电量
     */
    public void setPowerLevel(int level, int status) {
        batteryPowerLevel = level;
        // 根据电池电量设置电池颜色
        boolean isCharging = status == BatteryManager.BATTERY_STATUS_CHARGING || status == BatteryManager.BATTERY_STATUS_FULL;
        if (isCharging) {
            // 充电中
            batteryPowerPaint.setColor(batteryPowerChargingColor);
        } else {
            // 非充电中
            if (level <= 10) {
                batteryPowerPaint.setColor(batteryPowerLowColor);
            } else {
                batteryPowerPaint.setColor(batteryPowerNormalColor);
            }
        }
        invalidate();
    }

}