package com.mackwu.kt.base.parcelable;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * ===================================================
 * Created by MackWu on 2020/4/22 10:06
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 */
public class JavaPersonParcelable implements Parcelable {

    private String name;
    private int age;

    /**
     *
     */
    protected JavaPersonParcelable(Parcel in) {
        this.name = in.readString();
        this.age = in.readInt();
    }

    /**
     * 内容接口描述，返回0即可。
     */
    @Override
    public int describeContents() {
        return 0;
    }

    /**
     *
     */
    @Override
    public void writeToParcel(final Parcel dest, final int flags) {
        dest.writeString(this.name);
        dest.writeInt(this.age);
    }

    public static final Creator<JavaPersonParcelable> CREATOR = new Creator<JavaPersonParcelable>() {
        @Override
        public JavaPersonParcelable createFromParcel(Parcel in) {
            return new JavaPersonParcelable(in);
        }

        @Override
        public JavaPersonParcelable[] newArray(int size) {
            return new JavaPersonParcelable[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(final int age) {
        this.age = age;
    }
}
