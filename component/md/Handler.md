
Android提供了Handler和Looper来来满足线程间的通信


为什么会有Handler?
Handler的用法？1. 在主线程中创建Handler 2. 在子线程中创建Handler
如何用Handler实现定时器？


Handler原理/机制？
Handler、MessageQueue、Looper

IdleHandler
ThreadLocal、IntentService、BlockCanary



## 为什么会有Handler?
在开发中，经常需要处理一些耗时操作，如果在主线程中执行耗时操作，会导致卡顿，过长时间还会导致ANR。  
这个时候就需要将耗时操作移到子线程来执行，但是Android又规定只能在主线程中访问UI，如果在子线程中访问UI会抛出异常 `Only the original thread that created a view hierarchy can touch its views`。
而Android提供Handler就是为了解决在子线程中无法访问UI的问题。


