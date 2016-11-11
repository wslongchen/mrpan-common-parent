package com.mrpan.vpsplatform.web;

import com.mrpan.user.bean.User;

import javax.servlet.http.HttpServletRequest;


public class BaseController {
	public User getLoginUser(HttpServletRequest request) {
		User user = (User) request.getSession().getAttribute(
				WebConstant.LOGIN_USER);
		return user;
	}
}
