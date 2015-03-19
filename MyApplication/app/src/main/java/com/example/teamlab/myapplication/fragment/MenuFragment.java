package com.example.teamlab.myapplication.fragment;

import android.app.Activity;
import android.nfc.Tag;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Adapter;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.example.teamlab.myapplication.R;
import com.example.teamlab.myapplication.activity.BaseSlidingActionBarActivity;
import com.example.teamlab.myapplication.adapter.MenuAdapter;
import com.example.teamlab.myapplication.helper.MenuHelper;
import com.example.teamlab.myapplication.model.MenuItem;
import com.example.teamlab.myapplication.util.DialogUtil;
import com.jeremyfeinstein.slidingmenu.lib.app.SlidingFragmentActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class MenuFragment extends BaseListFragment implements View.OnClickListener {


    private static final String TAG = "MenuFragment";
    private static final String EXTRA_MENU = "MenuFragment.EXTRA_MENU";
    private static final int POSITION_HEADER = 0;
    private static final int POSITION_DEFAULT = 1;

    private MenuHelper menuHelper;
    private MenuHelper.Menu initMenu = MenuHelper.Menu.MENU_A;

    private BaseSlidingActionBarActivity slidingActionBarActivity;
    private BaseAdapter menuAdapter;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof BaseSlidingActionBarActivity) {
            slidingActionBarActivity = (BaseSlidingActionBarActivity) activity;
        }
    }

    @Override
    public int getLayoutResource() {
        return R.layout.fragment_menu;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (null != savedInstanceState && savedInstanceState.getString(EXTRA_MENU) != null) {
            String menuName = savedInstanceState.getString(EXTRA_MENU);
            initMenu = MenuHelper.Menu.valueOf(menuName);
            Log.d(TAG, "onCreate 菜单数据恢复");
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        MenuHelper.Menu currentMenu = menuHelper.getSelectedMenu();
        if (null != currentMenu) {
            outState.putString(EXTRA_MENU, currentMenu.name());
        }
        super.onSaveInstanceState(outState);

    }


    @Override
    public void init(View view) {
        //设置用户头像信息
        LayoutInflater inflater = LayoutInflater.from(slidingActionBarActivity);
        getListView().addHeaderView(inflater.inflate(R.layout.view_menu_header, null));

        //初始化菜单选项
        List<MenuItem> menuItemList = MenuHelper.Menu.getMenus();
        menuAdapter = new MenuAdapter(getActivity(), R.layout.view_menu_item, menuItemList);
        this.setListAdapter(menuAdapter);

        menuHelper = new MenuHelper(getFragmentManager(), getActivity());
        initMenu.getMenu().setSelected(true);
        menuHelper.replaceContainer(initMenu, menuAdapter);

        //设置退出按钮
        view.findViewById(R.id.tv_system_exit).setOnClickListener(this);
    }


    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {

        //点击用户头像
        if (position == POSITION_HEADER) {
            position = POSITION_DEFAULT;
        }

        MenuItem menu = (MenuItem) getListAdapter().getItem(position - 1);
        MenuHelper.Menu clickedMenu = MenuHelper.Menu.getMenuById(menu.getNameRes());

        if (null == clickedMenu) {
            return;
        }
        //切换菜单
        menuHelper.replaceContainer(clickedMenu, menuAdapter);

        if (null != slidingActionBarActivity) {
            slidingActionBarActivity.showContent();
        }
    }

    @Override
    public void onClick(View v) {

        DialogUtil.showExitDialog(slidingActionBarActivity, new SweetAlertDialog.OnSweetClickListener() {
            @Override
            public void onClick(SweetAlertDialog sDialog) {
                sDialog.dismissWithAnimation();
            }
        }, new SweetAlertDialog.OnSweetClickListener() {
            @Override
            public void onClick(SweetAlertDialog sDialog) {
                sDialog.dismiss();
                slidingActionBarActivity.finish();
            }
        });
    }
}
