package com.example.teamlab.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcel;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;

import com.example.teamlab.myapplication.activity.BaseActionBarActivity;
import com.example.teamlab.myapplication.parcel.ContentParcel;

public class ModalActivity extends BaseActionBarActivity {

    public static final String EXTRA_DATA = "ModalActivity.EXTRA_DATA";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modal);
        final ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeAsUpIndicator(R.drawable.actionbar_back_btn);
        setCircleProgressbar(true);
        showContent();
    }

    private void showContent() {

        Bundle bundle = getIntent().getExtras();

        if (null != bundle && bundle.containsKey(EXTRA_DATA)) {

            ContentParcel contentParcel = bundle.getParcelable(EXTRA_DATA);
            setActionBarTitle(contentParcel.getTitleRes());

            FragmentTransaction transaction=getSupportFragmentManager().beginTransaction();
            Fragment fragment=Fragment.instantiate(this,contentParcel.getClassName());
            transaction.replace(R.id.modal_container,fragment);
            transaction.commit();
        }

    }

    @Override
    protected boolean onHomeClicked() {
        onBackPressed();
        return true;
    }
}
