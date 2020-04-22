package com.mackwu.adapter.screen

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.util.Log
import com.mackwu.adapter.R
import com.mackwu.adapter.util.dpi
import com.mackwu.adapter.util.screenHeight
import com.mackwu.adapter.util.screenWidth
import kotlinx.android.synthetic.main.activity_a.*

/**
 * ================================================
 * Created by MackWu on 2019/9/19 19:17
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ================================================
 * 最小宽度限定符适配方案、smallestWidth适配，或者叫sw限定符适配
 * <a href="https://www.jianshu.com/p/a4b8e4c5d9b0">作者：拉丁吴</a>
 *
 * <h2>原理</h2>
 * Android会识别屏幕可用高度和宽度的最小尺寸的dp值（其实就是手机的宽度值），然后根据识别到的结果去资源文件中寻找对应限定符的文件夹下的资源文件。
 * 计算公式：px=dp/(dpi/160)
 *
 * 计算1：根据dpi和手机宽的像素计算sw
 * 计算公式：px=dp/(dpi/160) => sw=px/(dpi/160)
 * 如dpi=480，手机宽=1080px，sw=1080/(480/160)=360dp
 *
 * 计算2：按照设计图尺寸对dp进行等分，再乘以sw
 * 计算公式：(1dp/设计图宽度)*sw => 1dp对应sw/设计图宽度dp
 * 如设计图尺寸=375x667，sw=360dp => (1/375)*360=0.96dp => 1dp对应0.96dp
 * 定义一个values-sw300dp的文件夹，根据公式进行赋值
 * <dimen name="dp_1">0.96dp</dimen>
 * <dimen name="dp_2">1.92dp</dimen>
 * <dimen name="dp_3">2.88dp</dimen>
 * ...
 *
 * 计算3：dp转px
 * px=dp/(dpi/160) => 1dp对应(dpi/160)px。如dpi=480, 1dp对应3px
 *
 * 总结计算公式：1dp=sw/设计度宽度*(dpi/160)px 或者根据sw找到对应的dp值再乘以dpi
 *
 * <h2>测试</h2>
 * 设计图尺寸：375x667
 * 手机1(1080x1920、dpi=480)：button宽度取dp_187占屏幕宽度一半
 * 09-29 03:30:31.439 1756-1756/com.mackwu.adapter D/TAG: dpi: 480, 宽高: 1080x1776, 计算出来的sw: 360dp, 实际使用的sw: 360dp，计算公式：1dp=360/375*(480/160)=2.88px, btn_test.width: 539px
 * 手机2(1080x1920、dpi=400)：button宽度取dp_187占屏幕宽度一半
 * 09-29 05:50:15.729 1883-1883/com.mackwu.adapter D/TAG: dpi: 400, 宽高: 1080x1800, 计算出来的sw: 432dp, 实际使用的sw: 430dp，计算公式：1dp=430/375*(400/160)=2.8666666px, btn_test.width: 536px
 * 手机3(1440x2560、dpi=480)：button宽度取dp_187占屏幕宽度一半
 * 09-29 05:00:21.988 1844-1844/com.mackwu.adapter D/TAG: dpi: 480, 宽高: 1440x2416, 计算出来的sw: 480dp, 实际使用的sw: 480dp，计算公式：1dp=480/375*(480/160)=3.0px, btn_test.width: 718px
 *
 * 设计图尺寸：960x540
 * tv1(1920x1080、dpi=480)：button宽度取dp_480占屏幕宽度一半
 * 09-29 06:41:42.743 2076-2076/com.mackwu.adapter D/TAG: dpi: 480, 宽高: 1794x1080, 计算出来的sw: 360dp, 实际使用的sw: 360dp，计算公式：1dp=360/540*(480/160)=2.0px, btn_test.width: 960px
 * tv2(1920x1080、dpi=400)：button宽度取dp_480占屏幕宽度一半
 * 09-29 21:56:24.849 1641-1641/com.mackwu.adapter D/TAG: dpi: 400, 宽高: 1815x1080, 计算出来的sw: 432dp, 实际使用的sw: 430dp，计算公式：1dp=430/540*(400/160)=1.9907408px, btn_test.width: 956px
 */
class SwActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_a)


        val realWidth = if (screenWidth > screenHeight) screenHeight else screenWidth
        btn_test.setOnClickListener {
            val text = "dpi: $dpi, \n" +
                    "宽高: ${screenWidth}x$screenHeight, \n" +
                    "设计图宽度: ${SwManager.DESIGN_WIDTH}, \n" +
                    "计算出来的sw: $realWidth/($dpi/160)=${(realWidth / (dpi / 160f)).toInt()}dp, \n" +
                    "实际使用的sw: ${resources.getString(R.string.base_dpi)}，\n" +
                    "dp换算px公式：1dp=${resources.getString(R.string.base_dpi).replace("dp", "")}/${SwManager.DESIGN_WIDTH}*($dpi/160)=" +
                    (resources.getString(R.string.base_dpi).replace("dp", "").toFloat() / SwManager.DESIGN_WIDTH) * (dpi / 160f) + "px, \n" +
                    "btn_test.width: ${btn_test.width}px, \n" +
                    "btn_test.textSize: ${btn_test.textSize}px"
            Log.d("TAG", text)
            tv_test.text = text
        }
    }
}