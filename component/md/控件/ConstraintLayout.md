


https://juejin.cn/post/6844904105979150349


## 相对约束
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


## 基线约束
```
layout_constraintBaseline_toBaselineOf
```

## 角度约束
```
app:layout_constraintCircle
```

## 倾向约束
```
app:layout_constraintHorizontal_bias="0.9" // 水平向右偏移90%
```
```
layout_constraintHorizontal_bias (0最左边 1最右边) 默认是0.5
layout_constraintVertical_bias (0最上边 1 最底边)
```

## 可见性约束
android:visibility="gone"属性会忽略margin，添加以下属性
```
layout_goneMarginStart
layout_goneMarginEnd
```



## 例子
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

一个控件在另一个控件的下方
```

```