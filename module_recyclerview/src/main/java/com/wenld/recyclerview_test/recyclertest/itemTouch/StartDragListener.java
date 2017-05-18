package com.wenld.recyclerview_test.recyclertest.itemTouch;

import android.support.v7.widget.RecyclerView;

/**
 * Created by wenld on 2017/4/23.
 */

public interface StartDragListener {
    /**
     * 该接口用于需要主动回调拖拽效果的
     * @param viewHolder
     */
    public void onStartDrag(RecyclerView.ViewHolder viewHolder);

}
