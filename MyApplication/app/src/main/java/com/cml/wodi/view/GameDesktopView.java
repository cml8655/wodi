package com.cml.wodi.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

public class GameDesktopView extends ViewGroup {

    public GameDesktopView(Context context) {
        super(context);
    }

    public GameDesktopView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public GameDesktopView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

        int count = getChildCount();

        for (int i = 0; i < count; i++) {
            View v = getChildAt(i);
            v.layout(l, t, r, b);
        }

    }
}
