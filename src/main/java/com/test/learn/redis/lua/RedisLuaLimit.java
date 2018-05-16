package com.test.learn.redis.lua;

import java.io.File;
import java.nio.charset.Charset;

import com.google.common.collect.Lists;
import com.google.common.io.Files;

import redis.clients.jedis.Jedis;

/**
 * redis + lua 限流
 * 
 * @author zengzw-1220
 * @date 2018年5月14日上午10:21:14
 */
public class RedisLuaLimit {

    public static boolean acquire() throws Exception {
    	
    	String filePath = RedisLuaLimit.class.getClassLoader().getResource("lua/redis_limit.lua").getPath();
        String luaScript = Files.toString(new File(filePath), Charset.defaultCharset());
        Jedis jedis = new Jedis("192.168.10.44", 7003);
        jedis.auth("hhly");
        String key = "ip:" + System.currentTimeMillis()/ 1000; //此处将当前时间戳取秒数
        String limit = "3"; //限流大小
        return (Long)jedis.eval(luaScript,Lists.newArrayList(key), Lists.newArrayList(limit)) == 1;
    }
    
    
    public static void main(String[] args){
    	
    	for(int i = 0; i < 200; i++){
    		try {
				System.out.println(acquire());
			} catch (Exception e) {
				e.printStackTrace();
			}
    	}
	}
}
