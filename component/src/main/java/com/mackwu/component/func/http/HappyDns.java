package com.mackwu.component.func.http;

import androidx.annotation.NonNull;

import com.qiniu.android.dns.DnsManager;
import com.qiniu.android.dns.IResolver;
import com.qiniu.android.dns.NetworkInfo;
import com.qiniu.android.dns.Record;
import com.qiniu.android.dns.dns.DnsUdpResolver;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Dns;

/**
 * @author MackWu
 * @since 2021/4/23 15:51
 */
public class HappyDns implements Dns {

    private final DnsManager dnsManager;

    public HappyDns() {
        IResolver[] resolvers = new IResolver[4];
        resolvers[0] = new DnsUdpResolver("8.8.8.8");
        resolvers[1] = new DnsUdpResolver("8.8.4.4");
        resolvers[2] = new DnsUdpResolver("114.114.114.114");
        resolvers[3] = new DnsUdpResolver("114.114.114.115");
        dnsManager = new DnsManager(NetworkInfo.normal, resolvers);
    }

    @Override
    public List<InetAddress> lookup(@NonNull String hostname) throws UnknownHostException {
        List<InetAddress> inetAddresses = new ArrayList<>();
        try {
            Record[] records = dnsManager.queryRecords(hostname);
            if (records != null && records.length > 0) {
                for (Record record : records) {
                    inetAddresses.add(InetAddress.getByName(record.value));
                }
            }
        } catch (Throwable e) {
            e.printStackTrace();
        }
        inetAddresses.addAll(Dns.SYSTEM.lookup(hostname));
        return inetAddresses;
    }

}
