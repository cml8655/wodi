package com.cml.wodi.view.model;

import com.cml.wodi.model.GameUserType;

public class GameViewItem {
    private boolean enabled;
    private String title;
    private String imgUrl;
    private Integer imgRes;
    private GameUserType userType;//玩家角色

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public Integer getImgRes() {
        return imgRes;
    }

    public void setImgRes(Integer imgRes) {
        this.imgRes = imgRes;
    }

    public GameUserType getUserType() {
        return userType;
    }

    public void setUserType(GameUserType userType) {
        this.userType = userType;
    }
}
