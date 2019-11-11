package com.mackwu.view

import android.graphics.Color
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.SpannableString
import android.text.Spanned
import android.text.style.BackgroundColorSpan
import android.text.style.ForegroundColorSpan
import com.mackwu.view.R
import kotlinx.android.synthetic.main.base_activity_span.*

/**
 * ================================================
 * Created by MackWu on 2019/9/6 14:29
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ================================================
 *
 * SpannableString
 * SpannableString可以在字符序列基础上对指定的字符进行润饰，在开发中，TextView可以通过setText(CharSequence)传入SpannableString作为参数，
 * 来达到显示不同样式文字的效果
 *
 * <h2>setSpan</h2>
 * 样式通过SpannableString的setSpan(Object what, int start, int end, int flags)方法来进行设置
 * what：对SpannableString进行润色的各种Span；
 * int：需要润色文字段开始的下标
 * end：需要润色文字段结束的下标
 * flags：决定开始和结束下标是否包含的标志位，有四个参数可选
 *  SPAN_INCLUSIVE_EXCLUSIVE：包括开始下标，但不包括结束下标。一般用这个
 *  SPAN_EXCLUSIVE_INCLUSIVE：不包括开始下标，但包括结束下标
 *  SPAN_INCLUSIVE_INCLUSIVE：既包括开始下标，又包括结束下标
 *  SPAN_EXCLUSIVE_EXCLUSIVE：不包括开始下标，也不包括结束下标
 *
 * <h2>Span类型</h2>
 * ForegroundColorSpan: 字体样式
 * BackgroundColorSpan: 背景样式
 * ClickableSpan
 * MaskFilterSpan
 */
class SpanActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.base_activity_span)
        testBackgroundColorSpan()
    }

    private fun testForegroundColorSpan(){
        // ForegroundColorSpan。Hello变成蓝色
        val spannableString = SpannableString("Hello World!")
        val span = ForegroundColorSpan(Color.BLUE)
        spannableString.setSpan(span, 0, 5, Spanned.SPAN_INCLUSIVE_EXCLUSIVE)
        tv_test.text = spannableString
    }

    private fun testBackgroundColorSpan(){
        // ForegroundColorSpan。Hello背景变成蓝色
        val spannableString = SpannableString("Hello World!")
        val span = BackgroundColorSpan(Color.BLUE)
        spannableString.setSpan(span, 0, 5, Spanned.SPAN_INCLUSIVE_EXCLUSIVE)
        tv_test.text = spannableString
    }
}