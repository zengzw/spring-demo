/*
 * @Project Name: springmvcdemo
 * @File Name: Response.java
 * @Package Name: com.test.learn.httpsocket
 * @Date: 2017年11月21日上午11:38:11
 * @Creator: zengzw-1220
 * @line------------------------------
 * @修改人: 
 * @修改时间: 
 * @修改内容: 
 */

package com.test.learn.httpsocket;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Date;

/**
 * TODO
 * @author zengzw-1220
 * @date 2017年11月21日上午11:38:11
 * @see
 */
public class Response {
	   //两个常量
    public static final String CRLF = "\r\n";
    public static final String BLANK = " ";
    //流
    private BufferedWriter bw;
    //正文
    private StringBuilder content;
    //存储头信息
    private StringBuilder headInfo;
    //存储正文长度
    private int len = 0;

    public Response(){
        headInfo = new StringBuilder();
        content = new StringBuilder();
        len = 0;
    }
    public Response(OutputStream os){
        this();
        bw = new BufferedWriter(new OutputStreamWriter(os));
    }
    public Response(Socket client){
        this();
        try {
            bw = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));
        } catch (IOException e) {
            headInfo = null;
        }
    }
    /**
     * 构建正文
     */
    public Response print(String info){
        content.append(info);
        len += (info + CRLF).getBytes().length;
        return this;
    }
    /**
     * 构建正文+回车
     */
    public Response println(String info){
        content.append(info).append(CRLF);
        len += (info + CRLF).getBytes().length;
        return this;
    }

    /**
     * 构建响应头
     */
    private void createHeadInfo(int code){
        //1)HTTP协议版本、状态代码、描述
        headInfo.append("HTTP/1.1").append(BLANK).append(code).append(BLANK);
        switch(code){
            case 200:
                headInfo.append("OK");
                break;
            case 404:
                headInfo.append("NOT FOUND");
                break;
            case 500:
                headInfo.append("Server Error");
                break;
        }
        headInfo.append(CRLF);
        //2)响应头（Response Head）
        headInfo.append("Server:test Server/0.0.1").append(CRLF);
        headInfo.append("Date:").append(new Date()).append(CRLF);
        headInfo.append("Content-type:text/html;charset=GBK").append(CRLF);
        //正文长度：字节长度
        headInfo.append("Content-type:").append(len).append(CRLF);
        headInfo.append(CRLF);
    }

    /**
     * 推送到客户端
     * @throws IOException 
     */
    void pushToClient(int code) throws IOException{
        if(null==headInfo){
            code = 500;
        }
        createHeadInfo(code);
        //头信息+分割符
        bw.append(headInfo.toString());
        //正文
        bw.append(content.toString());
        bw.flush();
        bw.close();
    }
}
