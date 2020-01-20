package com.mackwu.http.rxjava

import io.reactivex.Observable


/**
 * ===================================================
 * Created by MackWu on 2020/1/15 18:23
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 */

fun map1() {
    val observable = Observable.just(1, 2, 3)
            .map { i -> i * 1.0f }
            .subscribe { i -> println(i) }
}


fun map10() {
    val observable = Observable.just(1, 2, 3)
            .flatMap { i ->
                val list = arrayListOf<String>()
                for (j in 0..5) {
                    list.add("item$i")
                }
                Observable.fromIterable(list)
            }
            .subscribe { i -> println(i) }
}