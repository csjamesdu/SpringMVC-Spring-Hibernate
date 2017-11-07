package com.csjamesdu.springmvc.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.csjamesdu.springmvc.dao.ProductDao;
import com.csjamesdu.springmvc.model.Product;

@Repository("productDao")
public class ProductDaoImpl implements ProductDao {
	
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
	public Product loadById(int id) {
		Session session = this.sessionFactory.getCurrentSession();
		Product product = (Product)session.get(Product.class, id);
		return product;
	}

	@Override
	public void add(Product product) {
		Session session = this.sessionFactory.getCurrentSession();
		session.save(product);
	}

	@Override
	public void delete(Product product) {
		Session session = this.sessionFactory.getCurrentSession();
		session.delete(product);

	}
	
	public void deleteById(int id) {
		Session session = this.sessionFactory.getCurrentSession();
		Product product = (Product)session.get(Product.class, id);
		if(null != product){
			session.delete(product);
		}
	}

	@Override
	public void update(Product product) {
		Session session = this.sessionFactory.getCurrentSession();
		session.update(product);
	}

}
