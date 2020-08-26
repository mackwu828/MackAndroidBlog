
## 一、设置宽高、位置、透明度、是否可以点击
```
    /**
     * 设置非全屏的Activity
     *
     * @param width          Activity宽度
     * @param height         Activity高度
     * @param x              x轴位置
     * @param y              y轴位置
     * @param dimAmount      Activity之外部分透明程度
     * @param isTouchOutside 响应activity对窗口之外的触摸消息
     */
    public static void setNotFullActivity(Activity activity, int width, int height, int x, int y, float dimAmount, boolean isTouchOutside) {
        Window window = activity.getWindow();
        WindowManager.LayoutParams attributes = window.getAttributes();
        attributes.width = width;
        attributes.height = height;
        attributes.x = x;
        attributes.y = y;
        attributes.dimAmount = dimAmount;
        window.setAttributes(attributes);
        if (isTouchOutside) window.setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL, WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL);
    }
```

## 二、设置主题
```
    <!--
        非全屏的Activity
        <item name="android:windowCloseOnTouchOutside">false</item> 点击activity窗口之外时，是否关闭activity。默认false
        <item name="android:windowIsFloating">true</item> 是否悬浮在其他activity之上。
    -->
    <style name="Theme.NonFullActivity" parent="AppTheme">
        <item name="android:windowCloseOnTouchOutside">false</item>
        <item name="android:windowIsFloating">true</item>
    </style>
```

```
        <activity
            android:name=".activity.NonFullActivity"
            android:theme="@style/Theme.NonFullActivity" />
```