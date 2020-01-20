package com.mackwu.media.util

import android.content.Context
import androidx.annotation.DrawableRes
import androidx.fragment.app.Fragment
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions

/**
 * ===================================================
 * Created by MackWu on 2019-06-04 15:38
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 */

inline fun requestOptions(block: RequestOptions.() -> Unit) = RequestOptions().apply(block)

fun Context.loadImage(@DrawableRes id: Int, imageView: ImageView) = Glide.with(this).load(id).into(imageView)

fun Context.loadImage(url: String, imageView: ImageView) = Glide.with(this).load(url).into(imageView)

fun Context.loadImage(url: String, imageView: ImageView, requestOptions: RequestOptions) = Glide.with(this).load(url).apply(requestOptions).into(imageView)

fun Context.loadImage(url: String, imageView: ImageView, placeholder: Int) = loadImage(url, imageView, requestOptions { placeholder(placeholder).skipMemoryCache(true).diskCacheStrategy(DiskCacheStrategy.NONE) })

fun Context.loadImageNoCache(url: String, imageView: ImageView) = loadImage(url, imageView, requestOptions { skipMemoryCache(true).diskCacheStrategy(DiskCacheStrategy.NONE) })

/**
 * 预加载图片。先调用preload，在into
 */
fun Context.preloadImage(url: String) = Glide.with(this).load(url).apply(requestOptions { diskCacheStrategy(DiskCacheStrategy.RESOURCE) }).preload()

fun Context.preloadImage(url: String, imageView: ImageView) = loadImage(url, imageView, requestOptions { diskCacheStrategy(DiskCacheStrategy.RESOURCE) })


/**
 * 加载圆形图片
 */
fun Context.loadRoundImage(url: String, imageView: ImageView, placeholder: Int) = loadImage(url, imageView, requestOptions { circleCrop().placeholder(placeholder).error(placeholder) })

fun Context.loadRoundImage(url: String, imageView: ImageView, placeholder: Int, roundingRadius: Int) = loadImage(url, imageView, requestOptions { transforms(CenterCrop(), RoundedCorners(roundingRadius)).placeholder(placeholder).error(placeholder) })


/**
 * 清除缓存
 */
fun Context.clearGlideDiskCache() = Thread { Glide.get(this).clearDiskCache() }.start()

fun Context.clearGlideMemory() = Glide.get(this).clearMemory()


/**
 *  *  *  *  *  *  *  *  *  *  *  *  *  *  Fragment *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *
 */
fun androidx.fragment.app.Fragment.loadImage(url: String, imageView: ImageView, placeholder: Int) = activity?.loadImage(url, imageView, placeholder)

fun androidx.fragment.app.Fragment.loadRoundImage(url: String, imageView: ImageView, placeholder: Int) = activity?.loadRoundImage(url, imageView, placeholder)
