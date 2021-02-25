package com.biao.xb.practice;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

public class Practice {

    @Test
    public void test() throws Exception {
        //获取 HttpClient 实例（用于后期执行请求）
        CloseableHttpClient client = HttpClients.createDefault();
        //封装一个 HttpGet 类型请求
        HttpGet httpGet = new HttpGet("http://localhost:8080/dept/findAll");

        //响应结果集
        CloseableHttpResponse response = client.execute(httpGet);

        //获取响应实体
        HttpEntity entity = response.getEntity();
        System.out.println("响应状态："+response.getStatusLine());

        if (entity != null) {
            System.out.println("响应内容长度为："+entity.getContentLength());

            //设置响应内容的编码（默认为 UTF-8）

        }

        response.close();
        client.close();
    }

    @Test
    public void test2() throws Exception {
        //获取 HttpClient 实例
        CloseableHttpClient client = HttpClients.createDefault();

        //请求参数
        StringBuilder params = new StringBuilder();
        params.append("?");
        //字符数据最好 encoding 一下，这样一来，某些字符串才能传过去
        params.append("username="+"&");

        HttpGet httpGet = new HttpGet("http://localhost:8080/user/checkUsername" + params);

        //发送请求，获取响应结果
        CloseableHttpResponse response = client.execute(httpGet);

        //获取响应实体
        HttpEntity entity = response.getEntity();
        System.out.println("响应状态："+response.getStatusLine());
        if (entity != null) {
            System.out.println("响应内容长度为："+entity.getContentLength());
            System.out.println("响应内容为："+ EntityUtils.toString(entity));
        }

        response.close();
        client.close();
    }

    /**
     * Get 请求使用 URI 方式
     * @throws Exception
     */
    @Test
    public void test3() throws Exception {
        //获取 HttpClient 实例
        CloseableHttpClient client = HttpClients.createDefault();

        //将参数放入键值对类 NameValuePair 中，再放入集合中
        List<NameValuePair> params = new ArrayList<>();
        params.add(new BasicNameValuePair("username","admin"));

        //设置 uri 信息，并将参数集合放入 uri
        // 这里也支持一个键值对一个键值对的往里面放 setParameter(String key,String value)
        URI uri = new URIBuilder()
                            .setScheme("http")
                            .setHost("localhost")
                            .setPort(8080)
                            .setPath("/user/checkUsername")
                            .setParameters(params).build();

        HttpGet httpGet = new HttpGet(uri);
        //发送请求，获取响应结果
        CloseableHttpResponse response = client.execute(httpGet);
        //获取响应实体
        HttpEntity entity = response.getEntity();
        System.out.println("响应状态："+response.getStatusLine());
        if (entity != null) {
            System.out.println("响应内容长度为："+entity.getContentLength());
            System.out.println("响应内容为："+ EntityUtils.toString(entity));
        }
        response.close();
        client.close();
    }
}
