## 目录
* [什么是Fragment](#什么是Fragment)
* Fragment生命周期
* fragemnt标签
* FragmentManager动态加载Fragment
* hide和show、replace和remove、detach和attach区别
* Fragment中嵌套Fragment
* Frgament重叠问题
* Fragment保存上次的页面
* Fragment和Activity的通信



### 什么是Fragment
```
 * A Fragment is a piece of an application's user interface or behavior
 * that can be placed in an {@link Activity}.  Interaction with fragments
 * is done through {@link FragmentManager}, which can be obtained via
 * {@link Activity#getFragmentManager() Activity.getFragmentManager()} and
 * {@link Fragment#getFragmentManager() Fragment.getFragmentManager()}.
```
Fragment可以简单理解为放在Activity中的控件。他拥有自己的生命周期，可以和用户交互，他的交互都是通过FragmentManager实现。

### Fragment生命周期

### fragemnt标签
