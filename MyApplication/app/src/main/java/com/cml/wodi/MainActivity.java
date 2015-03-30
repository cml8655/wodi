package com.cml.wodi;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.cml.wodi.activity.BaseActivity;
import com.cml.wodi.activity.GamePlayActivity;
import com.cml.wodi.view.DesktopView;
import com.cml.wodi.view.adapter.GameViewAdapter;
import com.cml.wodi.view.adapter.ViewAdapter;
import com.cml.wodi.view.model.GameViewItem;

public class MainActivity extends BaseActivity {


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void startGame(View v) {
        startActivity(new Intent(this, GamePlayActivity.class));
    }
}