package com.mrpan.vpnplatform.web.directive;

import com.mrpan.user.bean.AnnMenuTree;
import com.mrpan.user.bean.Ann_Menu;
import com.mrpan.user.service.Ann_MenuService;
import com.mrpan.vpnplatform.web.utils.DirectiveUtils;
import freemarker.core.Environment;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.io.Writer;
import java.util.List;
import java.util.Map;

/**
<<<<<<< Updated upstream
 * Created by mrpan on 2016/11/14.
 */
public class MenuDirective implements TemplateDirectiveModel {
    private static final Logger logger = LoggerFactory.getLogger(MenuDirective.class);
    /** 客户编号 */
    public static final String PARAM_USERID = "userId";
    /** 角色编号 */
    public static final String PARAM_ROLEID = "roleId";
    /** 工号 */
    public static final String PARAM_JOBNO = "jobNo";

    public static final String PARAM_CTX_BASE = "base";

    public static final String OUT_BEAN = "tag_bean";

    @Autowired
    private Ann_MenuService ann_MenuService;

    public void execute(Environment env, Map params, TemplateModel[] loopVars, TemplateDirectiveBody body) throws TemplateException, IOException {
        int userId = DirectiveUtils.getInt(PARAM_USERID, params);
        String base = DirectiveUtils.getString(PARAM_CTX_BASE, params);
        AnnMenuTree menuTree = this.ann_MenuService.getMenuTree(userId);
        StringBuffer sb = new StringBuffer();
        buildMenuTree(sb, menuTree.getChilds(), base, 1);
        Writer writer = env.getOut();

        System.out.println(sb.toString());
        writer.write(sb.toString());
        writer.flush();
    }

    public void buildMenuTree(StringBuffer sb, List<AnnMenuTree> subMenuTrees, String base, int menuLevel) {
        // menuLevel: 0->root(不在页面显示)  1 -> 一级     2 ->   二级  3 -> 三级
        if (menuLevel == 1) {	//渲染一级菜单
            logger.info("一级菜单 ---> ");
            for (AnnMenuTree menuTree : subMenuTrees) {
                Ann_Menu menu = menuTree.getRoot();
                logger.info("       " + menu.getMenuName());
                List<AnnMenuTree> subTrees = menuTree.getChilds();
                //
                sb.append("<li><a href=\"#\"><i class=\"")
                        // 一级菜单样式(图标样式)
                        .append(menu.getIcon())
                        .append("\"></i> <span class=\"nav-label\">")
                        // 菜单名称
                        .append(menu.getMenuName())
                        .append("</span><span class=\"fa arrow\"></span></a>");
                if (subTrees == null || subTrees.size() == 0) {
                    sb.append("</li>");
                } else {
                    sb.append("<ul class=\"nav nav-second-level\">");
                    buildMenuTree(sb, subTrees, base, 2);// 渲染二级菜单
                    sb.append("</ul>");
                    sb.append("</li>");
                }
            }
        } else if (menuLevel == 2) {
            logger.info("		二级菜单 ---> ");
            for (AnnMenuTree menuTree : subMenuTrees) {
                Ann_Menu menu = menuTree.getRoot();
                logger.info("			" + menu.getMenuName());
                List<AnnMenuTree> subTrees = menuTree.getChilds();
                sb.append("<li>");
                if (subTrees == null || subTrees.size() == 0) {//没有下级菜单
                    sb.append("<a class=\"J_menuItem\" href=\"")
                            // 菜单链接
                            .append(base + menu.getMenuUrl())
                            .append("\">")
                            // 菜单名称
                            .append(menu.getMenuName())
                            .append("</a></li>");
                } else {// 有下级菜单
                    sb.append("<a href=\"#\">")
                            // 菜单名称
                            .append(menu.getMenuName())
                            .append("<span class=\"fa arrow\"></span></a>")
                            .append("<ul class=\"nav nav-third-level\">");
                    buildMenuTree(sb, subTrees, base, 3);// 渲染三级菜单
                    sb.append("</ul>")
                            .append("</li>");
                }
            }
        } else if (menuLevel == 3) {
            logger.info("			三级菜单 ---> ");
            for (AnnMenuTree menuTree : subMenuTrees) {
                Ann_Menu menu = menuTree.getRoot();
                logger.info("				" + menu.getMenuName());
                List<AnnMenuTree> subTrees = menuTree.getChilds();
                if (subTrees == null || subTrees.size() == 0) {
                    sb.append("<li><a class=\"J_menuItem\" href=\"")
                            // 菜单链接地址
                            .append(base + menu.getMenuUrl())
                            .append("\">")
                            //菜单名称
                            .append(menu.getMenuName())
                            .append("</a></li>");
                } else {
                    logger.info("居然有下级菜单???????????????????");
                    sb.append("<a href=\"#\">")
                            // 菜单名称
                            .append(menu.getMenuName())
                            .append("<span class=\"fa arrow\"></span></a>");
                    sb.append("<ul class=\"nav nav-second-level\">");
                    buildMenuTree(sb, subTrees, base, 4);
                    sb.append("</ul>")
                            .append("</li>");
                }
            }
        } else {// menu level > 3 ????

        }
    }
}
