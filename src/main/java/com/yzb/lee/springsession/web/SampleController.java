package com.yzb.lee.springsession.web;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.yzb.lee.springsession.domain.DomainTest;
import com.yzb.lee.springsession.service.IUserService;

@RestController
@RequestMapping("/hello")
// @EnableConfigurationProperties(DomainTest.class) // 这个注解告诉Spring Boot 使能支持@ConfigurationProperties,让@ConfigurationProperties beans被添加
// 用@Configuration或者 @Component注解也能使@ConfigurationProperties beans 被添加
public class SampleController {

	@Autowired
	private IUserService userService;
	
	@Autowired
	private DomainTest domain;
	
    @RequestMapping("/greet")
    Map<String, String> greet(HttpServletRequest req, HttpServletResponse resp) {
    	String attributeName = req.getParameter("attributeName");
		String attributeValue = req.getParameter("attributeValue");
		HttpSession session = req.getSession();
		session.setAttribute(attributeName, attributeValue);
		
		Enumeration<String> attrs = session.getAttributeNames();
		Map<String,String> sessionAttrValues = new HashMap<String, String>();
		while( attrs.hasMoreElements())   {   
		    String sessionName=(String)attrs.nextElement();
		    sessionAttrValues.put(sessionName, session.getAttribute(sessionName).toString());
		      
		}  
		
        return sessionAttrValues;
    }
    
    @RequestMapping(value={"/name"}, method=RequestMethod.POST)
    String getName(@RequestParam(required=true) int id) {
    	return userService.getName(id);
    }
    
    @RequestMapping(value={"/domain"})
    DomainTest getDomain() {
    	return domain;
    }
}