package com.example.teamlab.myapplication.helper;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.widget.BaseAdapter;

import com.example.teamlab.myapplication.R;
import com.example.teamlab.myapplication.fragment.FragmentA;
import com.example.teamlab.myapplication.fragment.FragmentB;
import com.example.teamlab.myapplication.model.MenuItem;

import java.util.ArrayList;
import java.util.List;

public class MenuHelper {

    public static enum Menu {
        MENU_A(R.string.hello_world, null, null, FragmentA.class.getName()),//
        MENU_B(R.string.action_settings, null, null, FragmentB.class.getName());

        private MenuItem item;

        Menu(Integer nameRes, Integer iconRes, Integer titleRes, String className) {
            item = new MenuItem(nameRes, iconRes, titleRes, className);
        }

        public MenuItem getMenu() {
            return item;
        }

        public static Menu getMenuById(Integer id) {
            for (Menu menu : Menu.values()) {
                if (menu.getMenu().getNameRes().intValue() == id.intValue()) {
                    return menu;
                }
            }
            return null;
        }

        public static List<MenuItem> getMenus() {
            List<MenuItem> list = new ArrayList<>();
            for (Menu menu : Menu.values()) {
                list.add(menu.getMenu());
            }
            return list;
        }
    }

    //默认为第一个选中
    private Menu selectedMenu;
    private Fragment selectedFragment;

    private FragmentManager fragmentManager;
    private Context context;

    public MenuHelper(FragmentManager fragmentManager, Context context) {
        this.fragmentManager = fragmentManager;
        this.context = context;
    }

    public void replaceContainer(Menu menu, BaseAdapter adapter) {

        if (menu == selectedMenu) {
            return;
        }

        if (null != selectedMenu) {
            selectedMenu.getMenu().setSelected(false);
        }
        menu.getMenu().setSelected(true);

        FragmentTransaction transaction = fragmentManager.beginTransaction();

        Fragment fragment = fragmentManager.findFragmentByTag(menu.name());

        if (null == fragment) {
            fragment = Fragment.instantiate(context, menu.getMenu().getClassName());
            transaction.add(R.id.content_frame, fragment, menu.name());
        } else if (!fragment.isAdded()) {
            transaction.add(fragment, menu.name());
        }

        transaction.show(fragment);

        if (null != selectedFragment) {
            transaction.hide(selectedFragment);
        }

        transaction.commit();

        selectedFragment = fragment;
        selectedMenu = menu;
        adapter.notifyDataSetChanged();
    }

    public Menu getSelectedMenu() {
        return selectedMenu;
    }
}
