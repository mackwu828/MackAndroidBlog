ContentProvider的作用是为不同的应用之间数据共享，提供统一的接口。
安卓系统中应用内部的数据是对外隔离的，要想让其它应用能使用自己的数据（例如通讯录）就用到了ContentProvider

ContentProvider用uri来标识其他应用要访问的数据。
其他应用通过ContentResolver来访问ContentProvider提供的数据。
ContentResolver通过uri来定位要访问的数据

## 资料


## 生命周期


## 注册
authorities唯一标识ContentProvider，其他的应用可以通过authorities获取他提供的数据。默认android:exported="true"，可以被其他应用使用
```xml
        <provider
            android:authorities="com.mackwu.component.provider.StudentProvider"
            android:name=".provider.StudentProvider" />
```

## 通过ContentResolver的增、删、改、查方法实现对共享数据的操作。
## 通过注册ContentObserver来监听数据是否发生了变化来对应的刷新页面。

