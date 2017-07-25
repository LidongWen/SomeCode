package com.wenld.app_retrofit;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by wenld on 2017/7/24.
 */

public class BaseViewGroup extends ViewGroup {
    public BaseViewGroup(Context context) {
        super(context);
    }

    public BaseViewGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public BaseViewGroup(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec,heightMeasureSpec);
        View child;
        for (int i = 0; i < getChildCount(); i++) {
            child = getChildAt(i);
            child.measure(0, 0);
        }
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        View child;
        int top = 0;
        int bottom = 0;
        for (int i = 0; i < getChildCount(); i++) {
            child = getChildAt(i);
            bottom = top + child.getMeasuredHeight();
            child.layout(0, top, r, bottom);
            top = bottom;
        }
    }
}
