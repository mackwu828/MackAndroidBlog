package com.mackwu.component.util;

import android.content.Context;
import android.util.Base64;

import java.io.InputStream;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.cert.X509Certificate;
import java.util.Enumeration;

/**
 * ===================================================
 * Created by MackWu on 2021/10/19 15:22
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 */
public final class SslUtil {

    /**
     * KeyStore default type is jks. See {@link KeyStore#getDefaultType()}
     * Improve security. PKCS12 offers stronger cryptographic algorithms than JKS
     * https://stackoverflow.com/questions/11536848/keystore-type-which-one-to-use/11540061
     */
    private static final String KS_TYPE = "PKCS12";

    /**
     * 获取密钥库
     *
     * @param context  context
     * @param fileName 密钥库文件名
     * @param password 密钥库密码
     */
    public static KeyStore getKeyStore(Context context, String fileName, String password) throws Exception {
        // 打开密钥库文件
        InputStream inputStream = context.getAssets().open(fileName);
        // 初始化密钥库
        KeyStore keystore = KeyStore.getInstance(KS_TYPE);
        // 导入密钥库
        keystore.load(inputStream, password.toCharArray());
        inputStream.close();
        return keystore;
    }

    /**
     * 获取密钥库。无密码
     *
     * @param context  context
     * @param fileName 密钥库文件名
     */
    public static KeyStore getKeyStore(Context context, String fileName) throws Exception {
        return getKeyStore(context, fileName, "");
    }

    /**
     * 获取私钥
     *
     * @param keyStore 密钥库
     * @param password 私钥密码
     */
    public static PrivateKey getPrivateKey(KeyStore keyStore, String password) throws Exception {
        Enumeration<?> localEnumeration = keyStore.aliases();
        String alias = (String) localEnumeration.nextElement();
        return (PrivateKey) keyStore.getKey(alias, password.toCharArray());
    }

    /**
     * 获取证书
     *
     * @param keyStore 密钥库
     */
    public static X509Certificate getCert(KeyStore keyStore) throws Exception {
        Enumeration<?> localEnumeration = keyStore.aliases();
        String alias = (String) localEnumeration.nextElement();
        return (X509Certificate) keyStore.getCertificate(alias);
    }

    /**
     * 获取私钥字符串
     */
    public static String getPrivateKeyStr(PrivateKey privateKey) {
        return encodeToStr(privateKey.getEncoded());
    }

    /**
     * 获取证书字符串
     *
     * @param x509Certificate 证书
     */
    public static String getCertStr(X509Certificate x509Certificate) throws Exception {
        return encodeToStr(x509Certificate.getEncoded());
    }

    private static String encodeToStr(byte[] bytes) {
        return Base64.encodeToString(bytes, Base64.NO_WRAP);
    }

}
