package com.mackwu.component;

import android.view.View;
import android.widget.Button;

import androidx.lifecycle.Observer;

import com.mackwu.component.base.BaseFragment;
import com.mackwu.component.bean.User;
import com.mackwu.component.jetpack.LifecycleActivity;
import com.mackwu.component.jetpack.LiveDataActivity;
import com.mackwu.component.jetpack.UserLiveData;
import com.mackwu.component.jetpack.ViewModelActivity;
import com.mackwu.component.util.ActivityStartUtil;

/**
 * ===================================================
 * Created by MackWu on 2020/6/19 23:43
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 */
public class JetpackFragment extends BaseFragment {

    private Button btnLifeCycle;
    private UserLiveData userLiveData;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_jetpack;
    }

    @Override
    public void initView(View view) {
        btnLifeCycle = view.findViewById(R.id.btn_lifecycle);

        //
        btnLifeCycle.setOnClickListener(v -> ActivityStartUtil.startActivity(this, LifecycleActivity.class));
        view.findViewById(R.id.btn_view_model).setOnClickListener(v -> ActivityStartUtil.startActivity(this, ViewModelActivity.class));
        view.findViewById(R.id.btn_live_data).setOnClickListener(v -> ActivityStartUtil.startActivity(this, LiveDataActivity.class));

        //
        userLiveData = UserLiveData.getInstance();
        userLiveData.observe(this, user -> btnLifeCycle.setText(user.getName()));
    }
}
