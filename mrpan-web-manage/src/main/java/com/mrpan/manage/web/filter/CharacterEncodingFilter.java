package com.mrpan.manage.web.filter;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Map;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
/**
 * Created by mrpan on 2016/11/8.
 */
public class CharacterEncodingFilter implements Filter {
    public static final String PARAM_ENCODING_NAME = "encoding";
    public static final String UTF_8 = "UTF-8";
    public static final String ISO_8859_1 = "ISO-8859-1";

    private String encoding = CharacterEncodingFilter.UTF_8;

    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {
        HttpServletRequest requ = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        request.setCharacterEncoding(CharacterEncodingFilter.UTF_8);
        response.setCharacterEncoding(CharacterEncodingFilter.UTF_8);

        MyHttpServletRequest myrequest = new MyHttpServletRequest(requ);
        chain.doFilter(myrequest, resp);
    }

    public void init(FilterConfig filterConfig) throws ServletException {
        String _encoding = filterConfig
                .getInitParameter(CharacterEncodingFilter.PARAM_ENCODING_NAME);
        if (StringUtils.isNotBlank(_encoding)) {
            encoding = _encoding;
        }
    }

    private class MyHttpServletRequest extends HttpServletRequestWrapper {
        private HttpServletRequest request;

        private boolean flag = false;

        public MyHttpServletRequest(HttpServletRequest request) {
            super(request);
            this.request = request;
        }

        @Override
        public String getParameter(String name) {
            Map<String, String[]> map = getParameterMap();
            if (map != null) {
                String[] values = map.get(name);
                if (values != null) {
                    return values[0];
                }
            }
            return null;
        }

        @Override
        public String[] getParameterValues(String name) {
            Map<String, String[]> map = getParameterMap();
            if (map != null) {
                String[] values = map.get(name);
                if (values != null) {
                    return values;
                }
            }
            return null;
        }

        @Override
        public Map<String, String[]> getParameterMap() {
            if ("post".equalsIgnoreCase(request.getMethod())) {
                try {
                    request.setCharacterEncoding(encoding);
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                return request.getParameterMap();
            } else {
                // get鏂瑰紡鎻愪氦
                Map<String, String[]> paramMap = request.getParameterMap();
                if (!flag) {
                    for (Map.Entry<String, String[]> me : paramMap.entrySet()) {
                        String[] values = me.getValue();

                        for (int i = 0; i < values.length; i++) {
                            try {
                                values[i] = new String(
                                        values[i]
                                                .getBytes(CharacterEncodingFilter.ISO_8859_1),
                                        encoding);
                            } catch (UnsupportedEncodingException e) {
                                e.printStackTrace();
                            }
                        }

                    }
                    flag = true;
                }
                return paramMap;
            }
        }
    }

    public void destroy() {

    }
}

