package com.csjamesdu.springmvc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.csjamesdu.springmvc.model.User;
import com.csjamesdu.springmvc.service.UserService;



@Controller
public class UserController {
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	
	private UserService userService;

	@Autowired(required=true)
	@Qualifier(value="userService")
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
	@RequestMapping(value = "/users", method = RequestMethod.GET)
	public String listUsers(Model model){
		model.addAttribute("user", new User());
		model.addAttribute("listUsers", this.userService.listUser());
		return "user";
	}
	
	@RequestMapping(value = "/user/add", method = RequestMethod.POST)
	public String addUser(@ModelAttribute("user")User user){
		if(user.getId() == 0) {
			userService.addUser(user);
		}else {
			userService.updateUser(user);
		}
		
		return "redirect:/users";
	}
	
	@RequestMapping("/delete/{id}")
	public String deleteUser(@PathVariable("id") int id) {
		User user = userService.loadUserById(id);
		userService.deleteUser(user);
		return "redirect:/users";
	}
	
	@RequestMapping("/edit/{id}")
	public String editUser(@PathVariable("id") int id, Model model) {
		model.addAttribute("user", userService.loadUserById(id));
		model.addAttribute("listUsers", userService.listUser());
		return "user";
	}
	
	@RequestMapping("/autoinsert")
	public String autoinsertUser() {
		userService.autoInsert();
		return "redirect:/users";
	}
	
	@RequestMapping("/autoinsertrb")
	public String autoinsertrbUser() {
		try{
			userService.autoInsertRollBack();
		}catch(RuntimeException e){
			logger.error("Exception: "+e.getClass()+" was detected, auto insert has been rolled back!");
		}
		return "redirect:/users";
	}
	
}
