

什么是Fragment？Fragment可以简单理解为放在Activity中的控件。他拥有自己的生命周期，可以和用户交互，他的交互都是通过FragmentManager实现。
Fragment的添加方式？1. 在xml中使用fragment标签 2. replace  3. show和hide
Fragment的回退栈？Fragment默认不添加到回退栈，按返回键时会退出所有的Fragment。
commit和commitAllowingStateLoss的区别？


java.lang.IllegalStateException: Can not perform this action after onSaveInstanceState？
复现：延时调用commit。
原因：
解决：使用commitAllowingStateLoss。

java.lang.IllegalStateException: Fragment already added？
复现：快速点击按钮，多次执行commit。
原因：系统在执行add fragment时如果已经add过了当前fragment，则不允许再add，add就报异常。
解决：判断isAdded()有时还是有问题，会返回false，因为事务的提交是异步的
```
        try {
            FragmentManager manager = activity.getSupportFragmentManager();
            manager.beginTransaction().remove(this).commit();
            show(manager, null);
        } catch (Exception e) {
            e.printStackTrace();
        }
```





Fragment的生命周期？
Fragment嵌套？
Fragment重叠？
Fragment和Activity的通信？
Fragment保存上次的页面？
系统的Fragment？DialogFragment、ListFragment、PreferenceFragment



## Fragment的添加方式？
### 在xml中使用fragment标签
```
    <fragment
        android:id="@+id/fragment_home"
        android:name="com.mackwu.component.fragment.HomeFragment"
        android:layout_width="match_parent"
        android:layout_height="match_parent" />
```

### replace
动态添加Fragment。在xml中创建一个布局如FrameLayout，然后通过 `SupportFragmentManager` 进行显示
```
    <FrameLayout
        android:id="@+id/fl_container"
        android:layout_width="match_parent"
        android:layout_height="match_parent" />
```

```
    replaceFragment(R.id.fl_container, HomeFragment.class);

    public void replaceFragment(final int containerViewId, @NonNull final Class<? extends Fragment> clazz) {
        Fragment fragment = instantiate(clazz);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(containerViewId, fragment);
        transaction.commit();
    }
```

### show和hide
```
    public void showFragment(final int containerViewId, @NonNull final Class<? extends Fragment> clazz) {
        Fragment fragment = instantiate(clazz);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        if (!fragment.isAdded()) {
            transaction.add(containerViewId, fragment);
        } else {
            transaction.show(fragment);
        }
        transaction.commit();
    }

    public void hideFragment(@NonNull final Class<? extends Fragment> clazz) {
        Fragment fragment = instantiate(clazz);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        if (fragment.isVisible()) {
            transaction.hide(fragment);
        }
        transaction.commit();
    }
```
    

## Fragment的回退栈？
Fragment默认不添加到回退栈，按返回键时会退出所有的Fragment。如果Fragment被加入了回退栈，则按返回键时会显示之前显示的Fragment。
```
          FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
          transaction.addToBackStack(null); // 将Fragment添加到回退栈中
          transaction.replace(containerViewId, fragment);
          transaction.commit();
```


## commit和commitAllowingStateLoss的区别？
Fragment的控制采用事务方式执行，在事务执行之前，用户可能已经点击了返回键，导致Activity的onSaveInstanceState()方法被调用，在保存当前activity实例的状态以备恢复
在onSaveInstanceState()方法中会将一个成员变量mStateSaved设为true。

```
    @see onSaveInstanceState
    @see android.support.v4.app.FragmentManagerImpl.saveAllState
```
采用commit()方式提交事务，在执行时会检查mStateSaved的值，若为true，则会抛出异常。
因此事务必须保证在用户点击返回键之前执行。而采用commitAllowingStateLoss()方式提交事务，在执行时不会检查mStateSaved的值，不会发生异常。
```
    @see android.support.v4.app.FragmentManagerImpl.checkStateLoss
```

模拟: 延迟commit()方式提交事务，按返回键，出现异常
```
    java.lang.IllegalStateException: Can not perform this action after onSaveInstanceState
```