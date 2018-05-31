package io.mykit.lock.redis.factory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.Set;

import io.mykit.lock.redis.client.RedisClient;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * Redis工厂类
 * @author liuyazhuang
 *
 */
public class RedisFactory extends BaseFactory{
	
	private static volatile Properties properties;
	
	static{
		properties = new Properties();
		InputStream in = RedisFactory.class.getClassLoader().getResourceAsStream("redis.properties");
		try {
			properties.load(in);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static JedisPoolConfig getPoolConfig(){
		JedisPoolConfig config = new JedisPoolConfig();
		config.setMaxIdle(Integer.parseInt(properties.getProperty(MAXIDLE, "100")));
		config.setMinIdle(Integer.parseInt(properties.getProperty(MINIDLE, "1")));
		config.setMaxTotal(Integer.parseInt(properties.getProperty(MAXTOTAL, "1000")));
		return config;
		
	}
	
	public static RedisClient getDefaultClient(){
		JedisPool pool = new JedisPool(properties.getProperty(HOST, "127.0.0.1"));
		RedisClient client = new RedisClient(pool);
		return client;
	}
	
	public static void main(String[] args) {
		RedisClient client = getDefaultClient();
		Jedis jedis = client.getJedisPool().getResource();
		Set<String> keys = jedis.keys("*");
		for(String key : keys){
			System.out.println(key);
		}
	}
}
