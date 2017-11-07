package com.csjamesdu.springmvc.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.csjamesdu.springmvc.dao.ProductDao;
import com.csjamesdu.springmvc.model.Product;

@Repository("productDao")
public class ProductDaoImpl implements ProductDao {
	private static final Logger logger = LoggerFactory.getLogger(ProductDaoImpl.class);
	private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sf){
		this.sessionFactory = sf; 
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Product> list() {
		Session session = this.sessionFactory.getCurrentSession();
		Query q = session.createQuery("from Product");
		List<Product> products = q.list();
		return products;
	}

	@Override
	public Product getById(int id) {
		Session session = this.sessionFactory.getCurrentSession();
		Product product = (Product)session.get(Product.class, id);
		logger.info("@@@Product :" + product.getName() + " is GOT@@@");
		return product;
	}

	@Override
	public void add(Product product) {
		Session session = this.sessionFactory.getCurrentSession();
		session.save(product);
		logger.info("@@@Product :" + product.getName() + " is SAVED@@@");
	}

	@Override
	public void delete(Product product) {
		Session session = this.sessionFactory.getCurrentSession();
		session.delete(product);
		logger.info("@@@Product :" + product.getName() + " is DELETED@@@");

	}
	
	@Override
	public void deleteWithNewSession(Product product) {
		Session session = this.sessionFactory.openSession();
		session.beginTransaction();
		session.delete(product);
		logger.info("@@@Product :" + product.getName() + " is DELETED@@@");
		session.getTransaction().commit();
		session.close();		
	}
	
	public void deleteById(int id) {
		Session session = this.sessionFactory.getCurrentSession();
		Product product = (Product)session.get(Product.class, id);
		if(null != product){
			session.delete(product);
			logger.info("@@@Product :" + product.getName() + " is DELETED@@@");
		}
	}

	@Override
	public void update(Product product) {
		Session session = this.sessionFactory.getCurrentSession();
		session.update(product);
		logger.info("@@@Product :" + product.getName() + " is UPDATED@@@");
	}

}
