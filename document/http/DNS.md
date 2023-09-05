


公共DNS服务器地址？https://zhuanlan.zhihu.com/p/39406412

OkHttp使用DNS？https://github.com/square/okhttp/tree/master/okhttp-dnsoverhttps
https://stackoverflow.com/questions/52458671/android-retrofit-okhttp-use-8-8-8-8-programatically-for-dns-lookup

OkHttp使用公共的DNS？如8.8.8.8. 
OkHttp使用阿里云DNS？https://help.aliyun.com/document_detail/174169.html


## OkHttp使用公共的DNS服务器地址？
```
    implementation 'com.qiniu:happy-dns:1.0.0'
```
```
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
```

## OkHttp使用阿里云DNS服务器地址？
```
public class AliDns implements Dns {

    private final DNSResolver dnsResolver = DNSResolver.getInstance();

    @Override
    public List<InetAddress> lookup(@NonNull String hostname) throws UnknownHostException {
        String ip = dnsResolver.getIPV4ByHost(hostname);
        if (ip != null) {
            return Arrays.asList(InetAddress.getAllByName(ip));
        }
        return Dns.SYSTEM.lookup(hostname);
    }
}
```