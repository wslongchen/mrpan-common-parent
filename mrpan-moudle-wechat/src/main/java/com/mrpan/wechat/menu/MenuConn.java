package com.mrpan.wechat.menu;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.mrpan.wechat.Connection;
import com.mrpan.wechat.bean.menu.Menu;
import com.mrpan.wechat.bean.menu.ReturnMenu;
import com.mrpan.wechat.bean.results.JsonResult;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

/**
 * Created by mrpan on 2017/1/22.
 */
public class MenuConn extends Connection {
    // 创建菜单
    private String menu_create = "https://api.weixin.qq.com/cgi-bin/menu/create";
    // 查询自定义菜单
    private String menu_get = "https://api.weixin.qq.com/cgi-bin/menu/get";
    // 删除自定义菜单
    private String menu_delte = "https://api.weixin.qq.com/cgi-bin/menu/delete";

    /**
     * 创建的菜单
     *
     * @param menu
     *            菜单项
     * @param token
     *            授权token
     * @return {"errcode":0,"errmsg":"ok"}
     */
    public JsonResult createMenu(Menu menu, String token) {
        TreeMap<String, String> map = new TreeMap<String, String>();
        map.put("access_token", token);
        String jsonData = JSONObject.toJSON(menu).toString();
        String result = HttpsDefaultExecute("POST", menu_create, map, jsonData);
        return ConvertResult(result);
    }

    /**
     * 第二种方式创建菜单
     *
     * @param menu
     *            菜单项
     * @param token
     *            授权token
     * @return {"errcode":0,"errmsg":"ok"}
     */
    public JsonResult createTwoMenu(Menu menu, String token) {
        TreeMap<String, String> map = new TreeMap<String, String>();
        map.put("access_token", token);
        String jsonData = JSONObject.toJSON(menu).toString();
        String result = HttpsDefaultExecute("POST", menu_create, map, jsonData);
        return ConvertResult(result);
    }

    /**
     * 获取自定义菜单(此处待改进)
     *
     * @param token
     * @return
     */
    public String getMenu(String token) {
        TreeMap<String, String> map = new TreeMap<String, String>();
        map.put("access_token", token);
        String result = HttpsDefaultExecute("GET", menu_get, map, "");
        return result;
    }

    /**
     * 将json格式的字符串转换为Menu对象
     *
     * @param jsonReuslt
     * @return
     */
    private List<ReturnMenu> converMenu(String jsonReuslt) {
        List<ReturnMenu> list = new ArrayList<ReturnMenu>();
        if (jsonReuslt != null && !"".equals(jsonReuslt)) {
            JSONObject object = JSONObject.parseObject(jsonReuslt);
            JSONArray array = object.getJSONObject("menu").getJSONArray("button");
            for (int i = 0; i < array.size(); i++) {
                ReturnMenu group = new ReturnMenu();
                group = array.getObject(i, ReturnMenu.class);
                list.add(group);
            }
        }
        return list;
    }

    /**
     * 删除自定义菜单
     *
     * @param token
     * @return
     */
    public boolean deleteMenu(String token) {
        boolean fig = true;
        TreeMap<String, String> map = new TreeMap<String, String>();
        map.put("access_token", token);
        String result = HttpsDefaultExecute("GET", menu_delte, map, "");
        JSONObject object = JSONObject.parseObject(result);
        if (object.getInteger("errcode") != 0|| object.getString("errmsg") != "ok") { // 删除失败
            fig = false;
        }
        return fig;
    }
}
