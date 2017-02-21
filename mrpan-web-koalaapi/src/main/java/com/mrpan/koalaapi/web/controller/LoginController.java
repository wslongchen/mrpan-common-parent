package com.mrpan.koalaapi.web.controller;

import com.mrpan.koalaapi.web.WebConstant;
import com.mrpan.user.bean.Ann_User;
import com.mrpan.user.service.Ann_UserService;
import com.mrpan.koalaapi.web.BaseController;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping(value = "/")
@Scope(value = "prototype")
public class LoginController extends BaseController {
	private static final Logger logger = LoggerFactory
			.getLogger(LoginController.class);

	@Resource(name="ann_UserService")
	Ann_UserService ann_UserService;

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
			Ann_User user = this.ann_UserService
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

	/*@RequestMapping("/authImage")
	public void authImage(HttpServletRequest request,
			HttpServletResponse response) {
		ConfigurableCaptchaService cs = new ConfigurableCaptchaService();
		cs.setColorFactory(new SingleColorFactory(new Color(25, 60, 170)));
		cs.setFilterFactory(new CurvesRippleFilterFactory(cs.getColorFactory()));
		RandomFontFactory ff = new RandomFontFactory();
		ff.setMinSize(30);
		ff.setMaxSize(30);
		RandomWordFactory rwf = new RandomWordFactory();
		rwf.setMinLength(4);
		rwf.setMaxLength(4);
		cs.setWordFactory(rwf);
		cs.setFontFactory(ff);
		cs.setHeight(30);
		cs.setWidth(140);
		try {
			ServletOutputStream stream = response.getOutputStream();
			String validate_code = EncoderHelper.getChallangeAndWriteImage(cs,
					"png", stream);
			request.getSession().setAttribute("AUTHIMAGE", validate_code);
			stream.flush();
			stream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}*/

}
