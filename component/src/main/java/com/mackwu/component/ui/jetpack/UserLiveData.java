package com.mackwu.component.ui.jetpack;

import androidx.lifecycle.MutableLiveData;

import com.mackwu.component.bean.User;

/**
 * ===================================================
 * Created by MackWu on 2020/7/8 19:25
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 */
public class UserLiveData extends MutableLiveData<User> {

    private static UserLiveData userLiveData;

    public static UserLiveData getInstance() {
        if (userLiveData == null) {
            userLiveData = new UserLiveData();
        }
        return userLiveData;
    }

    @Override
    protected void onActive() {
    }

    @Override
    protected void onInactive() {
    }

}
