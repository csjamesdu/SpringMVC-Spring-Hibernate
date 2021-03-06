package com.csjamesdu.springmvc.util;


import org.junit.After;
import org.junit.Before;
import org.springframework.beans.BeansException;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.util.StringUtils;

public class UnitTestBase {
	private ClassPathXmlApplicationContext context;
	private String springXmlPath;
	public UnitTestBase(){};
	
	public UnitTestBase(String springXmlPath) {
		this.springXmlPath = springXmlPath;
	}
	
	@Before
	public void before() {
		if(StringUtils.isEmpty(springXmlPath)) {
			springXmlPath = "classpath:WEB-INF/spring/appServlet/servlet-context.xml";
		}
		try {
			context = new ClassPathXmlApplicationContext(springXmlPath);
			context.start();
		} catch(BeansException e) {
			e.printStackTrace();
		}
	}
	
	@After
	public void after() {
		context.destroy();
	}
	
	@SuppressWarnings("unchecked")  
    protected <T extends Object> T getBean(String beanId) {  
        try {  
            return (T)context.getBean(beanId);  
        } catch (BeansException e) {  
            e.printStackTrace();  
            return null;  
        }  
    }  
      
    protected <T extends Object> T getBean(Class<T> clazz) {  
        try {  
            return context.getBean(clazz);  
        } catch (BeansException e) {  
            e.printStackTrace();  
            return null;  
        }  
    }  
}
