package com.cml.wodi.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;

import com.cml.wodi.R;

/**
 * Created by teamlab on 2015/3/20.
 */
public class SearchActivity extends ActionBarActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.i("SearchActivity", getIntent().getAction());
    }
}
