package com.yzb.lee.springsession;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableAutoConfiguration
//@ComponentScan(basePackages = { "com.yzb.lee.springsession.web", "com.yzb.lee.springsession.init"})
@ComponentScan
// @Configuration用于定义配置类，可替换xml配置文件，被注解的类内部包含有一个或多个被@Bean注解的方法，这些方法将会被AnnotationConfigApplicationContext或AnnotationConfigWebApplicationContext类进行扫描，并用于构建bean定义，初始化Spring容器 
// @EnableAutoConfiguration:基于已经添加jar依赖项,SpringBoot“猜”你将如何想配置Spring。例如spring-boot-starter-web已经添加Tomcat和Spring MVC,这个注释自动将假设您正在开发一个web应用程序并添加相应的spring设置
// @ComponentScan:告诉Spring 哪个packages的用注解标识的类会被spring自动扫描并且装入bean容器。
// @Import: 支持导入配置类，也支持导入普通的java类，并将其声明成一个bean
// @ImportResource: load XML configuration files,支持导入xml配置文件
// @SpringBootApplication = @Configuration + @EnableAutoConfiguration + @ComponentScan; @SpringBootApplication(scanBasePackages={}, excludeName={})
// 使用嵌入式容器时，可以使用@ServletComponentScan启用@WebServlet，@ WebFilter和@WebListener注释类的自动注册; @ServletComponentScan在独立的容器中将不起作用，容器的内置发现机制将被使用来代替它
public class Application {

	public static void main(String[] args) {
		//SpringApplication.run(Application.class, args);
		
		
		SpringApplication app = new SpringApplication(Application.class);
	    app.setBannerMode(Banner.Mode.OFF);			// 是否打印banner
	    app.setWebEnvironment(true);				// 是否创建web环境
	    // app.setApplicationContextClass();		// 指定spring应用上下文启动类
	    app.run(args);
	}
}
