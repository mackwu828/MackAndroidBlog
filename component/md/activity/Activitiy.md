
Activity启动方式有哪些？startActivity、startActivityForResult
启动Activity的最佳方式？
如何用命令行启动Activity？adb shell am start com.mackwu.component/.MainActivity
如何通过包名启动Activity？
如何通过包名和类名启动Activity？
如何通过action启动Activity？
什么是Activity栈？
如何用命令行查看Activity栈？adb shell dumpsys activity | grep -i run
什么是Activity启动模式? standard、singleTop、singleTask、singleInstance。默认是standard
Activity生命周期？





什么是DeepLink? 如何配置DeepLink?



如何获取根布局？
如何设置无标题的Activity？
如何设置非全屏的Activity？
如何隐藏状态栏？



## Activity启动方式有哪些？
- startActivity
- startActivityForResult

## 启动Activity的最佳方式？
```
    public static void start(Context context) {
        Intent intent = new Intent(context, SecondActivity.class);
        context.startActivity(intent);
    }
```

## 如何通过包名启动Activity？
```
            Intent intent = context.getPackageManager().getLaunchIntentForPackage(packageName);
            if (intent != null) {
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
```

## 如何通过包名和类名启动Activity？
```
            Intent intent = new Intent();
            intent.setComponent(new ComponentName(packageName, activityName));
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
```

## 如何通过action启动Activity？
```
            Intent intent = new Intent(action);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
```

## 什么是Activity栈？
Activity是通过Activity栈管理的，当一个Activity启动时，系统会根据配置将Activity添加到栈中。如果用户点击返回或者finish结束了该Activity，那么该Activity会从栈中移除。

## Activity启动模式？
- standard: Activity的默认启动方式。每次启动activity都会新建一个activity实例，然后添加到栈中。
- singleTop。如果启动的activity在栈顶，则会复用栈顶的activity。否则会创建一个新的activity实例。
    activity被复用时，它的onCreate、onStart不会被系统调用，会调用onNewIntent方法。
- singleTask。如果启动的activity在栈中，会复用栈中的activity，把该activity上面的所有activity销毁，该activity在栈顶。
- singleInstance。单独存在一个activity栈中。





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