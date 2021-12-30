package com.mackwu.component.ui;

import android.os.Bundle;

import androidx.annotation.Nullable;

import com.mackwu.base.BaseActivity;
import com.mackwu.base.util.LogUtil;
import com.mackwu.base.viewmodel.BaseViewModel;
import com.mackwu.component.databinding.MainActivityBinding;
import com.mackwu.component.util.SslUtil;

import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.cert.X509Certificate;

/**
 * ===================================================
 * Created by MackWu on 2021/10/19 10:26
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 */
public class SecurityActivity extends BaseActivity<BaseViewModel, MainActivityBinding> {

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {
        binding.btnTest.setOnClickListener(v -> {

        });

        try {
            KeyStore keyStore = SslUtil.getKeyStore(this, "test.keystore", "123456");
            PrivateKey privateKey = SslUtil.getPrivateKey(keyStore, "123456");
            X509Certificate x509Certificate = SslUtil.getCert(keyStore);

//            KeyStore keyStore = SslUtil.getKeyStore(this, "client.p12", "zeasn123");
//            PrivateKey privateKey = SslUtil.getPrivateKey(keyStore, "zeasn123");
//            X509Certificate x509Certificate = SslUtil.getX509Certificate(keyStore);

            LogUtil.d("privateKey: " + SslUtil.getPrivateKeyStr(privateKey));
            LogUtil.d("x509Certificate: " + SslUtil.getCertStr(x509Certificate));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
