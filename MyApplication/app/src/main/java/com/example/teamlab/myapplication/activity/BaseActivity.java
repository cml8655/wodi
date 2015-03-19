package com.example.teamlab.myapplication.activity;

import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.example.teamlab.myapplication.R;

public abstract class BaseActivity extends ActionBarActivity {

    private boolean circleProgressbar;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public boolean onCreateOptionsMenu(final Menu menu) {
        getMenuInflater().inflate(R.menu.actionbar_refresh, menu);
        MenuItem refresh = menu.findItem(R.id.action_refresh);
        if (circleProgressbar) {
            MenuItemCompat.setActionView(refresh,
                    R.layout.actionbar_indeterminate_progress);
        } else {
            MenuItemCompat.setActionView(refresh,
                    null);
        }

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // ActionBar中设置的Home按钮的点击处理
                return onHomeClicked();
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    /**
     *
     * @return false to allow normal menu processing to proceed, true to consume
     *         it here.
     */
    protected abstract boolean onHomeClicked();

    public void setCircleProgressbar(boolean circleProgressbar) {
        this.circleProgressbar = circleProgressbar;
        this.invalidateOptionsMenu();
    }
}
