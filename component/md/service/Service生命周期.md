
如果在Activity中启动服务后，即使Activity被销毁，服务仍在后台运行，需要手动停止服务。如果启动服务所在的进程被系统杀死，服务仍会被销毁。

## onCreate

## onStartCommand

## onDestroy

## onBind

## onUnbind

## startService
首次startService。onCreate => onStartCommand。
```
08-23 15:22:53.316 2604-2604/com.mackwu.service D/TAG: onCreate...
08-23 15:22:53.317 2604-2604/com.mackwu.service D/TAG: onStartCommand...
```

继续点击startService，只执行onStartCommand。
```
08-23 15:22:56.956 2604-2604/com.mackwu.service D/TAG: onStartCommand...
08-23 15:22:57.652 2604-2604/com.mackwu.service D/TAG: onStartCommand...
08-23 15:22:57.952 2604-2604/com.mackwu.service D/TAG: onStartCommand...
```

退出Activity，onDestroy并没有执行。点击stopService，执行onDestroy。
```
08-23 15:23:10.132 2604-2604/com.mackwu.service D/TAG: onDestroy...
```