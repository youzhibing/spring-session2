package com.yzb.lee.springsession.config;

import java.util.HashSet;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisClusterConfiguration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisNode;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import org.springframework.util.StringUtils;

import redis.clients.jedis.JedisPoolConfig;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.yzb.lee.springsession.exception.LocalException;

@EnableRedisHttpSession(maxInactiveIntervalInSeconds = 300) // session有效时长, 单位为s
@EnableCaching // 开启缓存
@PropertySource("redis/redis.properties")
// @ConfigurationProperties(prefix="spring.redis")		// 前缀配置, 这样配置可以将spring.redis.password的值赋值给属性password
public class RedisConfig {

	private static final Logger LOGGER = LoggerFactory.getLogger(RedisConfig.class);

	/*
	 * @Value("${spring.redis.host}") private String hostName;
	 * 
	 * @Value("${spring.redis.port}") private int port;
	 */

	@Value("${spring.redis.password}")
	private String password;

	@Value("${spring.redis.timeout}")
	private int timeout;

	@Value("${spring.redis.pool.maxActive}")
	private int maxTotal;

	@Value("${spring.redis.pool.maxIdle}")
	private int maxIdle;

	@Value("${spring.redis.pool.minIdle}")
	private int minIdle;

	@Value("${spring.redis.pool.maxWaitMillis}")
	private long maxWaitMillis;

	@Value("${spring.redis.pool.numTestsPerEvictionRun}")
	private int numTestsPerEvictionRun;

	@Value("${spring.redis.pool.timeBetweenEvictionRunsMillis}")
	private long timeBetweenEvictionRunsMillis;

	@Value("${spring.redis.pool.minEvictableIdleTimeMillis}")
	private long minEvictableIdleTimeMillis;

	@Value("${spring.redis.pool.softMinEvictableIdleTimeMillis}")
	private long softMinEvictableIdleTimeMillis;

	@Value("${spring.redis.pool.testOnBorrow}")
	private boolean testOnBorrow;

	@Value("${spring.redis.pool.testWhileIdle}")
	private boolean testWhileIdle;

	@Value("${spring.redis.pool.testOnReturn}")
	private boolean testOnReturn;

	@Value("${spring.redis.pool.blockWhenExhausted}")
	private boolean blockWhenExhausted;

	@Value("${spring.redis.expire.time}")
	private int expireTime;

	@Value("${spring.cluster.host}")
	private String clusterHosts;

	@Value("${spring.cluster.port}")
	private String clusterPorts;

	/*
	 * @Value("${spring.cluster.socketTimeout}") private int socketTimeout;
	 * 
	 * @Value("${spring.cluster.maxAttempts}") private int maxAttempts;
	 */

	@Value("${spring.cluster.maxRedirects}")
	private int maxRedirects;

	@Value("${spring.cluster.usePool}")
	private boolean usePool;

	@Bean
	public CacheManager cacheManager(RedisTemplate<String, String> redisTemplate) {
		LOGGER.info("CacheManager init......; expireTime = ", expireTime);
		RedisCacheManager cacheManager = new RedisCacheManager(redisTemplate);
		cacheManager.setDefaultExpiration(expireTime); // 设置缓存过期时间
		return cacheManager;
	}

	@SuppressWarnings("rawtypes")
	@Bean(name="redisTemplate")
	public RedisTemplate<String, String> redisTemplate(RedisConnectionFactory cf, RedisSerializer redisSerializer) {
		RedisTemplate<String, String> redisTemplate = new RedisTemplate<String, String>();
		redisTemplate.setConnectionFactory(cf);
		redisTemplate.setKeySerializer(redisSerializer);
		redisTemplate.setValueSerializer(redisSerializer);
		return redisTemplate;
	}

	@Bean
	public JedisConnectionFactory connectionFactory(
			RedisClusterConfiguration redisClusterConfig,
			JedisPoolConfig jedisPoolConfig) {
		JedisConnectionFactory connectionFactory = new JedisConnectionFactory(
				redisClusterConfig, jedisPoolConfig);
		connectionFactory.setUsePool(usePool);
		connectionFactory.setPassword(password);

		return connectionFactory;
	}

