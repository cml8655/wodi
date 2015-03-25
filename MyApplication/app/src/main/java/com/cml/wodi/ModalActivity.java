package com.cml.wodi;

import android.os.Bundle;
import android.support.v7.app.ActionBar;

import com.cml.wodi.activity.BaseActionBarActivity;

public class ModalActivity extends BaseActionBarActivity {

    public static final String EXTRA_DATA = "ModalActivity.EXTRA_DATA";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modal);
        final ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeAsUpIndicator(R.drawable.actionbar_back_btn);
    }

}
