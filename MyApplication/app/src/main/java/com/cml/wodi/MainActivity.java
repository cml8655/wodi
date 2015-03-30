package com.cml.wodi;

import android.os.Bundle;

import com.cml.wodi.activity.BaseActivity;
import com.cml.wodi.view.DesktopView;
import com.cml.wodi.view.adapter.GameViewAdapter;
import com.cml.wodi.view.adapter.ViewAdapter;
import com.cml.wodi.view.model.GameViewItem;

public class MainActivity extends BaseActivity {

    DesktopView desktopView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        desktopView = (DesktopView) findViewById(R.id.desktopView);

        ViewAdapter<GameViewItem> adapter = new GameViewAdapter();
        adapter.add(new GameViewItem());
        adapter.add(new GameViewItem());
        adapter.add(new GameViewItem());
        adapter.add(new GameViewItem());
        adapter.add(new GameViewItem());
        desktopView.setAdapter(adapter);
    }
}