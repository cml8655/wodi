package com.cml.wodi.fragment;

import android.view.View;

import com.cml.wodi.R;
import com.cml.wodi.view.DesktopView;
import com.cml.wodi.view.adapter.GameViewAdapter;
import com.cml.wodi.view.adapter.ViewAdapter;
import com.cml.wodi.view.model.GameViewItem;

public class GamePlayFragment extends BaseFragment {

    private DesktopView desktopView;

    @Override
    int getLayoutResource() {
        return R.layout.fragment_game_play;
    }

    @Override
    protected void init(View view) {
        desktopView = (DesktopView) view.findViewById(R.id.desktopView);
        //TODO 参数传递过来
        ViewAdapter<GameViewItem> adapter = new GameViewAdapter();
        adapter.add(new GameViewItem());
        adapter.add(new GameViewItem());
        adapter.add(new GameViewItem());
        adapter.add(new GameViewItem());
        adapter.add(new GameViewItem());
        desktopView.setAdapter(adapter);
    }
}
