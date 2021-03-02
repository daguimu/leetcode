/*
 * Copyright (c) 2021 maoyan.com
 * All rights reserved.
 *
 */
package com.guimu.leetcode.engineering;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.HttpClientUtils;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.http.HttpStatus;

/**
 * @author Guimu
 * @date 2021/02/27
 */
public class Crawler {

    private static final String AGENT_VAL = "Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/74.0.3729.169 Safari/537.36";

    public static List<String> crawlerByUrl(String baseUrl,
        Function<Document, List<String>> function) {
        //1.生成httpclient，相当于该打开一个浏览器
        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse response = null;
        //2.创建get请求，相当于在浏览器地址栏输入 网址
        HttpGet request = new HttpGet(baseUrl);
        //设置请求头，将爬虫伪装成浏览器
        request.setHeader("User-Agent", AGENT_VAL);
//HttpHost proxy = new HttpHost("60.13.42.232", 9999);
//RequestConfig config = RequestConfig.custom().setProxy(proxy).build();
//request.setConfig(config);
        try {
            //3.执行get请求，相当于在输入地址栏后敲回车键
            response = httpClient.execute(request);

            //4.判断响应状态为200，进行处理
            if (response.getStatusLine().getStatusCode() == HttpStatus.OK.value()) {
                //5.获取响应内容
                HttpEntity httpEntity = response.getEntity();
                String html = EntityUtils.toString(httpEntity, "utf-8");
                System.out.println(html);

                /**
                 * 下面是Jsoup展现自我的平台
                 */
                //6.Jsoup解析html
                Document document = Jsoup.parse(html);
                List<String> result = function.apply(document);
                return result;

            } else {
                //如果返回状态不是200，比如404（页面不存在）等，根据情况做处理，这里略
                System.out.println("返回状态不是200");
                System.out.println(EntityUtils.toString(response.getEntity(), "utf-8"));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //6.关闭
            HttpClientUtils.closeQuietly(response);
            HttpClientUtils.closeQuietly(httpClient);
        }
        return null;
    }

    public static void main(String[] args) {
        Function<Document, List<String>> function = (document) -> {
            List<String> result = new ArrayList<>();
            //像js一样，通过标签获取title
            Elements links = document.select("a[href]");
            //像js一样，通过id 获取文章列表元素对象
            //像js一样，通过class 获取列表下的所有博客
            //循环处理每篇博客
            for (Element postItem : links) {
                //像jquery选择器一样，获取文章标题元素
                result.add(postItem.text());
            }
            return result;
        };

        List<String> list = crawlerByUrl("https://www.cnblogs.com/", function);
        System.out.println(list);
    }
}
