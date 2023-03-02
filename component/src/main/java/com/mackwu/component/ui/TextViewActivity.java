package com.mackwu.component.ui;

import android.os.Bundle;
import android.util.TypedValue;
import android.view.ViewTreeObserver;

import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.mackwu.base.BaseActivity;
import com.mackwu.base.util.Logger;
import com.mackwu.base.viewmodel.BaseViewModel;
import com.mackwu.component.R;
import com.mackwu.component.databinding.ActivityTextViewBinding;

/**
 * ===================================================
 * Created by MackWu on 2021/2/24 10:46
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 */
public class TextViewActivity extends BaseActivity<BaseViewModel, ActivityTextViewBinding> {

    String text = "习近平代表第十九届中央委员会向大会作了题为《高举中国特色社会主义伟大旗帜　为全面建设社会主义现代化国家而团结奋斗》的报告。习近平指出，中国共产党第二十次全国代表大" +
            "会，是在全党全国各族人民迈上全面建设社会主义现代化国家新征程、向第二个百年奋斗目标进军的关键时" +
            "刻召开的一次十分重要的大会。大会的主题是：高举中国特色社会主义伟大旗帜，全面贯彻新时代中国特色社" +
            "会主义思想，弘扬伟大建党精神，自信自强、守正创新，踔厉奋发、勇毅前行，为全面建设社会主义现代化国家、全面推进中华民族伟大复兴而团结奋斗。";

    String text2 = "不不不吃糖糖vv他v推塔v他v他v雨想法合计咔咔咔哦哦哦iUIi哦哦哦哦哦哦哦哦哦哦哦哦哦哦哦哦哦哦哦哦哦哦哦哦哦哦哦里面那个vv好：uu雨v他v邪恶呃呃邪恶邪恶显然吃体育部u不";

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {
        int minTextSize = getResources().getDimensionPixelSize(R.dimen.sp_24);
        int maxTextSize = getResources().getDimensionPixelSize(R.dimen.sp_50);
        Logger.d("minTextSize=" + minTextSize + ", maxTextSize=" + maxTextSize);

        binding.btnTest.setOnClickListener(v -> {
            binding.tvTest.setText(text2);
            binding.tvTest.setTextSize(TypedValue.COMPLEX_UNIT_PX, minTextSize);

            binding.tvTest.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                @Override
                public void onGlobalLayout() {
                    binding.tvTest.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                    binding.tvTest.setLayoutParams(new ConstraintLayout.LayoutParams(getResources().getDimensionPixelSize(R.dimen.dp_300), getResources().getDimensionPixelSize(R.dimen.dp_200)));
                    Logger.d("XXXX...  textSize=" + binding.tvTest.getTextSize() + ", lineCount=" + binding.tvTest.getLineCount());
                    binding.tvTest.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                        @Override
                        public void onGlobalLayout() {
                            binding.tvTest.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                            Logger.d("onGlobalLayout2222...  textSize=" + binding.tvTest.getTextSize() + ", lineCount=" + binding.tvTest.getLineCount());
                        }
                    });
                }
            });
        });
    }


}
