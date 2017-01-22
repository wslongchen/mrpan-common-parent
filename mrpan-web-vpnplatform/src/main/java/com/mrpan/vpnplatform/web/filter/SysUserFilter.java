package com.mrpan.vpnplatform.web.filter;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.web.filter.PathMatchingFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class SysUserFilter extends PathMatchingFilter {
	private static final Logger logger = LoggerFactory
			.getLogger(SysUserFilter.class);

	@Override
	protected boolean onPreHandle(ServletRequest request,
			ServletResponse response, Object mappedValue) throws Exception {
		logger.info("#################### SysUserFilter ##################");
		String username = (String) SecurityUtils.getSubject().getPrincipal();

		// HttpServletRequest req = (HttpServletRequest) request;
		// req.getSession().setAttribute(WebConstant.LOGIN_USER,crma_CustomerInfoService.findByJobNo(username));
		return true;
	}
}
