package com.mackwu.view.basic.viewpager

import android.os.Bundle
import androidx.viewpager.widget.ViewPager
import androidx.appcompat.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.mackwu.view.R
import kotlinx.android.synthetic.main.activity_vp.*

/**
 * ================================================
 * Created by MackWu on 2019/9/17 15:27
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ================================================
 *
 * ViewPager
 *
 *
 * <h2>ViewPager + ImageView</h2>
 * 1.数据
 * 2.适配器
 * 3.滑动监听
 *
 * <h2>预加载</h2>
 * 默认加载当前页面、左边1个页面和右边1个页面，即默认预加载个数是1。
 * 如果预加载个数是2，则会默认加载当前页面、左边2个页面和右边2个页面。
 * @see ViewPager.setOffscreenPageLimit
 *
 * <h2>设置当前页面</h2>
 * @see ViewPager.setCurrentItem
 * item表示位置
 * smoothScroll表示是否流畅滑动：smoothScroll设置为true流畅滑动，比较流畅的换页是使用了动画。没设置或false，则闪一下就跳到第二个页面。
 *
 * <h2>ViewPager + Fragment + Tab</h2>
 * @see VpFTabActivity
 *
 * <h2>画廊/h2>
 * 画廊表示除了当前item，左右两个item都会显示一部分：https://blog.csdn.net/JM_beizi/article/details/51297605
 *
 * <h2>无限轮播</h2>
 *
 *
 * setTag的作用？
 * FragmentPagerAdapter和FragmentStatePagerAdapter区别？
 * 懒加载：即只加载当前页面：https://www.jianshu.com/p/7a47907f49c2
 * 从第0页跳转到第n页，不显示中间滑动的页面：自定义Scroller
 * 翻页动画：https://github.com/ToxicBakery/ViewPagerTransforms
 * ViewPager不能动态刷新UI，notifyDataSetChanged会失效：https://www.jianshu.com/p/80891d0185f7
 * PagerAdapter工作流程：
 * 1.Viewpager初始化页面时会加载当前页面和左右两个页面，左边没有页面，所以会加载当前和右边共两个页面，即调用了2次instantiateItem()
 * 2. 向右滑动，会调用instantiateItem()生成新的对象，如果页面滑动超过了预加载个数，则调用destroyItem销毁对象。如滑动到第2个页面，只保存第1/2/3，销毁第0个页面
 *
 */
class VpActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_vp)

        // 数据
        val resIds = intArrayOf(R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher)
        val imageList = arrayListOf<View>()
        for (resId in resIds) {
            val iv = ImageView(this)
            iv.setBackgroundResource(resId)
            imageList.add(iv)
        }
        // 适配器
        val adapter = VpAdapter(imageList)
        view_pager.adapter = adapter
        // 滑动监听
        view_pager.addOnPageChangeListener(object : androidx.viewpager.widget.ViewPager.OnPageChangeListener {
            /**
             * @param state state=1表示正在滑动，state=2表示滑动完毕，state=0表示什么都没做
             */
            override fun onPageScrollStateChanged(state: Int) {
            }

            /**
             * @param position 点击滑动的页面
             * @param positionOffset t当前页面偏移的百分比
             * @param positionOffsetPixels 当前页面偏移的像素位置
             */
            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
            }

            /**
             * position就是当前选中的页面，从0开始
             */
            override fun onPageSelected(position: Int) {
            }
        })

        // 预加载个数是1
        view_pager.offscreenPageLimit = 1
        // 设置item0为当前页面
        view_pager.setCurrentItem(0, true)
    }

    class VpAdapter(private val list: List<View>) : androidx.viewpager.widget.PagerAdapter() {

        /**
         * 是否使用缓存。true, 使用缓存，false调用instantiateItem方法创建一个新的对象
         */
        override fun isViewFromObject(view: View, obj: Any): Boolean {
            Log.d("TAG", "isViewFromObject...")
            return view === obj
        }

        override fun getCount(): Int {
            Log.d("TAG", "getCount...")
            return list.size
        }

        /**
         * 初始化对象
         */
        override fun instantiateItem(container: ViewGroup, position: Int): Any {
            Log.d("TAG", "instantiateItem...")
            container.addView(list[position])
            return list[position]
        }

        /**
         * 销毁对象
         */
        override fun destroyItem(container: ViewGroup, position: Int, obj: Any) {
            Log.d("TAG", "destroyItem...")
            container.removeView(list[position])
        }
    }

}