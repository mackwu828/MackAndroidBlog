package com.mackwu.component.ui.view.util.decoration;

import android.graphics.Rect;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class SpaceSectionItemDecoration extends SpaceItemDecoration {

    int ignoredItemViewType;

    public SpaceSectionItemDecoration(int left, int top, int right, int bottom, int ignoredItemViewType) {
        super(left, top, right, bottom);
        this.ignoredItemViewType = ignoredItemViewType;
    }

    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, RecyclerView parent, @NonNull RecyclerView.State state) {
        int position = parent.getChildAdapterPosition(view);
        if (parent.getAdapter() != null) {
            int itemViewType = parent.getAdapter().getItemViewType(position);
            if (itemViewType == ignoredItemViewType) {
                return;
            }
        }
        super.getItemOffsets(outRect, view, parent, state);
    }

}
