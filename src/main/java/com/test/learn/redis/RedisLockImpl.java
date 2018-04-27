/*
 * @Project Name: springmvcdemo
 * @File Name: RedisLockImpl.java
 * @Package Name: com.test.learn.redis
 * @Date: 2018年4月20日下午4:14:57
 * @Creator: zengzw-1220
 * @line------------------------------
 * @修改人: 
 * @修改时间: 
 * @修改内容: 
 */

package com.test.learn.redis;

import java.util.Arrays;

import org.apache.log4j.Logger;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * TODO
 * @author zengzw-1220
 * @date 2018年4月20日下午4:14:57
 * @see
 */
public class RedisLockImpl implements IRedisLock{

	 protected Logger logger = Logger.getLogger(getClass());

	    // 锁超时时间（单位ms）
	    public static final long EXPIRE = 20000;

	    private RedisTemplate<Object, Object> redisTemplate;

	    public RedisLockImpl(RedisTemplate<Object, Object> redisTemplate){
	        super();
	        this.redisTemplate = redisTemplate;
	    }

	    private RedisConnection getRedisConnection(){
	        return redisTemplate.getConnectionFactory().getConnection();
	    }

	    @Override
	    public boolean lock(String lock){
	        Long value = System.currentTimeMillis() + EXPIRE + 1;
	        boolean locked = false;
	        RedisConnection redisConnection = getRedisConnection();
	        try{
	            if(redisConnection.setNX(lock.getBytes(), Long.toString(value).getBytes())){
	                //获取到锁
	                locked = true;
	            } else{
	                //没有获取到锁，说明锁仍然被其他对象保持，检查其是否已经超时
	                byte[] oldValue = redisConnection.get(lock.getBytes());
	                if(oldValue != null && oldValue.length > 0){
	                    long oldTime = Long.parseLong(new String(oldValue));
	                    //超时
	                    if(oldTime < System.currentTimeMillis()){
	                        byte[] getValue = redisConnection.getSet(lock.getBytes(), Long.toString(value).getBytes());
	                        if(Arrays.equals(oldValue, getValue)){
	                            locked = true;
	                        } else{
	                            //已被其它进程获取
	                            locked = false;
	                        }
	                    } else{
	                        locked = false;
	                    }
	                } else{
	                    //表示锁已释放,设置锁
	                    locked = redisConnection.setNX(lock.getBytes(), Long.toString(value).getBytes());
	                }
	            }
	        } catch(Exception e){
	            logger.error("获取锁失败" + lock, e);
	        } finally{
	            redisConnection.close();
	        }
	        return locked;
	    }

	    @Override
	    public void tryUnlock(String lock){
	        long current = System.currentTimeMillis();
	        RedisConnection redisConnection = getRedisConnection();
	        try{
	            byte[] value = redisConnection.get(lock.getBytes());
	            long tiem = Long.parseLong(new String(value));
	            // 避免删除非自己获取得到的锁
	            if(current < tiem){
	                redisConnection.del(lock.getBytes());
	            }
	        } catch(Exception e){
	            logger.error("尝试释放锁失败！：" + lock, e);
	        } finally{
	            redisConnection.close();
	        }
	    }

	    @Override
	    public boolean tryLock(String lock, long time){
	        boolean locked = false;
	        RedisConnection redisConnection = getRedisConnection();
	        try{
	            Long value = System.currentTimeMillis() + EXPIRE + 1;
	            if(redisConnection.setNX(lock.getBytes(), Long.toString(value).getBytes())){
	                //获取到锁
	                locked = true;
	            } else{
	                long expireTime = System.currentTimeMillis() + time;
	                while(System.currentTimeMillis() < expireTime){
	                    locked = redisConnection.setNX(lock.getBytes(), Long.toString(value).getBytes());
	                    if(locked){
	                        break;
	                    }
	                    Thread.sleep(1000);
	                    value = System.currentTimeMillis() + EXPIRE + 1;
	                }
	            }
	        } catch(Exception e){
	            logger.error("尝试获取锁失败！：" + lock, e);
	        } finally{
	            redisConnection.close();
	        }
	        return locked;
	    }

	    @Override
	    public void unLock(String lock){
	        logger.info("释放锁：" + lock);
	        RedisConnection redisConnection = getRedisConnection();
	        try{
	            redisConnection.del(lock.getBytes());
	        } catch(Exception e){
	            logger.error("释放锁失败！：" + lock, e);
	        } finally{
	            redisConnection.close();
	        }
	    }
}
