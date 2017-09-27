/*
 * @Project Name: springmvcdemo
 * @File Name: SerializationUtil.java
 * @Package Name: com.test.learn.rpc.util
 * @Date: 2017年9月21日下午5:08:36
 * @Creator: zengzw-1220
 * @line------------------------------
 * @修改人: 
 * @修改时间: 
 * @修改内容: 
 */

package com.test.learn.rpc.util;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.objenesis.Objenesis;
import org.objenesis.ObjenesisStd;

import com.dyuproject.protostuff.LinkedBuffer;
import com.dyuproject.protostuff.ProtostuffIOUtil;
import com.dyuproject.protostuff.Schema;
import com.dyuproject.protostuff.runtime.RuntimeSchema;



/**
 * TODO
 * @author zengzw-1220
 * @date 2017年9月21日下午5:08:36
 * @see
 */
public class SerializationUtil {
	
	 private static Map<Class<?>, Schema<?>> cachedSchema = new ConcurrentHashMap<>();

	    private static Objenesis objenesis = new ObjenesisStd(true);

	    private SerializationUtil() {
	    }

	    @SuppressWarnings("unchecked")
	    private static <T> Schema<T> getSchema(Class<T> cls) {
	        Schema<T> schema = (Schema<T>) cachedSchema.get(cls);
	        if (schema == null) {
	            schema = RuntimeSchema.createFrom(cls);
	            if (schema != null) {
	                cachedSchema.put(cls, schema);
	            }
	        }
	        return schema;
	    }

	    @SuppressWarnings("unchecked")
	    public static <T> byte[] serialize(T obj) {
	        Class<T> cls = (Class<T>) obj.getClass();
	        LinkedBuffer buffer = LinkedBuffer.allocate(LinkedBuffer.DEFAULT_BUFFER_SIZE);
	        try {
	            Schema<T> schema = getSchema(cls);
	            return ProtostuffIOUtil.toByteArray(obj, schema, buffer);
	        } catch (Exception e) {
	            throw new IllegalStateException(e.getMessage(), e);
	        } finally {
	            buffer.clear();
	        }
	    }

	    public static <T> T deserialize(byte[] data, Class<T> cls) {
	        try {
	            T message = (T) objenesis.newInstance(cls);
	            Schema<T> schema = getSchema(cls);
	            ProtostuffIOUtil.mergeFrom(data, message, schema);
	            return message;
	        } catch (Exception e) {
	            throw new IllegalStateException(e.getMessage(), e);
	        }
	    }
}
