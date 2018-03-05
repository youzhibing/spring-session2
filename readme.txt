redis cluster在设计的时候，就考虑到了去中心化，去中间件，也就是说，集群中的每个节点都是平等的关系，都是对等的，每个节点都保存各自的数据和整个集群的状态。
	每个节点都和其他所有节点连接，而且这些连接保持活跃，这样就保证了我们只需要连接集群中的任意一个节点，就可以获取到其他节点的数据.
	Redis 集群会把数据存在一个 master 节点，然后在这个 master 和其对应的salve 之间进行数据同步。当读取数据时，也根据一致性哈希算法到对应的 master节点获取数据。
	只有当一个master挂掉之后，才会启动一个对应的 salve节点，充当 master

HttpSession创建时机: server端程序调用 HttpServletRequest.getSession(true)这样的语句时才被创建

spring-boot：用来简化Spring应用的搭建到开发的过程，减少Spring的配置文件，推崇习惯优先于配置”的原则
spring-boot会基于已经添加jar依赖项，“猜”开发者想如何配置spring，从而加载相应的配置(spring-boot有自己的默认配置)和启动相应的容器

spring-boot中使用spring-session
	最简单配置：1、pom.xml中配置spring-session-data-redis依赖；2、application.properties中配置spring.session.store-type=redis。
			以上配置默认连接的是本地的redis（localhost:6379），如需自定义连接，则需在application.properties中配置redis连接属性。
	pom.xml中有spring-session依赖，application.properties中必须配置spring.session.store-type属性来告诉spring以何种方式来存储session; 
	spring.session.store-type=none则是告诉spring以默认方式存储session(session存放在servlet容器中)

@EnableRedisHttpSession与@EnableCaching没关系 前者开启Session缓存，后者开启普通缓存； 不是说配置了@EnableCaching，@EnableRedisHttpSession才起作用

redis集群启动：若集群已经已经在之前搭建好了，那么只需要启动各个节点即可，步骤2可以可以省略
	1、节点启动
		202节点
		[root@slave1 redis-3.2.10]# ./src/redis-server redis_cluster/7000/redis.conf 
		[root@slave1 redis-3.2.10]# ./src/redis-server redis_cluster/7001/redis.conf 
		[root@slave1 redis-3.2.10]# ./src/redis-server redis_cluster/7002/redis.conf
		222节点
		[root@master redis-3.2.10]# ./src/redis-server redis_cluster/7003/redis.conf 
		[root@master redis-3.2.10]# ./src/redis-server redis_cluster/7004/redis.conf 
		[root@master redis-3.2.10]# ./src/redis-server redis_cluster/7005/redis.conf 
	2、创建集群
		[root@master redis-3.2.10]# ./src/redis-trib.rb  create  --replicas  1  192.168.11.202:7000 192.168.11.202:7001  192.168.11.202:7002 192.168.11.222:7003  192.168.11.222:7004  192.168.11.222:7005
连接redis集群
	连接某个节点即可，h:节点ip, c:表示连接到集群, p:端口, a:集群密码
	[root@slave1 redis-3.2.10]# ./src/redis-cli -h 192.168.11.222 -c -p 7004 -a myredis

参考博客：
	http://blog.csdn.net/javaloveiphone/article/details/53187314

spring rest异步请求
	SpringRestTemplateController，对应推送地址
	SpringRestTemplateTest，对应推送者
	
