package com.mackwu.component.ui.view.select;

import java.util.List;

/**
 * @author MackWu
 * @since 2022/7/11 15:06
 */
public interface SelectAdapter<T> {

    List<T> getData();

    int getItemCount();

}
