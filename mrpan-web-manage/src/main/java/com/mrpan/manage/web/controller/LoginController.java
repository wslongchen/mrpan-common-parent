package com.mrpan.manage.web.controller;

import javax.servlet.http.HttpServletRequest;

import com.mrpan.api.user.bean.User;
import com.mrpan.api.user.service.UserService;
import com.mrpan.manage.web.BaseController;
import com.mrpan.manage.web.WebConstant;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


/**
 * Created by mrpan on 2016/11/8.
 */
public class LoginController extends BaseController {
    private static final Logger logger = LoggerFactory
            .getLogger(LoginController.class);

    @Autowired
    UserService userService;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login() {
        logger.debug("login..");
        return "login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String doLogin(String username, String password, HttpServletRequest request) {
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(username,
                password);
        token.setRememberMe(true);
        try {
            subject.login(token);
            User user = this.userService
                    .findUser(username,"");
            if (user == null) {
                return "login";
            }

            Session session = subject.getSession();
            session.setAttribute(WebConstant.LOGIN_USER, user);
            logger.info("login the system successfully");
        } catch (UnknownAccountException uae) {
            logger.error("###unknown account" + uae.getMessage(), uae);
            return "login";
        } catch (IncorrectCredentialsException ice) {
            logger.error("###incorrect Credentials" + ice.getMessage(), ice);
            return "login";
        } catch (LockedAccountException lae) {
            logger.error("locked Account " + lae.getMessage(), lae);
            return "login";
        } catch (AuthenticationException ae) {
            logger.error("authentication error " + ae.getMessage(), ae);
            return "login";
        } catch (Exception e) {
            logger.error("Other Exception" + e.getMessage(), e);
            return "500";
        }
        return "redirect:frame/main";
    }

    @RequestMapping(value = "logout")
    public String logout() {
        Subject subject = SecurityUtils.getSubject();
        try {
            subject.logout();
            Session session = subject.getSession();//
            session.setAttribute(WebConstant.LOGIN_USER, null);
            logger.info("logout the system successfully");
        } catch (AuthenticationException e) {
            logger.error("failed to logout System" + e.getMessage(), e);
        }
        return "redirect:login";
    }

}
