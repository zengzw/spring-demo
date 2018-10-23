package com.test.springmvc.intercept.limit;

import com.alibaba.fastjson.JSON;
import com.google.common.util.concurrent.RateLimiter;
import org.apache.commons.lang.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * 请求速率限制切面
 *
 *
 * @author zengzw
 * @date 2018/9/27 14:59
 */
@Aspect
@Component
public class RateLimitAspect {
    private static final Logger logger =  LoggerFactory.getLogger(RateLimitAspect.class);

    private ConcurrentHashMap<String, RateLimiter> limiters = new ConcurrentHashMap<String, RateLimiter>();
    private ReentrantLock lock = new ReentrantLock();

    /**
     * 切入点
     */
    @Pointcut("execution(* com.xiaode.struts..*.*(..))")
    private void beforeAdd() {}


    /**
     * 速率请求限制
     *
     * @param jp
     * @param limit
     * @return
     * @throws Throwable
     */
    @Around("beforeAdd() && @annotation(limit)")
    public Object rateLimit(ProceedingJoinPoint jp, RequestLimit limit) throws  Throwable {
        RequestAttributes ra = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes sra = (ServletRequestAttributes) ra;
        HttpServletRequest request = sra.getRequest();

        Map<String, String> paramsMap = getRequestParamsMap(request);
        logger.info("#rateLimit APO# intercept class:{},reqparams:{}",jp.getSignature().toLongString(), JSON.toJSONString(paramsMap));

        String key = createKey(jp, limit);
        //创建limiter
        RateLimiter rateLimiter = createLimiter(key, limit);
        if(rateLimiter == null){
            logger.info("#rateLimit APO# rateLimiter is null,method:{}", jp.getSignature().toLongString());
            Object proceed = jp.proceed();
            return proceed;
        }

        //不抛弃请求的情况下，等待执行
        if(!limit.discard()){
            rateLimiter.acquire();
            return jp.proceed();
        }

        //请求超时时间，如果能在超时时间内获取到请求数，返回true，否则返回false
        long timeout = 1000 / limit.limitPermits();
        if(rateLimiter.tryAcquire(timeout,TimeUnit.MILLISECONDS)){
            return jp.proceed();
        }else{
            logger.error("#rateLimit APO# 请求太频繁，抛弃请求......,method:{}", jp.getSignature().toLongString());
        }

        return null;

    }


    private Map<String, String> getRequestParamsMap(HttpServletRequest request) {
        //获取所有请求参数字段
        Map<String,String> allParamsMap = new HashMap<String,String>();
        Enumeration enu=request.getParameterNames();
        while(enu.hasMoreElements()){
            String paraName=(String)enu.nextElement();
            allParamsMap.put(paraName,request.getParameter(paraName));
        }
        return allParamsMap;
    }

    /**
     * 构造RateLimiter关联的key
     *
     * @param pjp
     * @param rateLimiterMethod
     * @return
     */
    private String createKey(JoinPoint pjp, RequestLimit rateLimiterMethod) {
        //使用注解时指定了key
        if (StringUtils.isNotBlank(rateLimiterMethod.key())) {
            return rateLimiterMethod.key();
        }

        return KeyFactory.createKey(pjp);
    }


    /**
     * 构造RateLimiter,保证多线程环境下相同key对应的value不会被覆盖,且返回值相同
     *
     * @param key
     *            相同key返回同一个rateLimiter
     * @param rateLimiterMethod
     * @return
     */
    private RateLimiter createLimiter(String key, RequestLimit rateLimiterMethod) {
        RateLimiter result = limiters.get(key);
        if (result == null) {
            logger.info("#---RateLimiter is null,create......key:{}",key);
            RateLimiter value = RateLimiter.create(rateLimiterMethod.limitPermits());
            result = value;

            RateLimiter putByOtherThread = limiters.putIfAbsent(key,value);
            if(putByOtherThread != null){
                result = putByOtherThread;
            }

        }

        return result;
    }


}
