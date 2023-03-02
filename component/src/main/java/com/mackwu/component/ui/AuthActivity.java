package com.mackwu.component.ui;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;

import com.mackwu.base.BaseActivity;
import com.mackwu.base.util.Logger;
import com.mackwu.base.viewmodel.BaseViewModel;
import com.mackwu.component.databinding.ActivtyAuthBinding;

/**
 * ===================================================
 * Created by MackWu on 2022/4/19 16:51
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 */
public class AuthActivity extends BaseActivity<BaseViewModel, ActivtyAuthBinding> {

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {
        Intent intent = getIntent();
        if (intent != null) {
            Uri uri = intent.getData();
//            String host = uri.getHost();
//            String scheme = uri.getScheme();
//            LogUtil.d("host=" + host + ", scheme=" + scheme);

            // https://whalephoto.zeasn.tv/skill/link/?code=ANztsfyAXjUOfwTLXarD&scope=alexa%3A%3Askills%3Aaccount_linking
            String appLinkData = intent.getData().toString();
            Logger.d("appLinkData=" + appLinkData);

            //  code=ANztsfyAXjUOfwTLXarD&scope=alexa%3A%3Askills%3Aaccount_linking
            if (appLinkData.startsWith("https://whalephoto.zeasn.tv/skill/link")) {
                String[] urlParts = appLinkData.split("\\?");
                if (urlParts.length > 0) {
                    for (String urlPart : urlParts) {
                        Logger.d("urlPart=" + urlPart);
                    }
                }
            }
        }
    }
}
