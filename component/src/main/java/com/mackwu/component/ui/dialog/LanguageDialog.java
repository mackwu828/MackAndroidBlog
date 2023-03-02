package com.mackwu.component.ui.dialog;

import android.os.Bundle;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.mackwu.base.viewmodel.BaseViewModel;
import com.mackwu.component.R;
import com.mackwu.component.bean.Language;
import com.mackwu.component.databinding.DialogLanguageBinding;

import java.util.ArrayList;
import java.util.List;

/**
 * @author MackWu
 * @since 2022/7/29 17:56
 */
public class LanguageDialog extends LifecycleDialog<BaseViewModel, DialogLanguageBinding> {

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {
        List<Language> languages = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            Language language = new Language();
            language.setName(i + "");
            languages.add(language);
        }

        // adapter
        LanguageAdapter adapter = new LanguageAdapter(languages);
        adapter.setOnItemClickListener((a, v, position) -> {
            dismiss();
        });

        // verticalGridView
        binding.verticalGridView.setAdapter(adapter);
        binding.verticalGridView.scrollToPosition(64);
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {

    }

    static class LanguageAdapter extends BaseQuickAdapter<Language, BaseViewHolder> {

        public LanguageAdapter(List<Language> languages) {
            super(R.layout.layout_item_language, languages);
        }

        @Override
        protected void convert(@NonNull BaseViewHolder helper, Language language) {
            helper.setText(R.id.tv_language_name, language.getName());
            ImageView ivLanguageOption = helper.getView(R.id.iv_language_option);
        }
    }
}
