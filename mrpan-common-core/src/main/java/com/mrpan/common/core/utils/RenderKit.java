package com.mrpan.common.core.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;

/**
 * Created by mrpan on 2016/11/3.
 */
public class RenderKit {
    public static final Logger log = LoggerFactory.getLogger(RenderKit.class);

    /**
     * 发送文本。使用UTF-8编码。
     * @param response
     *            HttpServletResponse
     * @param text
     *            发送的字符串
     */
    public static void renderText(HttpServletResponse response, String text) {
        render(response, "text/plain;charset=UTF-8", text);
    }

    /**
     * 发送json。使用UTF-8编码。
     * @param response
     *            HttpServletResponse
     * @param text
     *            发送的字符串
     */
    public static void renderJson(HttpServletResponse response, String text) {
        render(response, "application/json;charset=UTF-8", text);
    }

    /**
     * 发送xml。使用UTF-8编码。
     * @param response
     *            HttpServletResponse
     * @param text
     *            发送的字符串
     */
    public static void renderXml(HttpServletResponse response, String text) {
        render(response, "text/xml;charset=UTF-8", text);
    }

    /**
     * 发送html。使用UTF-8编码。
     * @param response
     * 			HttpServletResponse
     * @param text
     * 			发送的字符串
     */
    public static void renderHtml(HttpServletResponse response, String text) {
        render(response, "text/html;charset=UTF-8", text);
    }

    /**
     * 发送内容。使用UTF-8编码。
     * @param response
     * @param contentType
     * @param text
     */
    public static void render(HttpServletResponse response, String contentType,
                              String text) {
        response.setContentType(contentType);
        response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        try {
            response.getWriter().write(text);
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
    }

    /**
     * 下载文件
     * @param response
     * @param file
     */
    public static void renderFile(HttpServletResponse response, File file) {
        try {
            InputStream fis = new BufferedInputStream(new FileInputStream(
                    file.getPath()));
            byte[] buffer = new byte[fis.available()];
            fis.read(buffer);
            fis.close();
            // 清空response
            response.reset();

            OutputStream toClient = new BufferedOutputStream(
                    response.getOutputStream());
            response.setContentType("application/octet-stream");
            response.setHeader("Content-Disposition", "attachment;filename="
                    + URLEncoder.encode(file.getName(), "UTF-8"));

            toClient.write(buffer);
            toClient.flush();
            toClient.close();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        } finally {
            try {
                File f = new File(file.getPath());
                f.delete();
            } catch (Exception e) {
                log.error(e.getMessage(), e);
            }
        }
    }
}
