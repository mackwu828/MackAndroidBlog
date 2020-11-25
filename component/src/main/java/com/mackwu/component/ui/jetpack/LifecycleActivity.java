//package com.mackwu.component.ui.jetpack;
//
//import android.os.Bundle;
//
//import androidx.annotation.Nullable;
//
//import com.mackwu.component.R;
//import com.mackwu.component.base.BaseTransactionActivity;
//
///**
// * ===================================================
// * Created by MackWu on 2020/6/19 23:50
// * <a href="mailto:wumengjiao828@163.com">Contact me</a>
// * <a href="https://github.com/mackwu828">Follow me</a>
// * ===================================================
// */
//public class LifecycleActivity extends BaseTransactionActivity {
//
//    private boolean flag = true;
//
//    @Override
//    public int getLayoutId() {
//        return R.layout.activity_lifecycle;
//    }
//
//    @Override
//    public void initView(@Nullable final Bundle savedInstanceState) {
//        //        MyLifecycleObserver lifecycleObserver = new MyLifecycleObserver();
////        getLifecycle().addObserver(lifecycleObserver);
//
//        findViewById(R.id.btn_test).setOnClickListener(v -> {
//            if (flag) {
//                replaceFragment(R.id.fl_container, HomeFragment.class);
//            } else {
//                replaceFragment(R.id.fl_container, UserFragment.class);
//            }
//            flag = !flag;
//        });
//    }
//
//    @Override
//    public void initData(@Nullable final Bundle savedInstanceState) {
//
//    }
//
//}
