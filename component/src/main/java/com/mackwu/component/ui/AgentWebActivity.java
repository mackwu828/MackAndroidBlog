package com.mackwu.component.ui;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

import com.just.agentweb.AgentWeb;
import com.mackwu.base.BaseActivity;
import com.mackwu.base.viewmodel.BaseViewModel;
import com.mackwu.component.R;
import com.mackwu.component.databinding.WidgetActivityAgentWebBinding;

/**
 * ===================================================
 * Created by MackWu on 2020/12/14 15:31
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 */
public class AgentWebActivity extends BaseActivity<BaseViewModel, WidgetActivityAgentWebBinding> {

    private AgentWeb agentWeb;

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {
        String url = "https://www.youtube.com/watch?v=XWhZDQkq0bw";
        agentWeb = AgentWeb.with(this)
                .setAgentWebParent(binding.llContainer, new LinearLayout.LayoutParams(-1, -1))
                .useDefaultIndicator(Color.BLUE)
                .setMainFrameErrorView(R.layout.layout_web_error, R.id.ll_web_error)
                .createAgentWeb()
                .ready()
                .go(url);
    }

}
