package com.demo.common.utils;

import com.alibaba.fastjson.JSON;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Iterator;
import java.util.Map;

/**
 * @Auther: Minsky
 * @Date: 2019/1/5 15:54
 * @Description:
 */
public class HttpUtil {

    private static final Logger logger = LoggerFactory.getLogger(HttpUtil.class);

    private static final String POST = "post";
    private static final String GET = "get";
    private static final String UTF8 = "UTF-8";

    /**
     * GET 请求 URL参数通过params 传入
     * @param url
     * @param params
     * @return 调用成功返回 String 失败返回null
     */
    public static String get(String url, Map<String,String> params){
        // 拼装参数及返回结果
        StringBuilder psb = new StringBuilder();
        Iterator<String> keysIt = params.keySet().iterator();
        try {
            // 调用参数URL编码并 存入
            for(String key;keysIt.hasNext();){
                key = keysIt.next();
                if(null != params.get(key)){
                    psb.append(key).append("=").append(URLEncoder.encode(params.get(key),UTF8)).append("&");
//                    psb.append(key).append("=").append(params.get(key)).append("&");
                }
            }
            String paramSeries =  psb.insert(0,"?").toString();
            url = url + paramSeries;

            // 获取连接的字符流
            URL u = new URL(url);
            HttpURLConnection con = (HttpURLConnection) u.openConnection();
            // 若返回结果乱码 则在构造InputStreamReader 时指定编码格式
            BufferedReader reader = new BufferedReader(new InputStreamReader(con.getInputStream(),UTF8));
            String line;
            StringBuilder rsb = new StringBuilder();
            while(null != (line = reader.readLine())){
                rsb.append(line);
            }
            // 关闭流
            reader.close();
            // 断开连接
            con.disconnect();
            return rsb.toString();

        } catch (IOException e) {
            logger.error("get请求调用错误{}",e);
        }
        return null;
    }



    /**
     * psot 请求
     * @param url
     * @param params
     * @return
     */
    public static String post(String url, Map<String,Object>params) {
        try {
            StringBuilder result = new StringBuilder();
            URL u = new URL(url);
            HttpURLConnection con = (HttpURLConnection) u.openConnection();
            con.setDoInput(true);
            con.setDoOutput(true);
            con.setAllowUserInteraction(false);
            con.setUseCaches(false);
            con.setRequestMethod(POST);
            con.setRequestProperty("Content-Type", "application/json");
            con.setConnectTimeout(5000);
            con.setReadTimeout(10000);
            String line;

            String paramStr = JSON.toJSONString(params);
            // 先向URL连接中写入传参
            if (StringUtils.isNotBlank(paramStr)) {
                OutputStream out = con.getOutputStream();
                out.write(paramStr.getBytes(UTF8));
                out.flush();
                out.close();
            }
            // 再从连接中读取返回结果
            BufferedReader reader = new BufferedReader(new InputStreamReader(con.getInputStream(), UTF8));
            while (StringUtils.isNotBlank(line = reader.readLine())) {
                result.append(line);
            }
            reader.close();
            return result.toString();
        } catch (IOException e) {
            logger.error("请求错误：{}" + url + "," + params, e);
            return null;
        }
    }
}
