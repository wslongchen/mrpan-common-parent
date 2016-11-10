package com.mrpan.manage.web.interceptor;

import com.mrpan.common.core.utils.PropertyUtils;
import com.mrpan.manage.web.WebConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by mrpan on 2016/11/8.
 */
public class SystemInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    private PropertyUtils propertyUtils;

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler) throws Exception {
        String contextPath = request.getContextPath();
        String resPath = contextPath + WebConstant.COMON_RES_PATH;
        String fastdfs_image_url = propertyUtils.getPropertiesString(WebConstant.FASTDFS_IMAGE_URL);
        request.setAttribute("base", contextPath);
        request.setAttribute("res", resPath);
        String path = request.getContextPath();
        String basePath = request.getScheme() + "://" + request.getServerName()
                + ":" + request.getServerPort() + path + "/";
        request.setAttribute("basepath", basePath);
        request.setAttribute("imageUrl", fastdfs_image_url);

        return super.preHandle(request, response, handler);
    }

    @Override
    public void postHandle(HttpServletRequest request,
                           HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {
        super.postHandle(request, response, handler, modelAndView);
    }

}

