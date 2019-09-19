package com.mackwu.viewcustom.draw

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View

/**
 * ================================================
 * Created by MackWu on 2019/9/17 11:36
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ================================================
 */
class MyView: View {

    private val paint = Paint()

    constructor(context: Context): this(context, null)

    constructor(context: Context, attributeSet: AttributeSet?): this(context, attributeSet, 0)

    constructor(context: Context, attributeSet: AttributeSet?, defStyleAttr: Int): super(context, attributeSet, defStyleAttr){
        init()
    }

    private fun init() {
        // 抗锯齿功能
        paint.isAntiAlias = true
        // 设置画笔颜色
        paint.color = Color.RED
        // 设置填充样式。填充内部Style.FILL、 填充内部和描边Style.FILL_AND_STROKE、 仅描边Style.STROKE
        paint.style = Paint.Style.FILL
        // 设置画笔宽度
        paint.strokeWidth = 5f
        // 设置阴影。radius:阴影的倾斜度 dx:水平位移 dy:垂直位移
        paint.setShadowLayer(10f, 15f, 15f, Color.GREEN)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        // 设置画布背景颜色
//        canvas.drawRGB(255, 255, 255)
        canvas.drawColor(Color.WHITE)

        // 画圆
        canvas.drawCircle(190f, 200f, 150f, paint)
    }
}