package com.cml.wodi.view;

import android.content.Context;
import android.database.DataSetObserver;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.nineoldandroids.animation.ObjectAnimator;

public class DesktopView extends ViewGroup {

    private static final String TAG = "GameDesktopView";

    private int radius;
    private int padding = 10;
    private int childSize;
    private PointF center = new PointF();
    private BaseAdapter adapter;
    private DataSetObserver dataSetObserver = new GameDataObserver();

    private class GameDataObserver extends DataSetObserver {
        @Override
        public void onChanged() {
            requestLayout();
        }

        @Override
        public void onInvalidated() {
        }
    }

    public DesktopView(Context context) {
        super(context);
    }

    public DesktopView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public DesktopView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private OnClickListener listener = new OnClickListener() {
        @Override
        public void onClick(View v) {
            ObjectAnimator.ofFloat(v, "x", v.getX(), center.x - childSize / 2).setDuration(2000).start();
            ObjectAnimator.ofFloat(v, "y", v.getY(), center.y - childSize / 2).setDuration(2000).start();
            DesktopView.this.requestLayout();
        }
    };

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

        Log.d(TAG, "onLayout");

        int count = getChildCount();

        float angle = 360 / count;

        //以圆形的形式排列元素
        for (int i = 0; i < count; i++) {

            View v = getChildAt(i);

            int width = v.getMeasuredWidth();
            int circleR = radius - width / 2;
            int halfWidth = width / 2;

            double radian = changeAngelToRadian(angle * i);

            int centerX = (int) (radius + circleR * Math.sin(radian)) + padding;
            int centerY = (int) (radius + circleR * Math.cos(radian)) + padding;

            v.layout(centerX - halfWidth, centerY - halfWidth, centerX + halfWidth, centerY + halfWidth);
            v.setOnClickListener(listener);
        }

    }

    private double changeAngelToRadian(float angle) {
        return Math.toRadians(angle);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        Log.d(TAG, "onMeasure");

        int width = getDefaultSize(getSuggestedMinimumWidth(), widthMeasureSpec);
        int height = getDefaultSize(getSuggestedMinimumHeight(), heightMeasureSpec);

        int size = Math.min(width, height);
        radius = (size - padding * 2) / 2;

        //子元素大小
        childSize = radius / 2;

        //设置圆心位置
        center.y = center.x = size / 2;

        int count = getChildCount();

        for (int i = 0; i < count; i++) {
            measureChild(getChildAt(i), size, size);
        }

        childSize = getChildAt(0).getMeasuredWidth();

        setMeasuredDimension(size, size);
    }

    public BaseAdapter getAdapter() {
        return adapter;
    }

    public void setAdapter(BaseAdapter adapter) {
        this.adapter = adapter;
        this.adapter.registerDataSetObserver(dataSetObserver);
    }
}
