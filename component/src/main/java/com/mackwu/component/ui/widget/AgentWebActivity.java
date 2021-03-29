package com.mackwu.component.ui.widget;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

import com.just.agentweb.AgentWeb;
import com.mackwu.component.R;
import com.mackwu.component.ui.BaseTestActivity;

import butterknife.BindView;

/**
 * ===================================================
 * Created by MackWu on 2020/12/14 15:31
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 */
public class AgentWebActivity extends BaseTestActivity {

    @BindView(R.id.ll_container)
    LinearLayout llContainer;

    private AgentWeb agentWeb;

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {
        String url = "https://www.amazon.com/gp/video/detail/B0892NW6PN/ref=atv_dl_rdr?tag=justwatch09-20";
//        String url = "https://watch.amazon.com/detail?asin=B0892NW6PN&camp=1789&creativeASIN=B0892NW6PN&ie=UTF8&linkCode=xm2&tag=justwatch09-20";
        agentWeb = AgentWeb.with(this)
                .setAgentWebParent(llContainer, new LinearLayout.LayoutParams(-1, -1))
                .useDefaultIndicator(Color.BLUE)
                .setMainFrameErrorView(R.layout.layout_web_error, R.id.ll_web_error)
                .createAgentWeb()
                .ready()
                .go(url);

    }

}
