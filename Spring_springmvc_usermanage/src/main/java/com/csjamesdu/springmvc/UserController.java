package com.csjamesdu.springmvc;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


import com.csjamesdu.springmvc.model.User;
import com.csjamesdu.springmvc.service.UserService;



@RestController
public class UserController {
	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	
	private UserService userService;

	@Autowired(required=true)
	@Qualifier(value="userService")
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	//--------------------------RESTful Handlers---------------------------
	
	@RequestMapping(value="/user", method=RequestMethod.GET)
	public ResponseEntity<List<User>> listAllUsers() {
		List<User> users = userService.listUser();
		if(users.isEmpty()){
			return new ResponseEntity<List<User>>(HttpStatus.NO_CONTENT);			
		}
		return new ResponseEntity<List<User>>(users, HttpStatus.OK);
	}
	
	@RequestMapping(value="/user/{id}", method=RequestMethod.GET, produces= MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<User> getUser(@PathVariable("id") int id) {
		System.out.println("Fetching user with id" + id);
		User user = userService.loadUserById(id);
		if(user==null) {
			System.out.println("User with id" + id + "not found!");
			return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}
	
	@RequestMapping(value="/user/", method=RequestMethod.POST)
	public ResponseEntity<Void> addUser(@RequestBody User user){
		int id = user.getId();
		if(userService.loadUserById(id)!=null){
			return new ResponseEntity<Void>(HttpStatus.CONFLICT);
		}
		userService.addUser(user);
		//HttpHeaders headers = new HttpHeaders();
		//headers.setLocation(ucBuilder.path("/user/{id}").buildAndExpand(user.getId()).toUri());
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	@RequestMapping(value="/user/{id}", method=RequestMethod.PUT)
	public ResponseEntity<User> updateUser(@PathVariable("id")int id, @RequestBody User user) {
		System.out.println("Updating user" + id);
		User current_user = userService.loadUserById(id);
		
		if(current_user==null) {
			System.out.println("User with id: "+ id +" not found");
			return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
		}
		
		current_user.setName(user.getName());
		current_user.setPassword(user.getPassword());
		
		userService.updateUser(current_user);
		return new ResponseEntity<User>(current_user, HttpStatus.OK);
	}
	
	@RequestMapping(value="/user/{id}", method = RequestMethod.DELETE) 
	public ResponseEntity<User> deleteUserREST(@PathVariable("id") int id) {
		System.out.println("Fetching and Deleting User With ID: " + id);
		
		User user = userService.loadUserById(id);
		if(null==user){
			System.out.println("Unable to delete User with ID: "+id+"not found!");
			return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
		}
		
		userService.deleteUser(user);
		return new ResponseEntity<User>(HttpStatus.NO_CONTENT);
	}
	
	@RequestMapping(value="/user/", method=RequestMethod.DELETE)
	public ResponseEntity<User> deleteAllUsers() {
		List<User> users = userService.listUser();
		for(User u : users) {
			userService.deleteUser(u);
		}
		return new ResponseEntity<User>(HttpStatus.NO_CONTENT);
	}
	
	
	
	//--------------------------Non RESTful Handlers-----------------------
	
	
	/*@RequestMapping(value = "/users", method = RequestMethod.GET)
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
	
	*/
	
}
