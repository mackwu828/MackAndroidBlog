package com.mackwu.component.ui.adapter;

import android.util.SparseIntArray;
import android.widget.SectionIndexer;

import androidx.annotation.NonNull;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.mackwu.component.bean.RecycleItem;

import java.util.List;

/**
 * @author MackWu
 * @since 2023/1/3 17:33
 */
public class SectionAdapter extends BaseQuickAdapter<RecycleItem, BaseViewHolder> implements SectionIndexer {

    private SparseIntArray positionForSections;
    private SparseIntArray sectionForPositions;

    public SectionAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder viewHolder, RecycleItem recycleItem) {

    }

    @Override
    public Object[] getSections() {
        positionForSections = new SparseIntArray();
        sectionForPositions = new SparseIntArray();


        //
        List<RecycleItem> data = getData();


        return new Object[0];
    }

    @Override
    public int getPositionForSection(int sectionIndex) {
        return positionForSections.get(sectionIndex);
    }

    @Override
    public int getSectionForPosition(int position) {
        return sectionForPositions.get(position);
    }
}
