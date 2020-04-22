package com.mackwu.kt.base.parcelable

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * ===================================================
 * Created by MackWu on 2020/4/22 10:16
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 */
@Parcelize
data class PersonParcelize(val name: String, val age: Int) : Parcelable