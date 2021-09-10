package com.mackwu.component.ui.widget;

import android.os.Bundle;

import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.mackwu.base.BaseActivity;
import com.mackwu.base.viewmodel.BaseViewModel;
import com.mackwu.component.databinding.WidgetActivityImageViewBinding;

/**
 * ===================================================
 * Created by MackWu on 2021/6/24 15:12
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 */
public class ImageViewActivity extends BaseActivity<BaseViewModel, WidgetActivityImageViewBinding> {

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {
        String url = "https://s3.amazonaws.com/CAPS-SSE/alexa_music_skills/906c/ask.skill.wordmark.2a6058e6806945e49ef430758390e56d/APP_WORDMARK_SVG?versionId=8RsM.buKjUG4JrxLALkQubAPR2qB_wDP&X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Date=20210624T074511Z&X-Amz-SignedHeaders=host&X-Amz-Expires=7200&X-Amz-Credential=AKIAWBV6LQ4QPPL3Q3H6%2F20210624%2Fus-east-1%2Fs3%2Faws4_request&X-Amz-Signature=4a43a251ee9d8609d939c99a3354df92da96e387a3fbcbf9a4679ed616898650";
//        String url = "http://mmbiz.qpic.cn/mmbiz/PwIlO51l7wuFyoFwAXfqPNETWCibjNACIt6ydN7vw8LeIwT7IjyG3eeribmK4rhibecvNKiaT2qeJRIWXLuKYPiaqtQ/0";
        Glide.with(getApplicationContext())
                .load(url)
                .into(binding.ivTest);
    }

}