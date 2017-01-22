package com.mrpan.vpnplatform.web.controller;

import com.mrpan.user.bean.Ann_User;
import com.mrpan.vpnplatform.web.BaseController;
import com.mrpan.vpnplatform.web.WebConstant;
import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by mrpan on 2016/11/12.
 */
@Controller
@RequestMapping(value = "/frame")
@Scope(value = "prototype")
public class WelcomeController extends BaseController {

    private static final Logger logger = LoggerFactory.getLogger(WelcomeController.class);


    @RequestMapping(value = "/main")
    public String main(HttpServletRequest request, HttpServletResponse response, ModelMap map) {
        // TODO
        Ann_User user = (Ann_User) SecurityUtils.getSubject().getSession().getAttribute(WebConstant.LOGIN_USER);
        if (user == null) {// 如果用户
        }

        map.addAttribute("user", user);
        return "frame/main";
    }

    public String top() {
        return "";
    }
}
