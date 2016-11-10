package com.mrpan.manage.web.filter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.web.filter.PathMatchingFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * Created by mrpan on 2016/11/8.
 */
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