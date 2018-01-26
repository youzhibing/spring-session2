package com.yzb.lee.springsession.web;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yzb.lee.springsession.service.IUserService;

@RestController
@RequestMapping("/hello")
public class SampleController {

	@Autowired
	private IUserService userService;
	
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
    
    @RequestMapping("/name")
    String getName() {
    	return userService.getName();
    }
}