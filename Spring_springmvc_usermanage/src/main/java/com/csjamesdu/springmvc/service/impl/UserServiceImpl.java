package com.csjamesdu.springmvc.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.csjamesdu.springmvc.dao.UserDao;
import com.csjamesdu.springmvc.model.User;
import com.csjamesdu.springmvc.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	private static final int NUM_AUTO_INSERT = 5;
	
	
	UserDao userDao;
	
	
	@Override
	@Transactional
	public List<User> listUser() {
		return userDao.list();
	}

	@Override
	@Transactional
	public User loadUserById(int id) {
		return userDao.loadById(id);
	}

	@Override
	@Transactional
	public void updateUser(User user) {
		userDao.update(user);
	}

	@Override
	@Transactional
	public void deleteUser(User user) {
		userDao.delete(user);
	}

	@Override
	@Transactional
	public void addUser(User user) {
		userDao.add(user);

	}

	@Override
	@Transactional
	public void autoInsert() {
		for(int i = 0; i< NUM_AUTO_INSERT; i++){
			User user = new User();
			user.setName("AutoInsertedUser "+i);
			user.setPassword("DefaultPsw "+i);
			userDao.add(user);
		}
	}

	
	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackForClassName="Exception")
	public void autoInsertRollBack() {
		for(int i = 0; i< NUM_AUTO_INSERT; i++){
			if (i==3){
				throw new RuntimeException();
			}
			User user = new User();
			user.setName("AutoInsertedUser "+i);
			user.setPassword("DefaultPsw "+i);
			userDao.add(user);
		}
	}
	
	public UserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	

}
