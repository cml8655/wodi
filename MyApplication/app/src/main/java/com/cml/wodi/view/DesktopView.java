package com.cml.wodi.view;

import android.content.Context;
import android.database.DataSetObserver;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.cml.wodi.view.adapter.ViewAdapter;
import com.nineoldandroids.animation.ObjectAnimator;

import java.util.List;

public abstract class DesktopView extends ViewGroup {

    private static final String TAG = "GameDesktopView";

    private int radius;
    private int padding = 10;
    protected int childSize;
    protected PointF center = new PointF();

    private DataSetObserver dataSetObserver = new GameDataObserver();
    protected ViewAdapter adapter;

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


    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

        Log.d(TAG, "onLayout");

        int count = getChildCount();

        float angle = 360 / count;

        //以圆形的形式排列元素
        for (int i = 0; i < count; i++) {

            View v = getChildAt(i);

            int width = v.getMeasuredWidth();
            int circleR = radius  - radius / 4 ;
            int halfWidth = width / 2;

            double radian = changeAngelToRadian(angle * i);

            int centerX = (int) (radius + circleR * Math.sin(radian)) + padding;
            int centerY = (int) (radius + circleR * Math.cos(radian)) + padding;

            v.layout(centerX - halfWidth, centerY - halfWidth, centerX + halfWidth, centerY + halfWidth);
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
        childSize = radius / 3;

        //设置圆心位置
        center.y = center.x = size / 2;

        int count = getChildCount();

        for (int i = 0; i < count; i++) {

            View child = getChildAt(i);
            //重设大小
            LayoutParams params = new LayoutParams(childSize, childSize);
            child.setLayoutParams(params);

            measureChild(child, size, size);
        }

        setMeasuredDimension(size, size);
    }

    /**
     * 移除已有的组件，重新设置组件
     */
    protected void relayout() {

        removeAllViews();

        for (View v : getChildViews()) {
            this.addView(v);
        }
        requestLayout();
    }

    protected abstract List<View> getChildViews();

    public ViewAdapter getAdapter() {
        return adapter;
    }

    public void setAdapter(ViewAdapter adapter) {
        this.adapter = adapter;
        this.adapter.setDataSetObserver(dataSetObserver);
        relayout();
    }
}
