package com.cml.wodi.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

public class ChatInfoPagerAdapter extends FragmentPagerAdapter {

    private List<Class<? extends Fragment>> data;
    private Context context;

    public ChatInfoPagerAdapter(FragmentManager fm, List<Class<? extends Fragment>> data, Context context) {
        super(fm);
        this.data = data;
        this.context = context;
    }

    @Override
    public Fragment getItem(int i) {
        Class c = data.get(i);
        return Fragment.instantiate(context, c.getName());
    }

    @Override
    public int getCount() {
        return data.size();
    }
}
