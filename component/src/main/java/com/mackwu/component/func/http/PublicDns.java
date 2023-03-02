package com.mackwu.component.func.http;

import androidx.annotation.NonNull;

import org.xbill.DNS.Address;
import org.xbill.DNS.ExtendedResolver;
import org.xbill.DNS.Lookup;
import org.xbill.DNS.Resolver;
import org.xbill.DNS.SimpleResolver;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import okhttp3.Dns;

/**
 * ===================================================
 * Created by MackWu on 2022/3/10 11:35
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 */
public class PublicDns implements Dns {

    private ExtendedResolver extendedResolver;

    public PublicDns() {
        try {
            List<Resolver> resolvers = new ArrayList<>();
            // Google Public DNS
            resolvers.add(new SimpleResolver("8.8.8.8"));
            // OpenDNS
            resolvers.add(new SimpleResolver("208.67.222.222"));
            // aliDNS
            resolvers.add(new SimpleResolver("223.5.5.5"));
            // 114DNS
            resolvers.add(new SimpleResolver("114.114.114.114"));
            extendedResolver = new ExtendedResolver(resolvers);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<InetAddress> lookup(@NonNull String hostname) throws UnknownHostException {
        try {
            Lookup.setDefaultResolver(extendedResolver);
            return Collections.singletonList(Address.getByAddress(hostname));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Dns.SYSTEM.lookup(hostname);
    }

}
