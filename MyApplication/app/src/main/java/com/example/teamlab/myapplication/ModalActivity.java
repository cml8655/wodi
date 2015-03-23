package com.example.teamlab.myapplication;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;

import com.example.teamlab.myapplication.activity.BaseActivity;
import com.example.teamlab.myapplication.parcel.ContentParcel;

public class ModalActivity extends BaseActivity {

    public static final String EXTRA_DATA = "ModalActivity.EXTRA_DATA";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modal);
        final ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeAsUpIndicator(R.drawable.actionbar_back_btn);
    }

}
