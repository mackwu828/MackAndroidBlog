package com.mackwu.component.util;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;

import com.google.gson.Gson;
import com.mackwu.component.bean.DeeplinkDetail;

import java.util.List;

/**
 * ===================================================
 * Created by MackWu on 2020/9/9 14:36
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 */
public class DeeplinkStarter {

    private final Context context;

    public DeeplinkStarter(Context context) {
        this.context = context;
    }

    public void start(String deeplinkJson, OnNotInstalledListener listener) {
        try {
            DeeplinkDetail deeplinkDetail = new Gson().fromJson(deeplinkJson, DeeplinkDetail.class);
            start(deeplinkDetail, listener);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void start(DeeplinkDetail deeplinkDetail, OnNotInstalledListener listener) {
        try {
            if (deeplinkDetail == null) {
                return;
            }
            String packageName = deeplinkDetail.getPkg();
            String activityName = deeplinkDetail.getClassName();
            DeeplinkDetail.Data data = deeplinkDetail.getData();
            String uri = data.getUri();
            int flag = deeplinkDetail.getFlag();
            String action = deeplinkDetail.getAction();
            List<DeeplinkDetail.Extra> extras = deeplinkDetail.getExtra();

            // intent
            Intent intent = new Intent();

            // packageName
            if (!TextUtils.isEmpty(packageName)) {
                boolean installed = PackageUtil.isAppInstalled(context, packageName);
                if (!installed) {
                    if (listener != null) listener.onNotInstalled(packageName);
                    return;
                }
                // activityName
                if (TextUtils.isEmpty(activityName)) {
                    activityName = PackageUtil.getActivityName(context, packageName);
                }
                intent.setClassName(packageName, activityName);
            }

            // uri
            if (!TextUtils.isEmpty(uri)) {
                intent.setData(Uri.parse(uri));
            }

            // flag
            if (flag != 0) {
                intent.setFlags(flag);
            }

            // action
            if (!TextUtils.isEmpty(action)) {
                intent.setAction(action);
            }

            // extras
            if (extras != null && extras.size() > 0) {
                for (DeeplinkDetail.Extra extra : extras) {
                    intent.putExtra(extra.getKey(), extra.getValue());
                }
            }

            context.startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public interface OnNotInstalledListener {
        /**
         * 未安装
         */
        void onNotInstalled(String packageName);
    }
}
