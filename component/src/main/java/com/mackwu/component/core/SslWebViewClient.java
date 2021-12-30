package com.mackwu.component.core;

import android.content.Context;
import android.net.http.SslError;
import android.webkit.ClientCertRequest;
import android.webkit.SslErrorHandler;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.mackwu.base.util.LogUtil;
import com.mackwu.component.util.SslUtil;

import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.cert.X509Certificate;

/**
 * ===================================================
 * Created by MackWu on 2021/10/18 18:36
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 */
public class SslWebViewClient extends WebViewClient {

    private PrivateKey privateKey;
    private X509Certificate cert;
    private static final String password = "zeasn123"; // 密钥库密码、私钥密码

    public SslWebViewClient(Context context) {
        LogUtil.d("SslWebViewClient...");
        try {
            KeyStore keyStore = SslUtil.getKeyStore(context, "client.p12", password);
            // 私钥
            privateKey = SslUtil.getPrivateKey(keyStore, password);
            // 证书
            cert = SslUtil.getCert(keyStore);
            LogUtil.d( " "  + "\n" +
                    "privateKey: " + SslUtil.getPrivateKeyStr(privateKey) + "\n" +
                    "x509Certificate: " + SslUtil.getCertStr(cert)
            );
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onReceivedClientCertRequest(WebView view, ClientCertRequest request) {
        LogUtil.d("onReceivedClientCertRequest...");
        if (privateKey != null && cert != null) {
            request.proceed(privateKey, new X509Certificate[]{cert});
        } else {
            request.cancel();
        }
    }

    @Override
    public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
        LogUtil.d("onReceivedSslError...  error: " + error.toString());
        handler.proceed();
    }

    @Override
    public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
        LogUtil.d("onReceivedError...  errorCode: " + errorCode + ", description: " + description);
    }

}
