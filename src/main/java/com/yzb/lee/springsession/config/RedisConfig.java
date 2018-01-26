package com.yzb.lee.springsession.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

@EnableRedisHttpSession(maxInactiveIntervalInSeconds=300)	// session有效时长, 单位为s
@EnableCaching	// 开启缓存
public class RedisConfig {
	
	@Value("${spring.redis.host}")
	private String hostName;

	@Value("${spring.redis.password}")
	private String password;

	@Value("${spring.redis.port}")
	private int port;

	@Value("${spring.redis.expire.time}")
	private int expireTime;
	
	@Value("${spring.redis.timeout}")
	private int timeout;
	
	@Bean
	public CacheManager cacheManager(RedisTemplate<String, String> redisTemplate)
	{
		System.out.println("CacheManager init......; expireTime = " + expireTime);
		RedisCacheManager cacheManager = new RedisCacheManager(redisTemplate);
		cacheManager.setDefaultExpiration(expireTime); 	// 设置缓存过期时间
		return cacheManager;
	}
	
	@Bean
    public JedisConnectionFactory connectionFactory() {
        JedisConnectionFactory connection = new JedisConnectionFactory();
        connection.setPort(port);
        connection.setHostName(hostName);
        connection.setPassword(password);
        connection.setTimeout(timeout);
        return connection;
    }
	
	@Bean
	public RedisTemplate<String, String> redisTemplate(RedisConnectionFactory cf)
	{
		RedisTemplate<String, String> redisTemplate = new RedisTemplate<String, String>();
		redisTemplate.setConnectionFactory(cf);
		// redisTemplate.setValueSerializer(new FastJson2JsonRedisSerializer<Object>(Object.class));
		redisTemplate.setValueSerializer(new RedisSerializer<Object>()
		{

			@Override
			public byte[] serialize(Object t) throws SerializationException
			{
				if (t == null)
				{
					return new byte[0];
				}
				return JSON.toJSONString(t, SerializerFeature.WriteClassName)
						.getBytes();
			}

			@Override
			public Object deserialize(byte[] bytes)
					throws SerializationException
			{
				if (bytes == null || bytes.length <= 0)
				{
					return null;
				}
				String str = new String(bytes);

				return (Object) JSON.parseObject(str, Object.class);
			}

		});
		return redisTemplate;
	}
}
