package com.mackwu.component.ui.livedata;

import com.mackwu.base.livedata.BaseLiveData;

/**
 * ===================================================
 * Created by MackWu on 2021/6/11 11:02
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 */
public class Str2LiveData extends BaseLiveData<String> {

    public Str2LiveData() {
        super(Str2LiveData.class.getSimpleName(), String.class);
    }
}