	@SuppressWarnings("rawtypes")
	@Bean
	public RedisSerializer redisSerializer()
	{
		return new RedisSerializer<Object>()
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

		};
	}

	// 单机连接池
	/*
	 * @Bean public JedisPool jedisPool(JedisPoolConfig jedisPoolConfig) {
	 * JedisPool jedisPool = new JedisPool(jedisPoolConfig, hostName, port,
	 * timeout, password); return jedisPool; }
	 */

	@Bean
	public JedisPoolConfig jedisPoolConfig() {
		JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
		jedisPoolConfig.setMaxTotal(maxTotal);
		jedisPoolConfig.setMaxIdle(maxIdle);
		jedisPoolConfig.setMinIdle(minIdle);
		jedisPoolConfig.setMaxWaitMillis(maxWaitMillis);
		jedisPoolConfig.setNumTestsPerEvictionRun(numTestsPerEvictionRun);
		jedisPoolConfig
				.setTimeBetweenEvictionRunsMillis(timeBetweenEvictionRunsMillis);
		jedisPoolConfig
				.setMinEvictableIdleTimeMillis(minEvictableIdleTimeMillis);
		jedisPoolConfig
				.setSoftMinEvictableIdleTimeMillis(softMinEvictableIdleTimeMillis);
		jedisPoolConfig.setTestOnBorrow(testOnBorrow);
		jedisPoolConfig.setTestWhileIdle(testWhileIdle);
		jedisPoolConfig.setTestOnReturn(testOnReturn);
		jedisPoolConfig.setBlockWhenExhausted(blockWhenExhausted);

		return jedisPoolConfig;
	}

	@Bean
	public RedisClusterConfiguration jedisClusterConfiguration() {
		if (StringUtils.isEmpty(clusterHosts)) {
			LOGGER.info("redis集群主机未配置");
			throw new LocalException("redis集群主机未配置");
		}
		if (StringUtils.isEmpty(clusterPorts)) {
			LOGGER.info("redis集群端口未配置");
			throw new LocalException("redis集群端口未配置");
		}
		String[] hosts = clusterHosts.split(",");
		String[] portArray = clusterPorts.split(";");
		if (hosts.length != portArray.length) {
			LOGGER.info("redis集群主机数与端口数不匹配");
			throw new LocalException("redis集群主机数与端口数不匹配");
		}
		Set<RedisNode> redisNodes = new HashSet<RedisNode>();
		for (int i = 0; i < hosts.length; i++) {
			String ports = portArray[i];
			String[] hostPorts = ports.split(",");
			for (String port : hostPorts) {
				RedisNode node = new RedisNode(hosts[i], Integer.parseInt(port));
				redisNodes.add(node);
			}
		}
		LOGGER.info("Set<RedisNode> : {}", JSON.toJSONString(redisNodes), true);

		RedisClusterConfiguration redisClusterConfiguration = new RedisClusterConfiguration();
		redisClusterConfiguration.setMaxRedirects(maxRedirects);
		redisClusterConfiguration.setClusterNodes(redisNodes);

		return redisClusterConfiguration;
	}

	// redis集群
	/*
	 * @Bean public JedisCluster jedisCluster(JedisPoolConfig jedisPoolConfig) {
	 * if (StringUtils.isEmpty(clusterHosts)) { LOGGER.info("redis集群主机未配置");
	 * return null; } if (StringUtils.isEmpty(clusterPorts)) {
	 * LOGGER.info("redis集群端口为配置"); return null; } String[] hosts =
	 * clusterHosts.split(","); String[] portArray = clusterPorts.split(";"); if
	 * (hosts.length != portArray.length) { LOGGER.info("redis集群主机数与端口数不匹配");
	 * return null; } Set<HostAndPort> jedisClusterNode = new
	 * HashSet<HostAndPort>(); for (int i=0; i<hosts.length; i++) { String ports
	 * = portArray[i]; String[] hostPorts = ports.split(","); for (String port :
	 * hostPorts) { HostAndPort hp = new HostAndPort(hosts[i],
	 * Integer.parseInt(port)); jedisClusterNode.add(hp); } }
	 * LOGGER.info("Set<HostAndPort> : {}", JSON.toJSONString(jedisClusterNode),
	 * true);
	 * 
	 * JedisCluster jedisCluster = new JedisCluster(jedisClusterNode, timeout,
	 * socketTimeout, maxAttempts, password, jedisPoolConfig); return
	 * jedisCluster; }
	 */
}
