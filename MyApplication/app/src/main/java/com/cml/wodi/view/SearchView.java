package com.cml.wodi.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.cml.wodi.R;


public class SearchView extends LinearLayout {

    public SearchView(Context context) {
        super(context);
        this.init(context);
    }

    public SearchView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.init(context);
    }

    private void init(Context context) {
        this.setOrientation(VERTICAL);
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.view_search, this);
    }


}
