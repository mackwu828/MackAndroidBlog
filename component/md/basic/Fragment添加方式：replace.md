
## 一、在xml中新建一个布局容易
```
    <FrameLayout
        android:id="@+id/fl_container"
        android:layout_width="match_parent"
        android:layout_height="match_parent" />
```


## 二、replace
```
    replaceFragment(R.id.fl_container, HomeFragment.class);
```

```
    public void replaceFragment(final int containerViewId, @NonNull final Class<? extends Fragment> clazz) {
        Fragment fragment = instantiate(clazz);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(containerViewId, fragment);
        transaction.commit();
    }
```