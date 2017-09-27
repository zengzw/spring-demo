/*
 * @Project Name: springmvcdemo
 * @File Name: RpcHandler.java
 * @Package Name: com.test.learn.rpc.server
 * @Date: 2017年9月21日下午6:08:53
 * @Creator: zengzw-1220
 * @line------------------------------
 * @修改人: 
 * @修改时间: 
 * @修改内容: 
 */

package com.test.learn.rpc.decode;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.test.learn.rpc.vo.RpcRequest;
import com.test.learn.rpc.vo.RpcResponse;

import clojure.lang.Obj;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import net.sf.cglib.reflect.FastClass;
import net.sf.cglib.reflect.FastMethod;

/**
 * TODO
 * @author zengzw-1220
 * @date 2017年9月21日下午6:08:53
 * @see
 */
public class RpcHandler extends SimpleChannelInboundHandler<RpcRequest>{

	
	private static final Logger LOGGER = LoggerFactory.getLogger(RpcHandler.class);

    private final Map<String, Object> handlerMap;

    public RpcHandler(Map<String, Object> handlerMap) {
        this.handlerMap = handlerMap;
    }

    
    
	@Override
	protected void channelRead0(ChannelHandlerContext ctx, RpcRequest req) throws Exception {
		RpcResponse response = new RpcResponse();
		response.setRequestId(req.getRequestId());
		
		try {
			Object result = handle(req);
			response.setResult(result);
		} catch (Exception e) {
			response.setError(e);
		}
		
		ctx.writeAndFlush(response).addListener(ChannelFutureListener.CLOSE);
		
	}
	
	
	/**
	 * 处理请求
	 * 
	 * @date 2017年9月26日上午11:31:54
	 * @author zengzw-1220
	 * @since 1.0.0 
	 * @param request
	 * @return
	 * @throws InvocationTargetException
	 */
	private  Object handle(RpcRequest request) throws InvocationTargetException{
		String className = request.getClassName();
		Object serviceBean = handlerMap.get(className);
		
		
	 Class<?> serviceClass =  serviceBean.getClass();
	 String methodName = request.getMethodName();
	 Class<?>[] paramterType = request.getParameterTypes();
	 Object[] paramters = request.getParameters();
	 
	 //执行目标方法
	 FastClass fastClass = FastClass.create(serviceClass);
	 FastMethod fastMethod = fastClass.getMethod(methodName,paramterType);
	 return fastMethod.invoke(serviceBean, paramters);
	 
	 
	}
	
	
}
