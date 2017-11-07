package com.csjamesdu.springmvc.service.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;


import com.csjamesdu.springmvc.util.UnitTestBase;
import com.csjamesdu.springmvc.model.User;
import com.csjamesdu.springmvc.service.UserService;
@RunWith(BlockJUnit4ClassRunner.class)
public class TestUserService extends UnitTestBase{
	public TestUserService() {
		super("servlet-context.xml");
	}
	
	@Test
	
	public void testUserSave(){
		UserService service = super.getBean("userService");
		User u = new User();
		u.setName("test1");
		u.setPassword("123");
		service.addUser(u);
		
	}
}
