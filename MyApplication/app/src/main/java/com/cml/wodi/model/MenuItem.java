package com.cml.wodi.model;

/**
 * Created by teamlab on 2015/3/4.
 */
public class MenuItem {
    // menu名称
    private Integer nameRes;

    // menu图标
    private Integer iconRes;

    // actionbar显示的导航条标题
    private Integer titleRes;

    private String className;

    //默认为非选中状态
    private boolean selected = false;

    public MenuItem() {
    }

    public MenuItem(Integer nameRes, Integer iconRes, Integer titleRes, String className) {
        this.nameRes = nameRes;
        this.iconRes = iconRes;
        this.titleRes = titleRes;
        this.className = className;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public Integer getIconRes() {
        return iconRes;
    }

    public void setIconRes(Integer iconRes) {
        this.iconRes = iconRes;
    }

    public Integer getNameRes() {
        return nameRes;
    }

    public void setNameRes(Integer nameRes) {
        this.nameRes = nameRes;
    }

    public Integer getTitleRes() {
        return titleRes;
    }

    public void setTitleRes(Integer titleRes) {
        this.titleRes = titleRes;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }
}
