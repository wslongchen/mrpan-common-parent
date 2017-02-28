package com.mrpan.koalaapi.web.controller;

import com.google.gson.Gson;
import com.mrpan.common.core.utils.JsonResponse;
import com.mrpan.common.core.utils.RenderKit;
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
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping(value = "/")
@Scope(value = "prototype")
public class LoginController extends BaseController {
	private static final Logger logger = LoggerFactory
			.getLogger(LoginController.class);

	@Resource(name="ann_UserService")
	Ann_UserService ann_UserService;

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public void login(HttpServletRequest request, HttpServletResponse response) {
		logger.debug("login..");
		JsonResponse result=new JsonResponse(-1,"请先登录");
		Gson gson = new Gson();
		RenderKit.renderJson(response, gson.toJson(result));
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public void doLogin(String username, String password, HttpServletRequest request, HttpServletResponse response) {
		Subject subject = SecurityUtils.getSubject();
		JsonResponse result=new JsonResponse(0,"登录成功");
		UsernamePasswordToken token = new UsernamePasswordToken(username,
				password);
		token.setRememberMe(true);
		try {
			subject.login(token);
			Ann_User user = this.ann_UserService
					.findUser(username,"");
			if (user == null) {
				result=new JsonResponse(-1,"请先登录");
			}
			
			Session session = subject.getSession();
			session.setAttribute(WebConstant.LOGIN_USER, user);
			logger.info("login the system successfully");
		} catch (UnknownAccountException uae) {
			logger.error("###unknown account" + uae.getMessage(), uae);
			result=new JsonResponse(-1,"登录失败");
		} catch (IncorrectCredentialsException ice) {
			logger.error("###incorrect Credentials" + ice.getMessage(), ice);
			result=new JsonResponse(-1,"登录失败");
		} catch (LockedAccountException lae) {
			logger.error("locked Account " + lae.getMessage(), lae);
			result=new JsonResponse(-1,"登录失败");
		} catch (AuthenticationException ae) {
			logger.error("authentication error " + ae.getMessage(), ae);
			result=new JsonResponse(-1,"登录失败");
		} catch (Exception e) {
			logger.error("Other Exception" + e.getMessage(), e);
			result=new JsonResponse(-1,"登录失败");
		}
		Gson gson = new Gson();
		RenderKit.renderJson(response, gson.toJson(result));
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
