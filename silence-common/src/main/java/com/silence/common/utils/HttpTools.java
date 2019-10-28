/**
 * Project Name:hotelfront
 * File Name:HttpTools.java
 * Package Name:com.iman.tool
 * Date:2015年11月4日下午1:23:51
 * Copyright (c) 1999 - 2015,iman, Inc or its affiliates. All Rights Reserved. 
*/

package com.silence.common.utils;

import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.SocketTimeoutException;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

/**
 * ClassName:HttpTools <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2015年11月4日 下午1:23:51 <br/>
 * 
 * @author haoxi
 */
public class HttpTools {
    private static final Logger LOGGER = LoggerFactory.getLogger(HttpTools.class);

    public static String doPost(String url, String data) {
        // 使用连接池创建连接
        PoolingHttpClientConnectionManager poolingHttpClientConnectionManager = new PoolingHttpClientConnectionManager();
        CloseableHttpClient httpClient = HttpClients.custom().setConnectionManager(poolingHttpClientConnectionManager)
                .build();
        CloseableHttpResponse response = null;
        try {
            HttpPost post = new HttpPost(url);
            // 设置请求和传输超时时间
            RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(10000).setConnectTimeout(10000)
                    .build();
            post.setConfig(requestConfig);
            ContentType contentType = ContentType.create(ContentType.APPLICATION_JSON.getMimeType(),
                    Charset.forName("UTF-8"));
            post.setEntity(new StringEntity(data, contentType));
            response = httpClient.execute(post, HttpClientContext.create());
            int statusCode = response.getStatusLine().getStatusCode();
            if (200 == statusCode) {
                HttpEntity entity = response.getEntity();
                String s = EntityUtils.toString(entity);
                // 关闭httpEntity流
                EntityUtils.consume(entity);
                return s;
            }
        } catch (ConnectTimeoutException e) {
            LOGGER.error("Connect Timeout.");
        } catch (SocketTimeoutException e) {
            LOGGER.error("Socket Timeout.");
        } catch (Exception e) {
            LOGGER.error("doPost Exception : ", e);
        } finally {
            if (null != response) {
                try {
                    // 关闭response
                    response.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    public static String escape(String src) {
        int i;
        char j;
        StringBuffer tmp = new StringBuffer();
        tmp.ensureCapacity(src.length() * 6);
        for (i = 0; i < src.length(); i++) {
            j = src.charAt(i);
            if (Character.isDigit(j) || Character.isLowerCase(j) || Character.isUpperCase(j))
                tmp.append(j);
            else if (j < 256) {
                tmp.append("%");
                if (j < 16)
                    tmp.append("0");
                tmp.append(Integer.toString(j, 16));
            } else {
                tmp.append("%u");
                tmp.append(Integer.toString(j, 16));
            }
        }
        return tmp.toString();
    }

    public static String unescape(String src) {
        StringBuffer tmp = new StringBuffer();
        tmp.ensureCapacity(src.length());
        int lastPos = 0, pos = 0;
        char ch;
        while (lastPos < src.length()) {
            pos = src.indexOf("%", lastPos);
            if (pos == lastPos) {
                if (src.charAt(pos + 1) == 'u') {
                    ch = (char) Integer.parseInt(src.substring(pos + 2, pos + 6), 16);
                    tmp.append(ch);
                    lastPos = pos + 6;
                } else {
                    ch = (char) Integer.parseInt(src.substring(pos + 1, pos + 3), 16);
                    tmp.append(ch);
                    lastPos = pos + 3;
                }
            } else {
                if (pos == -1) {
                    tmp.append(src.substring(lastPos));
                    lastPos = src.length();
                } else {
                    tmp.append(src.substring(lastPos, pos));
                    lastPos = pos;
                }
            }
        }
        return tmp.toString();
    }

    /**
     * @disc 对字符串重新编码
     * @param src
     * @return
     */
    public static String isoToGB(String src) {
        String strRet = null;
        try {
            strRet = new String(src.getBytes("ISO_8859_1"), "GB2312");
        } catch (Exception e) {

        }
        return strRet;
    }

    /**
     * @disc 对字符串重新编码
     * @param src
     * @return
     */
    public static String isoToUTF(String src) {
        String strRet = null;
        try {
            strRet = new String(src.getBytes("ISO_8859_1"), "UTF-8");
        } catch (Exception e) {

        }
        return strRet;
    }

    /**
     * 获取url无参数段
     * @param url
     * @return
     */
    public static String getUrlBase(String url){
        if(url.indexOf("?") != 0){
            String[] arrSplit = null;
            arrSplit = url.split("[?]");
            return arrSplit[0];
        }else{
            return url;
        }
    }
    
    public static String getUrlParam(String url){
        if(url.indexOf("?") >= 0){
            String[] arrSplit = null;
            arrSplit = url.split("[?]");
            return arrSplit[1];
        }else{
            return "";
        }
    }

    /**
     * 将url参数转换成map
     * 
     * @param param
     *            aa=11&bb=22&cc=33
     * @return
     */
    public static Map<String, Object> getUrlParamMap(String param) {
        Map<String, Object> map = new HashMap<String, Object>(0);
        if (StringUtils.isBlank(param)) {
            return map;
        }
        String[] params = param.split("&");
        for (int i = 0; i < params.length; i++) {
            String[] p = params[i].split("=");
            if (p.length == 2) {
                map.put(p[0], p[1]);
            }
        }
        return map;
    }

    /**
     * 将map转换成url
     * 
     * @param map
     * @return
     */
    public static String getUrlParamsByMap(Map<String, Object> map) {
        if (map == null) {
            return "";
        }
        StringBuffer sb = new StringBuffer();
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            sb.append(entry.getKey() + "=" + entry.getValue());
            sb.append("&");
        }
        String s = sb.toString();
        if (s.endsWith("&")) {
            s= StringUtils.substringBeforeLast(s, "&");
        }
        return s;
    }

    public static void main(String[] args) {
        /**
        String tmp = "小许";
        System.out.println("testing escape : " + tmp);
        tmp = escape(tmp);
        System.out.println(tmp);
        System.out.println("testing unescape :" + tmp);
        System.out.println(unescape("%u6211%u4eec"));
        System.out.println(isoToUTF(tmp));
        */
        
        String url = "https://www.baidu.com/s?abc=111";
        String params = getUrlParam(url);
        System.out.println( params);
        
        System.out.println(getUrlBase(url));
        
        Map<String, Object> map =getUrlParamMap(params);
        
        map.put("haha", "test");
        
        System.out.println(getUrlParamsByMap(map));
    }
}
