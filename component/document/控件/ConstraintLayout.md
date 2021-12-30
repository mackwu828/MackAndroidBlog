


约束布局？https://juejin.cn/post/6844904105979150349

layout_constraintTop_toTopOf？
layout_constraintHorizontal_bias？
layout_goneMarginStart？

chain？


## layout_constraintTop_toTopOf相对约束
```
layout_constraintLeft_toLeftOf 把该控件的左边约束到某控件的左边
layout_constraintLeft_toRightOf
layout_constraintRight_toLeftOf
layout_constraintRight_toRightOf
layout_constraintTop_toTopOf
layout_constraintTop_toBottomOf
layout_constraintBottom_toTopOf
layout_constraintBottom_toBottomOf
layout_constraintBaseline_toBaselineOf
layout_constraintStart_toEndOf
layout_constraintStart_toStartOf
layout_constraintEnd_toStartOf
layout_constraintEnd_toEndOf
```

## chain?
```
app:layout_constraintVertical_chainStyle="packed"
```


## layout_constraintHorizontal_bias
```
app:layout_constraintHorizontal_bias="0.9" // 水平向右偏移90%。(0最左边 1最右边) 默认是0.5
app:layout_constraintVertical_bias="0.9" // 垂直向下偏移90%(0最上边 1 最底边)
```










居中。上下左右都约束到parent
```
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toTopOf="parent"
```

在最顶部
```
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toTopOf="parent"
```

在最底部
```
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintBottom_toBottomOf="parent"
```