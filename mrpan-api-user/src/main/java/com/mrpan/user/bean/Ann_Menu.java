package com.mrpan.user.bean;

import javax.persistence.*;

/**
 * Created by mrpan on 2016/11/14.
 */
@Entity
@Table(name = "ann_menu")
public class Ann_Menu implements java.io.Serializable{

    @Id
    @Column(name ="MenuId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer menuId;
    @Column(name ="ParentId")
    private Integer parentId;
    @Column(name ="MenuName")
    private String menuName = "";
    @Column(name ="MenuUrl")
    private String menuUrl = "";
    @Column(name ="Icon")
    private String icon = "";
    @Column(name ="MenuLevel")
    private Integer menuLevel;
    @Column(name ="MenuSort")
    private Integer menuSort;
    @Column(name ="Visible")
    private Integer visible;
    @Column(name ="ToolBar")
    private String toolBar = "";
    @Column(name ="TypeFlag")
    private Integer typeFlag;
    public Ann_Menu() {}

    public Integer getMenuId() {
        return menuId;
    }

    public void setMenuId(Integer menuId) {
        this.menuId = menuId;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public String getMenuUrl() {
        return menuUrl;
    }

    public void setMenuUrl(String menuUrl) {
        this.menuUrl = menuUrl;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public Integer getMenuLevel() {
        return menuLevel;
    }

    public void setMenuLevel(Integer menuLevel) {
        this.menuLevel = menuLevel;
    }

    public Integer getMenuSort() {
        return menuSort;
    }

    public void setMenuSort(Integer menuSort) {
        this.menuSort = menuSort;
    }

    public Integer getVisible() {
        return visible;
    }

    public void setVisible(Integer visible) {
        this.visible = visible;
    }

    public String getToolBar() {
        return toolBar;
    }

    public void setToolBar(String toolBar) {
        this.toolBar = toolBar;
    }

    public Integer getTypeFlag() {
        return typeFlag;
    }

    public void setTypeFlag(Integer typeFlag) {
        this.typeFlag = typeFlag;
    }
}
