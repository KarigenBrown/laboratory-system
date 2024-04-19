package edu.bistu.utils;

import cn.hutool.core.util.CharsetUtil;
import cn.hutool.http.ContentType;
import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
public class WebUtils {

    private WebUtils() {
    }

    public static void renderString(HttpServletResponse response, String string) {
        response.setStatus(200);
        response.setContentType(ContentType.JSON.toString());
        response.setCharacterEncoding(CharsetUtil.UTF_8);
        try {
            response.getWriter().print(string);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void setDownLoadHeader(String filename, ServletContext context, HttpServletResponse response) throws UnsupportedEncodingException {
        String mimeType = context.getMimeType(filename);
        response.setHeader("content-type", mimeType);
        String urlFilename = URLEncoder.encode(filename, StandardCharsets.UTF_8);
        response.setHeader("Content-disposition", "attachment; filename=" + urlFilename);
    }

    public static void setDownLoadHeader(String filename, HttpServletResponse response) {
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setCharacterEncoding(StandardCharsets.UTF_8.toString());
        String urlFilename = URLEncoder.encode(filename, StandardCharsets.UTF_8)
                .replaceAll("\\+", "%20");
        response.setHeader("Content-disposition", "attachment; filename=" + urlFilename);
    }
}
