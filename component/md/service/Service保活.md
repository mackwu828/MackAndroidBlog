



## 提高Service的优先级？
为防止Service被系统回收，可以通过提高优先级解决。1000是最高优先级，数字越小，优先级越低
```
        <service android:name=".service.TestService">
            <intent-filter android:priority="1000"/>
        </service>
```


## 设置为系统服务？
将进程设置为系统进程，那么服务也就变成系统服务。添加 `android:persistent="true"` 到 application 中
```
    <application
        android:name=".ComponentApp"
        android:persistent="true"
        ...>
```


## 设置为前台服务


## 利用ANDROID的系统广播检查Service的运行状态，如果被杀掉，就再起来？
利用的系统广播是Intent.ACTION_TIME_TICK，这个广播每分钟发送一次，我们可以每分钟检查一次Service的运行状态，如果已经被结束了，就重新启动Service。