

```
adb devices 查看所有设备
adb disconnect 断开所有设备
adb connect 172.16.0.97:5555 连接设备。参数是设备的ip地址

adb shell
su 
adb root 获取root权限
adb remount 获取读写权限
mount -o remount,rw /system 获取读取权限
rm -rf 删除文件

adb install xxx.apk 安装apk。参数是apk的绝对路径
adb uninstall com.xxx.xxx 卸载apk。参数是包名


adb shell pm clear 包名 清除缓存
adb shell pm list packages -f 查看所有包名所在路径
adb shell pm list packages -f | findStr com.android.webview  xxx是关键字，用来过滤，可以是包名。
adb shell pm dump com.xxx.xxx | findstr "versionName" 查看应用版本号

keytool -list -v -keystore xxx.jks 查看签名文件的签名信息。参数是签名的绝对路径
keytool -printcert -file CERT.RSA 查看apk的签名信息。apk重命名zip，解压。进入META-INF目录，找到CERT.RSA文件


adb shell cat /sys/class/net/wlan0/address 获取wifi mac地址
adb shell cat /sys/class/net/eth0/address 获取有线mac地址


adb pull /system/app/temp/xxx.text   获取指定目录文件到
adb push xxx.text /system/app/temp 发送文件到指定目录


adb shell input keyevent 82 模拟按键。82 menu键、3 home键、4 back键
adb shell getevent -l 获取按键事件

adb shell getprop xxx 查看系统属性
adb shell setprop xxx xxx 设置系统属性

adb shell cat /proc/cpuinfo 查看cpu信息
adb shell getprop ro.product.cpu.abi 查看cpu架构

adb shell am force-stop 包名 杀死指定应用/进程


adb shell wm size 获取屏幕分辨率
adb shell wm density 获取屏幕密度
adb shell wm density 320 设置屏幕密度

adb shell am start 包名/activity名称  启动activity
adb shell am broadcast -a action名称 发送广播
adb shell am startservice -n 包名/service名称 启动service
adb shell am startservice -a action名称 启动service

gradlew :app:assembleXXXDebug  打包apk。app模块名称，XXX渠道名称，debug、acc

adb shell date "110110362019.00" 设置系统时间。如2019/11/1 10:36:00
```


## 查看cpu信息
```
adb shell cat /proc/cpuinfo 查看cpu信息
adb shell getprop ro.product.cpu.abi 查看cpu架构
```

```
processor       : 0
model name      : ARMv7 Processor rev 0 (v7l)
BogoMIPS        : 54.00
Features        : half thumb fastmult vfp edsp neon vfpv3 tls vfpv4 idiva idivt vfpd32 lpae evtstrm aes pmull sha1 sha2 crc32
CPU implementer : 0x41
CPU architecture: 7
CPU variant     : 0x1
CPU part        : 0xd05
CPU revision    : 0
```

- processor: 表示第几个核。
- BogoMIPS: 伪MIPS，用于测量CPU速度
- Features: 表示当前CPU所支持的特性，比如neon，vfp等。
- CPU architecture: 7表示arm-v7，8表示arm-v8。

