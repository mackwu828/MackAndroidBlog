

## 设置启动模式
在AndroidManifest的activity标签中设置启动模式。Activity有四种启动模式：standard、singleTop、singleTask、singleInstance。默认是standard
```
        <activity android:name=".activity.LaunchModeActivity"
            android:launchMode="standard"/>
```

## Activity栈 
Activity是通过Activity栈管理的，当一个Activity启动时，系统会根据配置将Activity添加到栈中。  
如果用户点击返回或者finish结束了该Activity，那么该Activity会从栈中移除

## standard
Activity的默认启动方式。每次启动Activity都会新建一个Activity实例，然后添加到栈中。
## singleTop
## singleTask
## singleInstance
## 命令行查看Activity栈中的情况
```
adb shell
dumpsys activity | grep -i run
```
