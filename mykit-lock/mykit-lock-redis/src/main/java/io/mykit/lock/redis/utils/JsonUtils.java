package io.mykit.lock.redis.utils;

import com.alibaba.fastjson.JSON;

/**
 * Json工具类
 * @author liuyazhuang
 *
 */
public class JsonUtils {
	public static  String beanToJson(Object o){
		return JSON.toJSONString(o);
	}
	//parse an object from 
	public static <T> T jsonToBean(String json,Class<T> cls){
		return JSON.parseObject(json, cls);
	} 
}
