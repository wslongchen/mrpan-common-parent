package com.mrpan.common.core.utils;

import javax.servlet.http.HttpServletRequest;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by mrpan on 2016/11/3.
 */
public class WebUtil {

    public static final String getParam(HttpServletRequest request,
                                        String name, String defval) {
        String param = defval;
        if (request.getParameter(name) != null) {
            param = (String) request.getParameter(name);
        }
        return param.trim();
    }

    public static final char getParam(HttpServletRequest request, String name,
                                      char defval) {
        char param = defval;
        if (request.getParameter(name) != null) {
            param = request.getParameter(name).charAt(0);
        }
        return param;
    }

    public static final String[] getParam(HttpServletRequest request,
                                          String name, String[] defval) {
        String[] param = new String[0];
        if (request.getParameter(name) != null) {
            param = request.getParameterValues(name);
        }
        return param;
    }

    public static final String getChineseParam(HttpServletRequest request,
                                               String name, String defval) {
        String param = defval;
        if (request.getParameter(name) != null) {
            param = (String) request.getParameter(name);
            param = CharsetDecodeToUTF8(param);
        }
        return param.trim();
    }

    public static final String getAttribute(HttpServletRequest request,
                                            String name, String defval) {
        String param = defval;
        if (request.getAttribute(name) != null) {
            param = (String) request.getAttribute(name);
        }
        return param.trim();
    }

    // public static final String getParam(MultiPartRequestWrapper request,
    // String name, String defval) {
    // String param = defval;
    // if (request.getParameter(name) != null) {
    // param = (String) request.getParameter(name);
    // }
    // return param.trim();
    // }

    public static final int getParam(HttpServletRequest request, String name,
                                     int defval) {
        String param = request.getParameter(name);
        int value = defval;
        if (param != null) {
            try {
                value = Integer.parseInt(param);
            } catch (NumberFormatException ignore) {
            }
        }
        return value;
    }

    public static final boolean getParam(HttpServletRequest request,
                                         String name, boolean defval) {
        boolean param = defval;
        if (request.getParameter(name) != null) {
            param = Boolean.parseBoolean(request.getParameter(name));
        }
        return param;
    }

    public static final Date getParam(HttpServletRequest request, String name,
                                      Date defval) {
        String param = request.getParameter(name);
        Date value = defval;
        if (param != null) {
            try {
                if (param.length() > "yyyy-MM-dd".length()) {
                    SimpleDateFormat df = new SimpleDateFormat(
                            "yyyy-MM-dd HH:mm:ss");
                    value = df.parse(param);
                } else {
                    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                    value = df.parse(param);
                }

            } catch (Exception ex) {

            }
        }
        return value;
    }

    public static final float getParam(HttpServletRequest request, String name,
                                       float defval) {
        String param = request.getParameter(name);
        float value = defval;
        if (param != null) {
            try {
                value = Float.parseFloat(param);
            } catch (NumberFormatException ignore) {
            }
        }
        return value;
    }

    public static final long getParam(HttpServletRequest request, String name,
                                      long defval) {
        String param = request.getParameter(name);
        long value = defval;
        if (param != null) {
            try {
                value = Long.parseLong(param);
            } catch (NumberFormatException ignore) {
            }
        }
        return value;
    }

    public static final double getParam(HttpServletRequest request,
                                        String name, double defval) {
        String param = request.getParameter(name);
        double value = defval;
        if (param != null) {
            try {
                value = Double.parseDouble(param);
            } catch (NumberFormatException ignore) {
            }
        }
        return value;
    }

    public static final int getParam(HashMap<String, String> map, String name,
                                     int defval) {
        String param = map.get(name);
        int value = defval;
        if (param != null) {
            try {
                value = Integer.parseInt(param);
            } catch (NumberFormatException ignore) {
            }
        }
        return value;
    }

    public static final String getParam(HashMap<String, String> map,
                                        String name, String defval) {
        String param = defval;
        if (map.get(name) != null) {
            param = (String) map.get(name);
        }
        return param.trim();
    }

    public static final String getSession(HttpServletRequest request,
                                          String name, String defval) {
        String param = defval;
        if (request.getSession().getAttribute(name) != null)
            param = (String) request.getSession().getAttribute(name);
        return param;
    }

    public static final void removeSession(HttpServletRequest request,
                                           String name) {
        request.getSession().removeAttribute(name);
    }

    public static final void setAttribute(HttpServletRequest request,
                                          String name, String defval) {
        request.setAttribute(name, defval);
    }

    public static final void setAttribute(HttpServletRequest request,
                                          String name, ArrayList<String> defval) {
        request.setAttribute(name, defval);
    }

    public static final void setAttribute(HttpServletRequest request,
                                          String name, Object defval) {
        request.setAttribute(name, defval);
    }

    public static final String CharsetDecodeToUTF8(String str) {
        if (str == null)
            return "";
        String result = "";
        try {
            result = URLDecoder.decode(str, "utf-8");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }

    public static String[] convertArray(List<String> list) {
        String[] ar = new String[list.size()];
        for (int i = 0; i < list.size(); i++) {
            ar[i] = list.get(i);
        }
        return ar;
    }

    public static String getIpAddress(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }

    public static final String getParam(Map<String, String> map, String name,
                                        String defval) {
        String param = defval;
        if (map.get(name) != null) {
            param = (String) map.get(name);
        }
        return param.trim();
    }

    public static final Date getParam(Map<String, String> map, String name,
                                      Date defval) {
        String param = map.get(name);
        Date value = defval;
        if (param != null) {
            try {
                SimpleDateFormat df = new SimpleDateFormat(
                        "yyyy-MM-dd HH:mm:ss");
                value = df.parse(param);
            } catch (Exception ignore) {

            }
        }
        return value;
    }

    public static final double getParam(Map<String, String> map, String name,
                                        double defval) {
        String param = map.get(name);
        double value = defval;
        if (param != null) {
            try {
                value = Double.parseDouble(param);
            } catch (NumberFormatException ignore) {
            }
        }
        return value;
    }

    public static final int getParam(Map<String, String> map, String name,
                                     int defval) {
        String param = map.get(name);
        int value = defval;
        if (param != null) {
            try {
                value = Integer.parseInt(param);
            } catch (NumberFormatException ignore) {
            }
        }
        return value;
    }

    public static final long getParam(Map<String, String> map, String name,
                                      long defval) {
        String param = map.get(name);
        long value = defval;
        if (param != null) {
            try {
                value = Integer.parseInt(param);
            } catch (NumberFormatException ignore) {
            }
        }
        return value;
    }

    public static final boolean getParam(Map<String, String> map, String name,
                                         boolean defval) {
        boolean param = defval;
        if (map.get(name) != null) {
            param = Boolean.parseBoolean(map.get(name));
        }
        return param;
    }
}
