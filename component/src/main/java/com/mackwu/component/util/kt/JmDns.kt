//package com.mackwu.component.util.kt
//
//import android.content.Context
//import android.net.wifi.WifiManager
//import android.util.Log
//import javax.jmdns.JmDNS
//import javax.jmdns.ServiceEvent
//import javax.jmdns.ServiceListener
//
///**
// * ===================================================
// * Created by MackWu on 2019/11/28 14:34
// * <a href="mailto:wumengjiao828@163.com">Contact me</a>
// * <a href="https://github.com/mackwu828">Follow me</a>
// * ===================================================
// */
//private const val TAG = "JmDNSHelper"
//
//private val Context.jmDns
//    get() = JmDNS.create(ip)
//
////fun Context.registerService() {
////    Log.d(TAG, "registerService...")
////    Thread {
////        val serviceInfo = ServiceInfo.create("_http._tcp.local.", "example", 1234, "path=index.html")
////        jmDns.registerService(serviceInfo)
////    }.start()
////}
////
////fun Context.unregisterAllServices() {
////    Log.d(TAG, "unregisterAllServices...")
////    Thread { jmDns.unregisterAllServices() }.start()
////}
//
//private val serviceListener = object : ServiceListener {
//    override fun serviceResolved(event: ServiceEvent?) {
//        Log.d(TAG, "serviceResolved...")
//    }
//
//    override fun serviceRemoved(event: ServiceEvent?) {
//        Log.d(TAG, "serviceRemoved...")
//    }
//
//    override fun serviceAdded(event: ServiceEvent) {
//        Log.d(TAG, "serviceAdded...")
//        val type = event.type
//        val name = event.name
//        val serviceInfo = event.info
//        Log.d(TAG, "type: $type, name: $name")
//        Log.d(TAG, serviceInfo.key)
//    }
//}
//
///**
// * addServiceListener
// */
//fun Context.addServiceListener() {
//    Log.d(TAG, "addServiceListener...")
//    Log.d(TAG, "ip: $ip")
//    Thread {
//        val wm = applicationContext.getSystemService(Context.WIFI_SERVICE) as WifiManager
//        val lock = wm.createMulticastLock(javaClass.simpleName)
//        lock.setReferenceCounted(false)
//        lock.acquire()
//        jmDns.addServiceListener("_http._tcp.local.", serviceListener)
//    }.start()
//}
//
///**
// * removeServiceListener
// */
//fun Context.removeServiceListener(){
//    Thread {
//        jmDns.removeServiceListener("_http._tcp.local.", serviceListener)
//        jmDns.close()
//    }.start()
//}
//
