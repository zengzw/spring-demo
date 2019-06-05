/*
 * Copyright (c) 2013, FPX and/or its affiliates. All rights reserved.
 * Use, Copy is subject to authorized license.
 */
package com.test.learn.jsoup;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

/**
 *
 * @author zengzw
 * @date 2017年1月13日
 */
public class JsoupTest {

    public static void main(String[] args) throws InterruptedException {
    /*    Document doc;
        try {
         *//*   doc = Jsoup.connect("http://www.cnblogs.com/zyw-205520/archive/2012/12/20/2826402.html").get();
            String text = doc.outerHtml();
            System.out.println(text);*//*
            String text = "<h2>局百犊牛排加盟详情</h2></p><p><h1>kktalk<h1><img title=\"局百犊牛排加盟\" alt=\"局百犊牛排加盟\" src=\"http://www.35335.com/upload/image2018/201805/W73712769178.jpg\" width=\"730\" height=\"294\" /></p><p>局百犊牛排除了牛排，更着力于西式餐饮所专属的全餐享受，开发研制了一系列脍炙人口的沙拉、小吃、汤品和饮料，其中“自助沙拉吧”和“经典牛排”已经和“比萨”一样，成为了的代名词。近几年来，随着中外交往的不断加强，西餐必将在中国掀起新一轮的产业经济，西餐牛扒经济已然方兴未艾。是中国人对西方文化的吸纳与追捧。而国民物质生活的富裕，对能表达西方文化的西式餐饮会，更加地愈发热爱，愈发狂热。</p><p>局百犊牛排局百犊牛排在所有西式餐饮中，牛排，可谓是吃西餐的代名词。“吃牛排、品红酒”虽简短的六个字，却蕴含着西方文化的与众不同之处。是成功人士、高贵身份的象征，体现生活的品位;是追逐潮流、追求时尚，是对西方生活的憧憬与追求。人们常说，如果学会吃牛排，就可以顺利地迈入西式生活。</p><p>局百犊牛排在当今我国大中城市，西餐占有率不到百分之五，而中小城市西餐牛排馆更是一片空白，国内仅有为数不多的牛排馆，要么是过于走高端的市场定位，而使众多消费者望而却步;要么只打着牛排馆的旗号，又不能为消费者提供地道的西式餐饮、牛排品质与服务。</p><p>局百犊牛排以专业的技术，星级的服务，优秀的品质，推出分别针对高、中、低档消费的人群，为更多创业者提供一个良好的平台。始终本着“诚信经营、铸就辉煌”的宗旨，为每一位加盟商提供可靠的保障。</p></p>";
            Document document = Jsoup.parse(text);
            System.out.println(document.body().html());
        }catch(Exception e){
            e.printStackTrace();
        }*/

        System.out.println(System.currentTimeMillis());
        Thread.sleep(2000);
        System.out.println(System.currentTimeMillis());
    }
}
