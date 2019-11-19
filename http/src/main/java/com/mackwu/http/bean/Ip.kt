package com.mackwu.http.bean

/**
 * ===================================================
 * Created by MackWu on 2019/11/1 15:29
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 */
data class Ip(
        val city: String,
        val lat: String,
        val lon: String,
        val query: String
)

//{
//    "status": "success",
//    "country": "China",
//    "countryCode": "CN",
//    "region": "FJ",
//    "regionName": "Fujian",
//    "city": "Fuzhou",
//    "zip": "",
//    "lat": 26.0745,
//    "lon": 119.296,
//    "timezone": "Asia/Shanghai",
//    "isp": "Chinanet",
//    "org": "Chinanet FJ",
//    "as": "AS4134 No.31,Jin-rong Street",
//    "query": "125.77.41.1"
//}