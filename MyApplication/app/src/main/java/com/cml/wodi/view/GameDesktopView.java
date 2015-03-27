package com.cml.wodi.view;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.Configuration;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewPropertyAnimator;
import android.view.WindowManager;

public class GameDesktopView extends ViewGroup {

    private static final String TAG = "GameDesktopView";

    private int radius;
    private int padding = 10;
    private int childSize;

    public GameDesktopView(Context context) {
        super(context);
    }

    public GameDesktopView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public GameDesktopView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private OnClickListener listener = new OnClickListener() {
        @Override
        public void onClick(View v) {
            ObjectAnimator.ofFloat(v, "x", v.getX(), radius + padding-childSize/2).setDuration(2000).start();
            ObjectAnimator.ofFloat(v, "y", v.getY(), radius + padding-childSize/2).setDuration(2000).start();
        }
    };

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

        int count = getChildCount();

        float angle = 360 / count;

        for (int i = 0; i < count; i++) {

            View v = getChildAt(i);

            int width = v.getMeasuredWidth();
            int circleR = radius - width / 2;
            int halfWidth = width / 2;

            double radian = changeAngelToRadian(angle * i);

            int centerX = (int) (radius + circleR * Math.sin(radian)) + padding;
            int centerY = (int) (radius + circleR * Math.cos(radian)) + padding;

            Log.d(TAG, "center position:" + centerX + "," + centerY);

            v.layout(centerX - halfWidth, centerY - halfWidth, centerX + halfWidth, centerY + halfWidth);
            v.setOnClickListener(listener);
        }

    }

    private double changeAngelToRadian(float angle) {
        return Math.toRadians(angle);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        int width = getDefaultSize(getSuggestedMinimumWidth(), widthMeasureSpec);
        int height = getDefaultSize(getSuggestedMinimumHeight(), heightMeasureSpec);

        int size = Math.min(width, height);
        radius = (size - padding * 2) / 2;

        int count = getChildCount();

        for (int i = 0; i < count; i++) {
            measureChild(getChildAt(i), size, size);
        }

        childSize = getChildAt(0).getMeasuredWidth();

        setMeasuredDimension(size, size);
    }
}
