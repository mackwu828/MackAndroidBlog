

静态注册和动态注册广播


网络状态广播在动态注册的时候会立马触发?
系统发送的广播接受不到？
如何通过命令行发送广播？


## 网络状态广播在动态注册的时候会立马触发？
https://stackoverflow.com/questions/16427812/broadcastreceiver-onreceive-triggered-when-registered

## 系统发送的广播接受不到？
如果在清单文件中加了 `android:sharedUserId="android.uid.system"` 这个，需要给广播加保护权限。
https://stackoverflow.com/questions/49248539/sending-non-protected-broadcast-com-motorola-motocare-intent-trigger-java-lang-t/50240471#50240471

## 如何通过命令行发送广播？
```
adb shell am broadcast -a "XXX"
```