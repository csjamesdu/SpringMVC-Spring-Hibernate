package com.csjamesdu.springmvc.dao.impl;

import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.csjamesdu.springmvc.dao.CategoryDao;
import com.csjamesdu.springmvc.model.Category;

@Repository("categoryDao")
public class CategoryDaoImpl implements CategoryDao {
	private static final Logger logger = LoggerFactory.getLogger(CategoryDaoImpl.class);
	private SessionFactory sessionFactory;
	
	@Resource(name="hibernate4AnnotatedSessionFactory")
	public void setSessionFactory(SessionFactory sf){
		this.sessionFactory = sf; 
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Category> list() {
		Session session = this.sessionFactory.getCurrentSession();
		Query q = session.createQuery("from Category");
		List<Category> categories = q.list();
		return categories;
	}

	@Override
	public Category getById(int id) {
		Session session = this.sessionFactory.getCurrentSession();		
		Category category = (Category)session.get(Category.class, id);
		logger.info("@@@Category :" + category.getName() + " is GOT@@@");
		return category;
	}
	
	@Override
	public Category loadById(int id) {
		Session session = this.sessionFactory.getCurrentSession();
		Category category = (Category)session.load(Category.class, id);
		logger.info("@@@Category :" + category.getName() + " is LOADED@@@");
		return category;
	}

	@Override
	public void add(Category category) {
		Session session = this.sessionFactory.getCurrentSession();
		//Session session = this.sessionFactory.openSession();
		//session.beginTransaction();
		logger.info("@@@Category :" + category.getName() + " is Saved@@@");
		session.save(category);
		//session.getTransaction().commit();
		//session.close();
	}

	@Override
	public void delete(Category category) {
		Session session = this.sessionFactory.getCurrentSession();
		logger.info("@@@Category :" + category.getName() + " is Deleted@@@");
		session.delete(category);
	}


	@Override
	public void update(Category category) {
		Session session = this.sessionFactory.getCurrentSession();
		//Session session = this.sessionFactory.openSession();
		//session.beginTransaction();
		logger.info("@@@Category :" + category.getName() + " is Updated@@@");
		session.update(category);
		//session.getTransaction().commit();
		//session.close();
	}

	@Override
	public void deleteById(int id) {
		Session session = this.sessionFactory.getCurrentSession();
		Category category = (Category)session.get(Category.class, id);
		if(null != category){
			logger.info("@@@Category :" + category.getName() + " is DELETED by ID@@@");
			session.delete(category);
		}
	}
	
	@Override
	public void deleteLeaf(Category category) {
		Session session = this.sessionFactory.openSession();
		session.beginTransaction();
		logger.info("@@@Category :" + category.getName() + " is DELETED@@@");
		session.delete(category);
		session.getTransaction().commit();
		session.close();
	}
	
	@Override
	public void deleteNonLeaf(Category category) {
		Session session = this.sessionFactory.openSession();
		session.beginTransaction();
		
		Set<Category> child_categories = category.getChildren();
		if(child_categories.size() != 0){
			for(Category child_category : child_categories) {
				logger.info("@@@Category :" + child_category.getName() + " is DELETED@@@");
				deleteNonLeaf(child_category);
			}
		}
		
		logger.info("@@@Category :" + category.getName() + " is DELETED@@@");
		session.delete(category);
		
		session.getTransaction().commit();
		session.close();
	}

	@SuppressWarnings("unchecked")
	@Override
	public void listCategoryTree(List<Category> categories, int parent_id) {
		Session session = this.sessionFactory.getCurrentSession();
		Query q = session.createQuery("from Category where parent.id =" + parent_id);
		List<Category> categories_list = q.list();
		for(Category c : categories_list) {
			categories.add(c);
			if (c.getIsleaf()== 0){
				listCategoryTree(categories, c.getId());
			}
		}
		
	}

}
