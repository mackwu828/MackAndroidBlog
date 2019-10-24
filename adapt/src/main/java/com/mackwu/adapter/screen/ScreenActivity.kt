package com.mackwu.adapter.screen

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.mackwu.adapter.screen.wh.WhActivity
import com.mackwu.adapter.screen.sw.SwActivity


/**
 * ====================================================
 * Created by MackWu on 2019/9/20 10:28
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ====================================================
 * 屏幕适配
 *
 * <h2>px</h2>
 * 真实像素单位，构成影像的基本单位。对应分辨率，如1920*1080，单位就是px
 *
 * <h2>分辨率</h2>
 * 横向、纵向上的像素总数，单位px。如1920x1080
 *
 * <h2>屏幕尺寸</h2>
 * 手机对角线的物理尺寸，单位英寸(inch)，1in=2.54cm。如5.5inch
 *
 * <h2>dpi</h2>
 * 像素密度，每英寸(对角线)的像素点数，单位dpi。如400dpi
 * 计算公式：像素密度=对角线像素/屏幕尺寸，对角线像素由勾股定理算出
 * 例子：分辨率=1920x1080，屏幕尺寸=5.5inch，像素密度=(√1920^2+1080^2)/5.5=400dpi
 *
 * <h2>dp</h2>
 * 设备独立像素。在不同分辨率和尺寸的手机上代表了不同的真实像素
 * 计算公式：px=dp/(dpi/160) => 1dp对应(dpi/160)px
 * 例子：手机(dpi=400)，400/160=2.5 => 1dp对应2.5px
 *
 * <h2>dp存在的问题</h2>
 * dp有dpi决定，dpi又由分辨率和屏幕尺寸决定
 * 例子1：手机a(分辨率=1080x720，dpi=320，1dp=2px)、手机b(分辨率=1920x1080，dpi=480，1dp=3px)
 * 设置ImageView宽度为360dp，在手机a上宽度显示为720px，在手机b上宽度显示为1080px，适配正常
 * 例子2：手机a(分辨率=1080x720，dpi=320，1dp=2px)、手机b(分辨率=1080x720，dpi=480，1dp=3px)
 * 设置ImageView宽度为360dp，在手机a上宽度显示为720px，在手机b上宽度显示为1080px，适配失败
 *
 * <h2>density</h2>
 * 屏幕密度
 * 计算公式：density=dpi/160、density=px/dp
 *
 * <h2>获取分辨率、dpi、density</h2>
 * [screenWidth]
 * [screenHeight]
 * [dpi]
 * [density]
 *
 * <h2>适配核心问题</h2>
 * 1.适配的效率，即把设计图转化为App界面的过程是否高效
 * 2.如何保证实现UI界面在不同尺寸和分辨率的手机中UI的一致性
 *
 * <h2>屏幕适配方案</h2>
 * 宽高限定符方案 [WhActivity]
 * 最小宽度限定符方案 [SwActivity]
 * 今日头条适配方案：
 */
class ScreenActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.mackwu.adapter.R.layout.activity_main)
    }
}