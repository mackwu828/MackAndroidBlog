package com.mackwu.component.base;

import org.jetbrains.annotations.NotNull;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.List;

import okhttp3.Dns;

/**
 * ===================================================
 * Created by MackWu on 2021/4/23 15:51
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 * 10.0.2.3
 * 10.0.2.2
 * <p>
 * adb root
 * adb shell getprop
 * adb shell setprop net.dns1 xx
 * adb shell setprop net.eth0.dns1 1.1.1.1
 * adb shell setprop net.eth0.gw 1.1.1.1
 */
public class OkHttpDns implements Dns {

    public OkHttpDns(String dnsAddress) {

    }

    public OkHttpDns(List<String> dnsAddressList) {

    }

    @NotNull
    @Override
    public List<InetAddress> lookup(@NotNull String hostname) throws UnknownHostException {
        return null;
    }

}
