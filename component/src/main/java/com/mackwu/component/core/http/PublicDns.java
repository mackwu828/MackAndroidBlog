package com.mackwu.component.core.http;

import com.qiniu.android.dns.DnsManager;
import com.qiniu.android.dns.Domain;
import com.qiniu.android.dns.IResolver;
import com.qiniu.android.dns.NetworkInfo;
import com.qiniu.android.dns.dns.DnsUdpResolver;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.List;

import okhttp3.Dns;

/**
 * ===================================================
 * Created by MackWu on 2021/4/23 15:51
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 * 国外：
 * Google Public DNS （8.8.8.8， 8.8.4.4）
 * <p>
 * 国内：
 * 114DNS （114.114.114.114， 114.114.115.115）
 */
public class PublicDns implements Dns {

    private final DnsManager dnsManager;

    public PublicDns() {
        IResolver[] resolvers = new IResolver[2];
        resolvers[0] = new DnsUdpResolver("8.8.8.8");
        resolvers[1] = new DnsUdpResolver("114.114.114.114");
        dnsManager = new DnsManager(NetworkInfo.normal, resolvers);
    }

    @NotNull
    @Override
    public List<InetAddress> lookup(@NotNull String hostname) throws UnknownHostException {
        try {
            Domain domain = new Domain(hostname);
            InetAddress[] inetAddresses = dnsManager.queryInetAdress(domain);
            if (inetAddresses != null) {
                return Arrays.asList(inetAddresses);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Dns.SYSTEM.lookup(hostname);
    }

}
