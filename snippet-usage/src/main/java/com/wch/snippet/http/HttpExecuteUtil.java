package com.wch.snippet.http;

import org.apache.commons.codec.binary.Base64;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * http请求工具
 */
public class HttpExecuteUtil {

    public enum MIME {


        /**
         * 入参类型-json
         */
        JSON("application/json;charset=UTF-8"),

        /**
         * 入参类型-纯文本
         */
        PLAIN_TEXT("text/plain");

        /**
         * 入参类型
         */
        private String contentType;

        MIME(String contentType) {
            this.contentType = contentType;
        }

        public String getContentType() {
            return contentType;
        }
    }

    private static HttpClientBuilder httpClientBuilder;

    static {
        httpClientBuilder = HttpClients.custom();
    }

    private static HttpClient getHttpClient() {
        return httpClientBuilder.build();
    }

    /**
     * 无参形式进行GET访问
     *
     * @param url
     * @return
     * @throws Exception
     */
    public static String doGet(String url) throws Exception {
        HttpGet httpGet = new HttpGet(url);
        httpGet.setHeader("Content-Type", MIME.JSON.getContentType());
        return EntityUtils.toString(getHttpClient().execute(httpGet).getEntity());
    }

    /**
     * 以Base64编码的形式进行GET访问
     *
     * @param url
     * @param param
     * @return
     * @throws Exception
     */
    public static String doGet(String url, String param) throws Exception {
        param = Base64.encodeBase64String(param.getBytes("UTF-8"));
        HttpGet httpGet = new HttpGet(url.concat("?").concat(param));
        httpGet.setHeader("Content-Type", MIME.PLAIN_TEXT.getContentType());
        String response = EntityUtils.toString(getHttpClient().execute(httpGet).getEntity());
        if (null != response) {
            response = new String(Base64.decodeBase64(response.getBytes("UTF-8")));
        }
        return response;
    }

    /**
     * 以参数形式进行GET访问
     *
     * @param url
     * @param paramMap
     * @return
     * @throws Exception
     */
    public static String doGet(String url, Map<String, String> paramMap) throws Exception {
        URIBuilder uriBuilder = new URIBuilder(url);
        for (Map.Entry<String, String> entry : paramMap.entrySet()) {
            uriBuilder.addParameter(entry.getKey(), entry.getValue());
        }
        HttpGet httpGet = new HttpGet(uriBuilder.build());
        httpGet.setHeader("Content-Type", MIME.JSON.getContentType());
        return EntityUtils.toString(getHttpClient().execute(httpGet).getEntity());
    }

    /**
     * 以参数形式进行POST访问
     *
     * @param url
     * @param paramMap
     * @return
     * @throws Exception
     */
    public static String doPost(String url, Map<String, String> paramMap) throws Exception {
        HttpPost httpPost = new HttpPost(url);
        List<NameValuePair> params = new ArrayList<>();
        for (Map.Entry<String, String> entry : paramMap.entrySet()) {
            params.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
        }
        httpPost.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));
        return EntityUtils.toString(getHttpClient().execute(httpPost).getEntity());
    }

    /**
     * 以JSON参数进行POST访问
     *
     * @param url
     * @param param
     * @param enableCode 是否使用Base64编码
     * @return
     * @throws Exception
     */
    public static String doPost(String url, String param, boolean enableCode) throws Exception {
        String contentType = MIME.JSON.getContentType();
        if (enableCode) {
            contentType = MIME.PLAIN_TEXT.getContentType();
            param = Base64.encodeBase64String(param.getBytes("UTF-8"));
        }
        HttpPost httpPost = new HttpPost(url);
        httpPost.setHeader("Content-Type", contentType);
        StringEntity entity = new StringEntity(param, "UTF-8");
        httpPost.setEntity(entity);
        String response = EntityUtils.toString(getHttpClient().execute(httpPost).getEntity());
        return enableCode ? new String(Base64.decodeBase64(response.getBytes("UTF-8"))) : response;
    }

    /**
     * 以JSON参数进行POST访问
     *
     * @param url
     * @param param
     * @return
     * @throws Exception
     */
    public static String doPost(String url, String param) throws Exception {
        return doPost(url, param, Boolean.FALSE);
    }

}
