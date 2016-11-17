package com.mrpan.vpsplatform.web;

import com.mrpan.user.bean.Ann_User;

import javax.servlet.http.HttpServletRequest;


public class BaseController {
	public Ann_User getLoginUser(HttpServletRequest request) {
		Ann_User user = (Ann_User) request.getSession().getAttribute(
				WebConstant.LOGIN_USER);
		return user;
	}
}
