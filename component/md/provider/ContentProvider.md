

什么是ContentProvider？
ContentResolver的注册和通知？
ContentResolver的增、删、改、查方法实现对共享数据的操作？
ContentObserver监听数据是否发生了变化来对应的刷新页面？



java.lang.SecurityException: Failed to find provider com.mackwu.test.observer for user 0; expected to find a valid ContentProvider for this authority？
原因：Android8.0以上包括8.0


ContentProvider的生命周期？



## 什么是ContentProvider？
ContentProvider的作用是为不同的应用之间数据共享，提供统一的接口。安卓系统中应用内部的数据是对外隔离的，要想让其它应用能使用自己的数据（例如通讯录）就用到了ContentProvider
ContentProvider用uri来标识其他应用要访问的数据。其他应用通过ContentResolver来访问ContentProvider提供的数据。
ContentResolver通过uri来定位要访问的数据



## ContentResolver的注册？
authorities唯一标识ContentProvider，其他的应用可以通过authorities获取他提供的数据。默认android:exported="true"，可以被其他应用使用
```
        <provider
            android:authorities="com.mackwu.component.other.provider.student.StudentProvider"
            android:name=".provider.StudentProvider" />
```








ContentResolver注册ContentObserver来监听数据是否发生了变化，通过通知notifyChange
```
contentResolver.registerContentObserver
contentResolver.notifyChange
uri格式：content://authority/path
authority：要用contentProvider注册的authority
path：任意值可以为空，有值的情况注册和通知需要使用相同的path
```



