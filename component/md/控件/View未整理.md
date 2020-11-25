

## 水波纹效果
https://www.jianshu.com/p/13eb4574e988

## TextView去掉上下间距
```
android:includeFontPadding="false"
```

## ScrollView滚动条颜色设置
- fadeScrollbars 不消失
= scrollbarSize 滚动条宽度
= scrollbarThumbVertical 滚动条滚动颜色
= scrollbarTrackVertical 滚动条默认颜色
```
        android:fadeScrollbars="false"
        android:scrollbarSize="@dimen/dp_8"
        android:scrollbarStyle="outsideOverlay"
        android:scrollbarThumbVertical="@color/blue_0089C7"
        android:scrollbarTrackVertical="@android:color/white"
```


## imeOptions设置了不起作用
```
        android:inputType="text"
        android:singleLine="true"
```

## 自动弹出软键盘 android:windowSoftInputMode="stateAlwaysVisible" 不起作用
需要让editText获取到焦点
```
et.requestFocus();
```

## getDimension()这个方法会将原有的值以px为单位进行转换
所以在动态设置字体大小时，要设置单位为PX。默认是SP
```
    setTextSize(TypedValue.COMPLEX_UNIT_PX, size);
```