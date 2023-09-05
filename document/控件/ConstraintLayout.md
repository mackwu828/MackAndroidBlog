


约束布局？https://juejin.cn/post/6844904105979150349


基础使用

多个View合并居中
设置宽高比


## 基础使用
- layout_constraintTop_toTopOf相对约束
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
- layout_constraintVertical_chainStyle


- layout_constraintHorizontal_bias
```
app:layout_constraintHorizontal_bias="0.9" // 水平向右偏移90%。(0最左边 1最右边) 默认是0.5
app:layout_constraintVertical_bias="0.9" // 垂直向下偏移90%(0最上边 1 最底边)
```


## 设置宽高比
```
app:layout_constraintDimensionRatio="h,4:3" 以宽度为基础设置高度。w:h=4:3 即h=(3/4)w

    <androidx.constraintlayout.widget.ConstraintLayout
        android:layout_width="wrap_content"
        android:layout_height="wrap_content">

        <ImageView
            android:id="@+id/iv_image"
            android:layout_width="match_parent"
            android:layout_height="0dp"
            android:scaleType="fitXY"
            app:layout_constraintDimensionRatio="H,4:3"
            app:layout_constraintStart_toStartOf="parent"
            app:layout_constraintTop_toTopOf="parent" />

    </androidx.constraintlayout.widget.ConstraintLayout>
    
    
app:layout_constraintDimensionRatio="w,1:2" 

```

## 多个View合并居中
```
    <androidx.constraintlayout.widget.ConstraintLayout
        android:layout_width="match_parent"
        android:layout_height="@dimen/dp_120"
        android:background="@color/colorPrimary"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toBottomOf="@id/btn_test">

        <TextView
            android:id="@+id/btn_111"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:layout_marginEnd="@dimen/dp_20"
            android:text="111"
            app:layout_constraintBottom_toTopOf="@+id/btn_222"
            app:layout_constraintEnd_toEndOf="parent"
            app:layout_constraintTop_toTopOf="parent"
            app:layout_constraintVertical_chainStyle="packed" />

        <TextView
            android:id="@+id/btn_222"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:layout_marginEnd="@dimen/dp_20"
            android:text="222"
            app:layout_constraintBottom_toBottomOf="parent"
            app:layout_constraintEnd_toEndOf="parent"
            app:layout_constraintTop_toBottomOf="@id/btn_111" />

    </androidx.constraintlayout.widget.ConstraintLayout>
```