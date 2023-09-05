package com.mackwu.component.ui.viewmodel;

import com.mackwu.base.viewmodel.BaseViewModel;
import com.mackwu.component.R;
import com.mackwu.component.bean.RecycleItem;

import java.util.ArrayList;
import java.util.List;

/**
 * ===================================================
 * Created by MackWu on 2022/3/14 18:01
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 */
public class RecyclerViewModel extends BaseViewModel {

    public List<RecycleItem> getData() {
        List<RecycleItem> data = new ArrayList<>();
        data.add(new RecycleItem("2022-1-3", R.drawable.home_bg_3));
        data.add(new RecycleItem("2022-1-1", R.drawable.home_bg_1));
        data.add(new RecycleItem("2022-1-2", R.drawable.home_bg_2));
        data.add(new RecycleItem("2022-1-3", R.drawable.home_bg_3));
        data.add(new RecycleItem("2022-1-3", R.drawable.home_bg_3));
        data.add(new RecycleItem("2022-1-1", R.drawable.home_bg_1));
        data.add(new RecycleItem("2022-1-2", R.drawable.home_bg_2));
//        data.add(new RecycleItem("2022-1-3", R.drawable.home_bg_3));
//        data.add(new RecycleItem("2022-1-1", R.drawable.home_bg_1));
//        data.add(new RecycleItem("2022-1-2", R.drawable.home_bg_2));
//        data.add(new RecycleItem("2022-1-3", R.drawable.home_bg_3));
//        data.add(new RecycleItem("2022-1-1", R.drawable.home_bg_1));
//        data.add(new RecycleItem("2022-1-2", R.drawable.home_bg_2));
//        data.add(new RecycleItem("2022-1-3", R.drawable.home_bg_3));
//        data.add(new RecycleItem("2022-1-3", R.drawable.home_bg_3));
        return data;
    }

    public List<RecycleItem> getSectionData() {
        List<RecycleItem> data = new ArrayList<>();
        data.add(new RecycleItem("2022-1-1", R.drawable.home_bg_1, true));
        data.add(new RecycleItem("2022-1-2", R.drawable.home_bg_2));
        data.add(new RecycleItem("2022-1-3", R.drawable.home_bg_3));
        data.add(new RecycleItem("2022-1-1", R.drawable.home_bg_1));
        data.add(new RecycleItem("2022-1-2", R.drawable.home_bg_2, true));
        data.add(new RecycleItem("2022-1-3", R.drawable.home_bg_3));
        data.add(new RecycleItem("2022-1-1", R.drawable.home_bg_1));
        data.add(new RecycleItem("2022-1-2", R.drawable.home_bg_2, true));
        data.add(new RecycleItem("2022-1-3", R.drawable.home_bg_3));
        data.add(new RecycleItem("2022-1-3", R.drawable.home_bg_3));
        return data;
    }

    public List<RecycleItem> getData2() {
        List<RecycleItem> data = new ArrayList<>();
        data.add(new RecycleItem("2023-1-1", R.drawable.ic_launcher_background));
        data.add(new RecycleItem("2023-2-1", R.drawable.ic_launcher_background));
        data.add(new RecycleItem("2023-3-1", R.drawable.ic_launcher_background));
        data.add(new RecycleItem("2023-4-1", R.drawable.ic_launcher_background));
        data.add(new RecycleItem("2023-5-1", R.drawable.ic_launcher_background));
        return data;
    }

}
