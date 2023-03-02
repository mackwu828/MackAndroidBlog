package com.mackwu.component.ui.view.recycler;

import android.view.View;

import java.util.ArrayList;

/**
 * @author MackWu
 * @since 2022/8/15 16:15
 */
public class MyRecycleBin {

    View[] activeViews = new View[0];
    int firstActivePosition;
    private ArrayList<View>[] scrapViews;

    void fillActiveViews(int childCount, int firstActivePosition) {
        if (activeViews.length < childCount) {
            activeViews = new View[childCount];
        }
        this.firstActivePosition = firstActivePosition;
        View[] activeViews = this.activeViews;

    }

    View getActiveView(int position) {
        int index = position - firstActivePosition;
        View[] activeViews = this.activeViews;
        if (index >= 0 && index < activeViews.length) {
            final View match = activeViews[index];
            activeViews[index] = null;
            return match;
        }
        return null;
    }

}
