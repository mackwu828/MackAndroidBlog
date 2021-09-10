package com.mackwu.component.ui.widget;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;

import androidx.annotation.Nullable;

import com.mackwu.base.BaseActivity;
import com.mackwu.base.util.LogUtil;
import com.mackwu.base.viewmodel.BaseViewModel;
import com.mackwu.component.databinding.WidgetActivityTextViewBinding;
import com.mackwu.component.util.DateUtil;

/**
 * ===================================================
 * Created by MackWu on 2021/2/24 10:46
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 */
public class TextViewActivity extends BaseActivity<BaseViewModel, WidgetActivityTextViewBinding> {

    private TimeChangedReceiver timeChangedReceiver;

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {
        binding.tcTest.setFormat12Hour("MMM dd , EEEE");
        binding.tcTest.setFormat24Hour("MMM dd , EEEE");

        timeChangedReceiver = new TimeChangedReceiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(Intent.ACTION_TIME_CHANGED);
//        intentFilter.addAction(Intent.ACTION_TIME_TICK);
        registerReceiver(timeChangedReceiver, intentFilter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(timeChangedReceiver);
    }

    private String getDaySuffix() {
        int day = DateUtil.getDay();
        String daySuffix;
        if (day == 1) {
            daySuffix = "st";
        } else if (day == 2) {
            daySuffix = "nd";
        } else if (day == 3) {
            daySuffix = "rd";
        } else {
            daySuffix = "th";
        }
        return daySuffix;
    }

    private class TimeChangedReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            String daySuffix = getDaySuffix();
            LogUtil.d("TimeChangedReceiver onReceive...  daySuffix: " + daySuffix);
            binding.tcTest.setFormat12Hour("MMM dd '" + daySuffix + "' , EEEE");
            binding.tcTest.setFormat24Hour("MMM dd '" + daySuffix + "' , EEEE");
        }
    }

}
