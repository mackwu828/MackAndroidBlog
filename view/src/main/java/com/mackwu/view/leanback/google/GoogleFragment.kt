package com.mackwu.tv.google

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import androidx.leanback.app.BackgroundManager
import androidx.leanback.app.BrowseSupportFragment
import androidx.leanback.widget.*
import androidx.core.content.ContextCompat
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.SimpleTarget
import com.bumptech.glide.request.transition.Transition
import com.mackwu.view.R
import com.mackwu.view.leanback.google.VideoDetailsActivity

/**
 * ================================================
 * Created by MackWu on 2019/7/18 19:21
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ================================================
 */
class GoogleFragment : BrowseSupportFragment() {

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        // 设置列表标题
        title = "Hello Android TV!"
        // 设置标题为图片。会将标题替换成图片
//        badgeDrawable = ContextCompat.getDrawable(activity!!, R.mipmap.ic_launcher)
        // 设置是否显示导航栏。HEADERS_ENABLED 显示左侧导航栏，HEADERS_DISABLED 不显示 HEADERS_HIDDEN 隐藏，到边缘按左键还能显示
        headersState = HEADERS_ENABLED
        //
        isHeadersTransitionOnBackEnabled = true
        // 设置导航栏颜色
        brandColor = ContextCompat.getColor(activity!!, R.color.colorAccent)
        // 设置搜索颜色
        searchAffordanceColor = ContextCompat.getColor(activity!!, R.color.colorAccent)


        // BrowseSupportFragment左边是header导航栏，右边是rows列表
        // headerItem
        val textHeaderItem = HeaderItem(0, "textHeaderItem")
        val cardHeaderItem = HeaderItem(1, "cardHeaderItem")
        // rowsAdapter
        val rowsAdapter = ArrayObjectAdapter(ListRowPresenter())

        // textAdapter
        val textAdapter = ArrayObjectAdapter(TextRowsPresenter())
        for (i in 0..3) {
            textAdapter.add("item$i")
        }
        rowsAdapter.add(ListRow(textHeaderItem, textAdapter))

        // cardAdapter
        val cardAdapter = ArrayObjectAdapter(CardRowsPresenter())
        for (i in 0..10) {
            val movie = Movie("title$i", "content$i", "http://heimkehrend.raindrop.jp/kl-hacker/wp-content/uploads/2014/08/DSC02580.jpg")
            cardAdapter.add(movie)
        }
        rowsAdapter.add(ListRow(cardHeaderItem, cardAdapter))

        // 设置adapter
        adapter = rowsAdapter


        // backgroundManager修改rows背景
        val backgroundManager = BackgroundManager.getInstance(activity!!)
        backgroundManager.attach(activity!!.window)
        // 监听item选中
        setOnItemViewSelectedListener { itemViewHolder, item, rowViewHolder, row ->
            when (item) {
                is String -> backgroundManager.clearDrawable()
                is Movie -> {
                    Glide.with(this)
                            .asBitmap()
                            .load(item.url)
                            .apply(RequestOptions().override(1280, 960))
                            .into(object : SimpleTarget<Bitmap>() {
                                override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
                                    backgroundManager.drawable = BitmapDrawable(resources, resource)
                                }
                            })
                }
            }
        }
        // 监听item点击
        setOnItemViewClickedListener { itemViewHolder, any, rowViewHolder, row ->
            startActivity(Intent(activity, VideoDetailsActivity::class.java))
        }
    }

    /**
     * rows的item是TextView
     */
    class TextRowsPresenter : Presenter() {
        override fun onCreateViewHolder(parent: ViewGroup): ViewHolder {
            val textView = TextView(parent.context)
            textView.layoutParams = ViewGroup.LayoutParams(300, 200)
            textView.isFocusable = true
            textView.isFocusableInTouchMode = true
            textView.gravity = Gravity.CENTER
            return ViewHolder(textView)
        }

        override fun onBindViewHolder(viewHolder: ViewHolder, item: Any) {
            (viewHolder.view as TextView).text = item as String
        }

        override fun onUnbindViewHolder(viewHolder: ViewHolder) {
        }
    }

    /**
     * rows的item是imageCardView
     */
    class CardRowsPresenter : Presenter() {

        override fun onCreateViewHolder(parent: ViewGroup): Presenter.ViewHolder {
            val imageCardView = ImageCardView(parent.context)
            imageCardView.isFocusable = true
            imageCardView.isFocusableInTouchMode = true
            return ViewHolder(imageCardView)
        }

        override fun onBindViewHolder(viewHolder: Presenter.ViewHolder, item: Any) {
            val movie = item as Movie
            (viewHolder as ViewHolder).run {
                this.movie = movie
                imageCardView.titleText = movie.title
                imageCardView.contentText = movie.content
                imageCardView.setMainImageDimensions(300, 200)
//                imageCardView.mainImage = defaultImage
                updateImageCardView(movie.url)
            }
        }

        override fun onUnbindViewHolder(viewHolder: Presenter.ViewHolder) {
        }

        class ViewHolder(view: View) : Presenter.ViewHolder(view) {
            var imageCardView = view as ImageCardView
            lateinit var movie: Movie
            val defaultImage = ContextCompat.getDrawable(view.context, R.mipmap.ic_launcher)

            fun updateImageCardView(url: String) {
                Glide.with(view.context)
                        .asBitmap()
                        .load(url)
                        .apply(RequestOptions().placeholder(defaultImage).override(300, 200))
                        .into(object : SimpleTarget<Bitmap>() {
                            override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
                                imageCardView.mainImage = BitmapDrawable(view.resources, resource)
                            }
                        })
            }
        }

    }

}