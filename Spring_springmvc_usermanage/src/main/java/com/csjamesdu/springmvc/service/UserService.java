package com.csjamesdu.springmvc.service;

import java.util.List;

import com.csjamesdu.springmvc.model.User;

public interface UserService {
	
	List<User> listUser();
	User loadUserById(int id);
	void updateUser(User user);
	void deleteUser(User user);
	void addUser(User user);
	void autoInsert();
	void autoInsertRollBack();
	
}
