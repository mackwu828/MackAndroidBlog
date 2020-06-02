Memory Monitor
Heap Viewer
LeakCanary
Android Studio自带的性能分析工具，可以监控应用的CPU、内存、网络、电量使用情况。



## 如何检测内存泄漏

### 1. 操作要检测的界面，然后退出界面

### 2. 手动GC，然后Dump java heap

### 3. 在 Heap Dump 中选择安装包名排序，找到自己的包名
Allocations：Java堆中的实例个数
Shallow Size：Java堆中分配实际大小
Retained Size：这个类的所有实例保留的内存总大小（并非实际大小）