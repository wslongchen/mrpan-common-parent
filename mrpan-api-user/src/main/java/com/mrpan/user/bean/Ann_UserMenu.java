package com.mrpan.user.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by mrpan on 2016/11/14.
 */
@Entity
@Table(name = "ann_usermenu")
public class Ann_UserMenu implements java.io.Serializable {
    @Id
    @Column(name = "UserId")
    private Integer userId;

    @Id
    @Column(name = "MenuId")
    private Integer menuId;
    @Column(name = "ToolBar")
    private String toolBar = "";

    public Ann_UserMenu() {
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getMenuId() {
        return menuId;
    }

    public void setMenuId(Integer menuId) {
        this.menuId = menuId;
    }

    public String getToolBar() {
        return toolBar;
    }

    public void setToolBar(String toolBar) {
        this.toolBar = toolBar;
    }
}
