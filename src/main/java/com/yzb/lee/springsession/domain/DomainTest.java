package com.yzb.lee.springsession.domain;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource("test/test.properties")
// @ConfigurationProperties(prefix="domain.test")
public class DomainTest {
	// test.properties中domain.test.name的值会自动注入到name属性中，domain.test.password的值会自动注入到password属性中, @Value都可以省略
	// setters 和 getters 需要在@ConfigurationProperties bean中创建! 与@Value注解相反
	@Value("${domain.test.name}")
	private String name;
	@Value("${domain.test.password}")
	private String password;

	// 此处的setter getter是用于object转json时用到, 如果只是用@Value来注入值的话是不需要setter、getter的
	public String getName() {
		System.out.println("get domain name");
		return name;
	}
	
	public void setName(String name) {
		System.out.println("set domain name");
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}
