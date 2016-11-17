package com.mrpan.user.service.impl;

import com.mrpan.user.bean.AnnMenuTree;
import com.mrpan.user.bean.Ann_Menu;
import com.mrpan.user.bean.Ann_UserMenu;
import com.mrpan.user.dao.Ann_MenuDao;
import com.mrpan.user.dao.Ann_UserMenuDao;
import com.mrpan.user.service.Ann_MenuService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by mrpan on 2016/11/14.
 */
@Component("ann_MenuService")
public class Ann_MenuServiceImpl implements Ann_MenuService {

    private static final Logger logger = LoggerFactory.getLogger(Ann_MenuServiceImpl.class);
    @Autowired
    private Ann_MenuDao ann_MenuDao;
    @Autowired
    private Ann_UserMenuDao ann_UserMenuDao;


    public AnnMenuTree getMenuTree(int userId) {
        AnnMenuTree menuTree = new AnnMenuTree();
        Ann_Menu root = new Ann_Menu();
        root.setMenuId(0);
        menuTree.setRoot(root);
        try {
            List<Ann_Menu> allMenus = this.ann_MenuDao.listJpq("visible=1");
            Set<Integer> userMenuIds = new HashSet<Integer>();
            String where = "where userId=" + userId;
            List<Ann_UserMenu> listUserMenu = this.ann_UserMenuDao.listJpq(where);
            for (Ann_UserMenu cm : listUserMenu) {
                userMenuIds.add(cm.getMenuId());
            }
            buildMenuTree(menuTree, allMenus, userMenuIds);
            for (AnnMenuTree tree : menuTree.getChilds()) {
                logger.info("   -" + tree.getRoot().getMenuName());// 一级菜单
                if (tree.getChilds() != null) {
                    for (AnnMenuTree t : tree.getChilds()) {
                        logger.info("         -" + t.getRoot().getMenuName());// 二级菜单
                        List<AnnMenuTree> subs = t.getChilds();
                        if (subs != null) {
                            for (AnnMenuTree sub : subs) {
                                logger.info("                    -" + sub.getRoot().getMenuName());// 三级菜单
                            }
                        }
                    }
                }
            }
        } catch (Exception ex) {
            logger.info("getMenuTree===" + ex.getMessage());
        }
        return menuTree;
    }

    public void buildMenuTree(AnnMenuTree root, List<Ann_Menu> allMenus, Set<Integer> userMenuIds) {
        if (allMenus == null) {
            return;
        }
        // 从数据库中找出属于该代理机构的菜单和该菜单节点的下一级所有菜单节点
        int parentId = root.getRoot().getMenuId() == null ? 0 : root.getRoot().getMenuId();
        List<AnnMenuTree> subMenuTrees = new ArrayList<AnnMenuTree>();
        logger.info("############菜单 -> " + root.getRoot().getMenuName());
        // 遍历所有数据库的菜单
        for (int i = 0; i < allMenus.size(); i++) {
            Ann_Menu menu = allMenus.get(i);// 数据库中的菜单
            int parentIdInDb = menu.getParentId() == null ? 0 : menu.getParentId();
            if (parentIdInDb == parentId) {
                logger.info("################子菜单 ->  " + menu.getMenuName());
                int menuId = menu.getMenuId();
                if (userMenuIds.contains(menuId)) {
                    AnnMenuTree suMenuTree = new AnnMenuTree();
                    suMenuTree.setRoot(menu);
                    // allMenus.remove(i);// 移除
                    buildMenuTree(suMenuTree, allMenus, userMenuIds);
                    subMenuTrees.add(suMenuTree);
                } else {
                    logger.info("###########################不拥有的权限" + menu.getMenuName());
                }
            }
            // root.setChilds(subMenuTrees);
        }
        root.setChilds(subMenuTrees);
    }

    public List<Ann_Menu> lazyLoadUserMenu(Integer parentId) {
        List<Ann_Menu> list = new ArrayList<Ann_Menu>();
        try {
            list = this.ann_MenuDao.findMenu(parentId);
        } catch (Exception e) {
            logger.error("failed to lazyLoadUserMenu ==" + parentId + e.getMessage(), e);
        }
        return list;
    }

    public AnnMenuTree getMenuTree(Integer userId) {
        return null;
    }
}
