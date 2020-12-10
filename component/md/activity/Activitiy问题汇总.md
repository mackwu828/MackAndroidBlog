
Activity启动方式
Activity启动模式
Activity生命周期
DeepLink是什么？
如何设置无标题的Activity？
如何设置非全屏的Activity？
如何隐藏状态栏？
如何获取根布局？



## 如何获取根布局？
```
        View view = LayoutInflater.from(this).inflate(R.layout.layout_test, null);
        FrameLayout rootView = findViewById(android.R.id.content);
        rootView.addView(view);
        View child = rootView.getChildAt(0);
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        layoutParams.topMargin = 80;
        child.setLayoutParams(layoutParams);
```


## 如何设置无标题的Activity？
方法1：在清单文件中设置主题
```
    <style name="AppTheme" parent="Theme.AppCompat.Light.NoActionBar">
        <!-- Customize your theme here. -->
        <item name="colorPrimary">@color/colorPrimary</item>
        <item name="colorPrimaryDark">@color/colorPrimaryDark</item>
        <item name="colorAccent">@color/colorAccent</item>
    </style>
```

方法2：在Activity的onCreate中设置
```
    @Override
    protected void onCreate(@Nullable final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
    }
```


## 如何隐藏状态栏（即设置全屏的Activity）？
```
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
```


## 如何设置非全屏的Activity？
1. 设置宽高、位置、透明度、是否可以点击
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
2. 在清单文件中添加主题
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

3. 给Activity设置主题
```
        <activity
            android:name=".activity.NonFullActivity"
            android:theme="@style/Theme.NonFullActivity" />
```