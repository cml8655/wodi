package com.example.teamlab.myapplication.activity;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.view.KeyEvent;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.teamlab.myapplication.R;

public abstract class BaseActionBarActivity extends BaseActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setupActionBar();
    }

    protected void setupActionBar() {

        final ActionBar actionBar = getSupportActionBar();

        // 应用程序图标左侧不显示向左的箭头
        actionBar.setDisplayHomeAsUpEnabled(true);
        // 设置是否显示应用程序的图标
        actionBar.setDisplayShowHomeEnabled(true);
        // 将应用程序图标设置为可点击的按钮
        actionBar.setHomeButtonEnabled(true);

        // 设定使用logo作为应用程序图标
        actionBar.setDisplayUseLogoEnabled(true);// activity logo or icon
        // 设定logo图片
        // actionBar.setLogo(R.drawable.actionbar_back_btn);

        // 不显示标题
        actionBar.setDisplayShowTitleEnabled(false);

        // 使用custom view显示自定义标题
        actionBar.setDisplayShowCustomEnabled(true);

        // 设定title
        TextView title = (TextView) getLayoutInflater().inflate(
                R.layout.actionbar_title, null);
        title.setText(this.getTitle());

        actionBar.setCustomView(title, new ActionBar.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT));
    }

    /**
     * @param title
     */
    public void setActionBarTitle(CharSequence title) {
        final ActionBar actionBar = getSupportActionBar();
        TextView customView = ((TextView) actionBar.getCustomView());
        customView.setText(title);
    }

    /**
     * @param title
     */
    public void setActionBarTitle(Integer title) {
        String titleStr = getString(title);
        this.setActionBarTitle(titleStr);
    }
}
