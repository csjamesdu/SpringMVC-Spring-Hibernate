package com.csjamesdu.springmvc.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.csjamesdu.springmvc.dao.UserDao;
import com.csjamesdu.springmvc.model.User;

@Repository("userDao")
public class UserDaoImpl extends HibernateTemplate implements UserDao {

	private static final Logger logger = LoggerFactory.getLogger(UserDaoImpl.class);

	@Resource(name="hibernate4AnnotatedSessionFactory")
	public void setSessionFactory(SessionFactory sessionFactory){
		super.setSessionFactory(sessionFactory);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<User> list() {
		List<User> userList = (List<User>)super.find("from User");
		for(User user: userList){
			logger.info("UserList::" + user);
		}
		return userList;
	}

	@Override
	public User loadById(int id) {
		logger.info("User number: "+ id + " loaded successfully");
		return super.get(User.class, id);
	}

	@Override
	public void update(User user) {
		logger.info("User "+user+" has been updated");
		super.update(user);
	}

	@Override
	public void delete(User user) {
		logger.info("User "+user+" has been deleted");
		super.delete(user);

	}

	@Override
	public void add(User user) {
		logger.info("ADD User: " + user.getName() + " Successfully!");
		super.save(user);
	}

	
}
