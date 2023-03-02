package com.mackwu.component.func.mqtt;

import android.util.Log;

import org.spongycastle.asn1.pkcs.PrivateKeyInfo;
import org.spongycastle.cert.X509CertificateHolder;
import org.spongycastle.cert.jcajce.JcaX509CertificateConverter;
import org.spongycastle.openssl.PEMKeyPair;
import org.spongycastle.openssl.PEMParser;
import org.spongycastle.openssl.jcajce.JcaPEMKeyConverter;

import java.io.StringReader;
import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.cert.X509Certificate;

import javax.net.ssl.SSLSocketFactory;

import okhttp3.tls.HandshakeCertificates;
import okhttp3.tls.HeldCertificate;

/**
 * ===================================================
 * Created by MackWu on 2022/4/1 15:41
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 */
public class TLS {

    private static final JcaX509CertificateConverter certificateConverter = new JcaX509CertificateConverter();
    private static final JcaPEMKeyConverter keyConverter = new JcaPEMKeyConverter();

    /**
     * 获取SSLSocketFactory
     *
     * @param caCert     证书
     * @param privateKey 私钥
     */
    public static SSLSocketFactory getSSLSocketFactory(String caCert, String privateKey) {
        try {
            HandshakeCertificates.Builder builder = new HandshakeCertificates.Builder();
            X509Certificate cert = parseCertificate(caCert);
            PrivateKey key = parsePrivateKey(privateKey);
            if (cert != null && key != null) {
                builder.heldCertificate(new HeldCertificate(new KeyPair(null, key), cert));
                return builder.build().sslSocketFactory();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 解析证书
     *
     * @param caCert 证书
     */
    private static X509Certificate parseCertificate(String caCert) throws Exception {
        PEMParser pemParser = new PEMParser(new StringReader(caCert));
        Object obj = pemParser.readObject();
        pemParser.close();
        if (obj instanceof X509CertificateHolder) {
            return certificateConverter.getCertificate((X509CertificateHolder) obj);
        } else {
            Log.d("TLS", "Unexpected PEM object: " + obj.getClass().getName());
        }
        return null;
    }

    /**
     * 解析私钥
     *
     * @param privateKey 私钥
     */
    private static PrivateKey parsePrivateKey(String privateKey) throws Exception {
        PEMParser pemParser = new PEMParser(new StringReader(privateKey));
        Object obj = pemParser.readObject();
        pemParser.close();
        if (obj instanceof PEMKeyPair) {
            return keyConverter.getPrivateKey(((PEMKeyPair) obj).getPrivateKeyInfo());
        } else if (obj instanceof PrivateKeyInfo) {
            return keyConverter.getPrivateKey((PrivateKeyInfo) obj);
        }
        return null;
    }

}
