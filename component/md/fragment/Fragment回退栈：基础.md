
fragment默认不添加到回退栈，按返回键时会退出activity。


## 一、加入回退栈
如果fragment被加入了回退栈，则按返回键时会恢复之前的fragment。

```
        transaction.addToBackStack(null);
```

```
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.addToBackStack(null);
        transaction.replace(containerViewId, fragment);
        transaction.commit();
```

