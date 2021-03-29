
ViewModel有什么用？负责处理业务逻辑。相对于MVC，将业务逻辑和用户交互逻辑分离。相对于MVP，相当于MVP中的Presenter。


如何创建ViewModel？Activity、Fragment、View、Object
Fragment的哪个生命周期中创建ViewModel最合适？https://www.reddit.com/r/androiddev/comments/8sstfg/which_is_the_best_place_to_initialise_viewmodel/

创建ViewModel的ViewModelProvider？
ViewModelProvider的构造函数第一个是ViewModelStoreOwner，第二个是Factory。
ViewModelStoreOwner的子类有哪些？Activity的父类ComponentActivity、Fragment
Factory的子类有哪些？SavedStateViewModelFactory、NewInstanceFactory、AndroidViewModelFactory


SavedStateViewModelFactory


## 如何创建ViewModel?
- Activity
```
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MainViewModel viewModel = new ViewModelProvider(this).get(MainViewModel.class);
    }
```
- Fragment