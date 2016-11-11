package com.mrpan.manage.web;

import com.mrpan.user.bean.User;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by mrpan on 2016/11/3.
 */
public class BaseController {
	public User getLoginUser(HttpServletRequest request) {
		User user = (User) request.getSession().getAttribute(
				WebConstant.LOGIN_USER);
		return user;
	}
}
