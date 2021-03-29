    

Fragment的添加方式？
Fragment的生命周期？ 
Fragment的回退栈？Fragment默认不添加到回退栈，按返回键时会退出activity。
  
  
## Fragment的回退栈？
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
  
    
    
    
    
    
    
    
    private Handler handler = new Handler(Looper.getMainLooper());
    private Runnable delayReplaceRunnable;

    protected Fragment instantiate(@NonNull final Class<? extends Fragment> clazz) {
        return getSupportFragmentManager().getFragmentFactory().instantiate(getClassLoader(), clazz.getName());
    }

    @Override
    public void replaceFragment(final int containerViewId, @NonNull final Class<? extends Fragment> clazz) {
        Fragment fragment = instantiate(clazz);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(containerViewId, fragment);
        transaction.commit();
    }

    @Override
    public void delayReplaceFragment(final int containerViewId, @NonNull final Class<? extends Fragment> clazz, long delayMillis) {
        if (null == delayReplaceRunnable) {
            delayReplaceRunnable = () -> replaceFragment(containerViewId, clazz);
        }
        handler.removeCallbacks(delayReplaceRunnable);
        handler.postDelayed(delayReplaceRunnable, delayMillis);
    }

    @Override
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

    @Override
    public void hideFragment(@NonNull final Class<? extends Fragment> clazz) {
        Fragment fragment = instantiate(clazz);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        if (fragment.isVisible()) {
            transaction.hide(fragment);
        }
        transaction.commit();
    }
