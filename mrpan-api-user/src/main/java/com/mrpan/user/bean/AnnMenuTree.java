package com.mrpan.user.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by mrpan on 2016/11/14.
 */
public class AnnMenuTree implements Serializable {
    private Ann_Menu root;
    private String tagClass;
    List<AnnMenuTree> childs;

    public String getTagClass() {
        return tagClass;
    }

    public void setTagClass(String tagClass) {
        this.tagClass = tagClass;
    }

    public Ann_Menu getRoot() {
        return root;
    }

    public void setRoot(Ann_Menu root) {
        this.root = root;
    }

    public List<AnnMenuTree> getChilds() {
        return childs;
    }

    public void setChilds(List<AnnMenuTree> childs) {
        this.childs = childs;
    }
}
