package com.mackwu.component.ui.viewmodel;

import com.mackwu.base.viewmodel.BaseViewModel;
import com.mackwu.component.R;
import com.mackwu.component.bean.RecycleItem;

import java.util.ArrayList;
import java.util.List;

/**
 * @author MackWu
 * @since 2023/3/31 16:39
 */
public class OverlapViewModel extends BaseViewModel {

    public List<RecycleItem> getData() {
        List<RecycleItem> data = new ArrayList<>();
        data.add(new RecycleItem("2022-1-1", R.drawable.icon_home_weather_unfocus));
        data.add(new RecycleItem("2022-1-2", R.drawable.icon_home_setting_unfocus));
        data.add(new RecycleItem("2022-1-3", R.drawable.icon_home_source_unfocus));
        data.add(new RecycleItem("2022-1-1", R.drawable.icon_home_recently_unfocus));
        data.add(new RecycleItem("2022-1-2", R.drawable.icon_home_search_unfocus));
        return data;
    }
}
