package com.mackwu.view.basic;

import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.mackwu.view.R;
import com.mackwu.xmvc.BaseActivity;

import butterknife.BindView;

public class EditTextActivity extends BaseActivity {

    @BindView(R.id.et_test)
    EditText etTest;

    @Override
    public int getLayoutId() {
        return R.layout.activity_edit_text;
    }

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {
        etTest.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {

            }
            return false;
        });
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {

    }

}
