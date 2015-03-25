package com.cml.wodi.view;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.StyleRes;
import android.util.AttributeSet;
import android.widget.LinearLayout;

/**
 * Created by teamlab on 2015/3/25.
 */
public class TabView extends LinearLayout {

    private Integer selectedColor;
    private Integer unSelectedColor;

    public TabView(Context context) {
        super(context);
    }

    public TabView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    private void init(Context context) {
    }

}
