package com.mackwu.kt.base.parcelable

import android.os.Parcel
import android.os.Parcelable

/**
 * ===================================================
 * Created by MackWu on 2020/4/21 19:04
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 */
data class PersonParcelable(val name: String, val age: Int) : Parcelable {

    constructor(parcel: Parcel) : this(parcel.readString() ?: "", parcel.readInt())

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeInt(age)
    }

    /**
     * 内容接口描述，返回0即可。
     */
    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<PersonParcelable> {
        override fun createFromParcel(parcel: Parcel): PersonParcelable {
            return PersonParcelable(parcel)
        }

        override fun newArray(size: Int): Array<PersonParcelable?> {
            return arrayOfNulls(size)
        }
    }
}