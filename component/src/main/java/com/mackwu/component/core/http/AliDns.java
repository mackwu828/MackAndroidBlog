package com.mackwu.component.core.http;

import android.content.Context;

import androidx.annotation.NonNull;

import com.alibaba.pdns.DNSResolver;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.List;

import okhttp3.Dns;

/**
 * ===================================================
 * Created by MackWu on 2021/12/21 15:18
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 * https://help.aliyun.com/document_detail/174169.html
 * https://help.aliyun.com/document_detail/270783.htm
 */
public class AliDns implements Dns {

    private final DNSResolver dnsResolver = DNSResolver.getInstance();

    public static void init(Context context) {
        DNSResolver.Init(context, ""); // 设置控制台接入sdk的accountId
        DNSResolver.setAccessKeySecret(""); // 设置控制台接入sdk的accessKeySecret
        DNSResolver.setAccessKeyId(""); // 设置控制台接入sdk的accessKeyId
    }

    @Override
    public List<InetAddress> lookup(@NonNull String hostname) throws UnknownHostException {
        // 调用阿里云公共DNS服务进行域名解析
        String ip = dnsResolver.getIPV4ByHost(hostname);
        if (ip != null) {
            // 如果ip不为null，直接使用该ip进行网络请求
            return Arrays.asList(InetAddress.getAllByName(ip));
        }
        // 如果返回null，走系统DNS服务进行域名解析
        return Dns.SYSTEM.lookup(hostname);
    }

}
