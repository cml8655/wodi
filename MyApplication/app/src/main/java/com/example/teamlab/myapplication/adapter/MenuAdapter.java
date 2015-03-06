package com.example.teamlab.myapplication.adapter;

import android.content.Context;
import android.graphics.Color;

import com.example.teamlab.myapplication.R;
import com.example.teamlab.myapplication.model.MenuItem;

import java.util.List;

public class MenuAdapter extends QuickAdapter<MenuItem> {

    public MenuAdapter(Context context, int layoutResId) {
        super(context, layoutResId);
    }

    public MenuAdapter(Context context, int layoutResId, List<MenuItem> data) {
        super(context, layoutResId, data);
    }

    @Override
    protected void convert(BaseAdapterHelper helper, MenuItem item) {
        if (item.isSelected()) {
            helper.setConvertViewBackground(Color.parseColor("#AAFF88"));
        }
        helper.setText(R.id.tv_menu_item, item.getNameRes());
    }
}
