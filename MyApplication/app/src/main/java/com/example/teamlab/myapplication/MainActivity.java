package com.example.teamlab.myapplication;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.view.Window;

import com.example.teamlab.myapplication.activity.BaseSlidingActionBarActivity;
import com.example.teamlab.myapplication.fragment.MenuFragment;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;

public class MainActivity extends BaseSlidingActionBarActivity {


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // set the content view
        setContentView(R.layout.activity_main);
        initSlidingMenu();
        setCircleProgressbar(true);
        setActionBarTitle("哈哈哈");
    }

    /**
     * 初始化滑动菜单配置信息
     */
    private void initSlidingMenu() {
        setSlidingActionBarEnabled(true);
        setBehindContentView(R.layout.menu_frame);

        SlidingMenu menu = getSlidingMenu();
        menu.setMode(SlidingMenu.LEFT);
        menu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
        menu.setShadowWidthRes(R.dimen.shadow_width);
        menu.setShadowDrawable(R.drawable.shadow);
        menu.setBehindOffsetRes(R.dimen.slidingmenu_offset);
        menu.setFadeDegree(0.35f);

        //设置菜单项
        MenuFragment menuFragment=new MenuFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.menu_frame, menuFragment).commit();

    }


}