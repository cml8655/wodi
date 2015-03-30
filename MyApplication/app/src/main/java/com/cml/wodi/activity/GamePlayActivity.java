package com.cml.wodi.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import com.cml.wodi.R;
import com.cml.wodi.adapter.ChatInfoPagerAdapter;
import com.cml.wodi.fragment.BaseListFragment;
import com.cml.wodi.fragment.ChatInfoFragment;
import com.cml.wodi.fragment.GamePlayFragment;
import com.cml.wodi.transformer.DepthPageTransformer;
import com.cml.wodi.view.CustomViewPager;
import com.cml.wodi.view.DesktopView;
import com.cml.wodi.view.adapter.GameViewAdapter;
import com.cml.wodi.view.adapter.ViewAdapter;
import com.cml.wodi.view.model.GameViewItem;

import java.util.ArrayList;
import java.util.List;

public class GamePlayActivity extends BaseActivity {

    private CustomViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_play);
        viewPager = (CustomViewPager) findViewById(R.id.gameplayViewPager);

        List<Class<? extends Fragment>> pagerData = new ArrayList<Class<? extends Fragment>>();
        pagerData.add(ChatInfoFragment.class);
        pagerData.add(GamePlayFragment.class);
        pagerData.add(ChatInfoFragment.class);
        FragmentPagerAdapter pagerAdapter = new ChatInfoPagerAdapter(getSupportFragmentManager(), pagerData, this);
        viewPager.setAdapter(pagerAdapter);
        viewPager.setCurrentItem(1);
        viewPager.setPageTransformer(true, new DepthPageTransformer());
    }
}
