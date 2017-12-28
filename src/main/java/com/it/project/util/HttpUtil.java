package com.it.project.util;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 发送http请求
 * @author fengqigui
 * @Date 2017/12/28 18:55
 */
public class HttpUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(HttpUtil.class);

    private static final String ENCODE = "UTF-8";

    /**
     * 模仿post请求
     * @param url
     * @param paramsMap
     * @return
     */
    public static String post(String url, Map<String, String> paramsMap){
        CloseableHttpClient client = HttpClients.createDefault();
        String responseText = "";
        CloseableHttpResponse response = null;
        try{
            HttpPost method = new HttpPost(url);
            if (paramsMap != null) {
                List<NameValuePair> paramList = new ArrayList<>();
                for (Map.Entry<String, String> param : paramsMap.entrySet()) {
                    NameValuePair pair = new BasicNameValuePair(param.getKey(), param.getValue());
                    paramList.add(pair);
                }
                method.setEntity(new UrlEncodedFormEntity(paramList, ENCODE));
            }
            response = client.execute(method);
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                responseText = EntityUtils.toString(entity);
            }

        } catch (Exception e){
            LOGGER.error("http request failed",e);
            throw  new RuntimeException("请求失败");
        }finally {
            try{
                response.close();
            }catch (Exception e){
                LOGGER.error("response close exception",e);
            }
        }

        return responseText;

    }


    public static String get(String url, Map<String, String> paramsMap) {



        CloseableHttpClient client = HttpClients.createDefault();
        String responseText = "";
        CloseableHttpResponse response = null;
        try{
            String getUrl = url+"?";
            if (paramsMap != null) {
                for (Map.Entry<String, String> param : paramsMap.entrySet()) {
                    getUrl += param.getKey() + "=" + URLEncoder.encode(param.getValue(), ENCODE );
                }
            }
            HttpGet method = new HttpGet(getUrl);
            response = client.execute(method);
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                responseText = EntityUtils.toString(entity);
            }

        } catch (Exception e){
            LOGGER.error("http request failed",e);
            throw  new RuntimeException("请求失败");
        }finally {
            try{
                response.close();
            }catch (Exception e){
                LOGGER.error("response close exception",e);
            }
        }

        return responseText;




    }




}
