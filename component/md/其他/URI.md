URI（Universal Resource Identifier）统一资源定位符

## URI的格式
[scheme:][//host:port][path][?query] 如https://www.baidu.com:8080/xxx/kkk?tn=62095104_23_oem_dg

- scheme: 协议。如http、https、ftp
- host: 主机，一般用域名或ip来表示。如www.baidu.com
- port: 端口。如8080
- path: 路径，资源的分级目录。如/xxx/kkk
- query: 查询字符串。如?tn=62095104_23_oem_dg


## URL
URL是URI的子集  
URL是抽象的，由访问资源的命名机制、存放资源的主机名、资源自身的名称组成  
URI是具体的，由协议、存放资源的主机的IP地址、具体资源名称，如 http://192.168.1.1/text.txt  