



ANR: https://www.dazhuanlan.com/2019/12/24/5e01bc4b294fe/

        * ANR超时时间
        * ANR产生的原因
        * 如何避免ANR
        * 如何定位ANR
        * 分析trace.txt
        * ANR常见类型
        * ANR分析工具
            * TraceView
            
# AMR常见类型

* 主线程长时间处理io操作引起的ANR
```
Subject:keyDispatchingTimedOut
CPU usage from 2550ms to -2814ms ago:
5%187/system_server: 3.5% user + 1.4% kernel / faults: 86 minor 20major
4.4% 1134/com.android.email: 0.7% user + 3.7% kernel /faults: 38 minor 19 major
4% 372/com.android.eventstream: 0.7%user + 3.3% kernel / faults: 6 minor
1.1% 272/com.android.phone:0.9% user + 0.1% kernel / faults: 33 minor
0.9%252/com.android.systemui: 0.9% user + 0% kernel
0%409/com.android.eventstream.telephonyplugin: 0% user + 0% kernel /faults: 2 minor
0.1% 632/com.android.devicemonitor: 0.1% user + 0%kernel
100%TOTAL: 6.9% user + 8.2% kernel + 84%iowait
```

* 内存不足引起的ANR
```
"main"prio=5 tid=3 VMWAIT
|group="main" sCount=1 dsCount=0 s=N obj=0x40026240self=0xbda8
| sysTid=1815 nice=0 sched=0/0 cgrp=unknownhandle=-1344001376
atdalvik.system.VMRuntime.trackExternalAllocation(NativeMethod)
atandroid.graphics.Bitmap.nativeCreate(Native Method)
atandroid.graphics.Bitmap.createBitmap(Bitmap.java:468)
atandroid.view.View.buildDrawingCache(View.java:6324)
atandroid.view.View.getDrawingCache(View.java:6178)
atandroid.view.ViewGroup.drawChild(ViewGroup.java:1541)
```

* 线程死锁引起的ANR
* 跑Money引发的ANR