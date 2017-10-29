package com.csjamesdu.springmvc.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.csjamesdu.springmvc.dao.ProductDao;
import com.csjamesdu.springmvc.model.Product;

@Repository("productDao")
public class ProductDaoImpl extends HibernateTemplate implements ProductDao {
	
	@Resource(name="hibernate4AnnotatedSessionFactory")
	public void setSessionFactory(SessionFactory sessionFactory){
		super.setSessionFactory(sessionFactory);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Product> list() {
		return (List<Product>)super.find("from Product");
	}

	@Override
	public Product loadById(int id) {
		return (Product)super.get(Product.class, id);
	}

	@Override
	public void add(Product product) {
		super.save(product);

	}

	@Override
	public void delete(Product product) {
		super.delete(product);

	}

	@Override
	public void update(Product product) {
		super.update(product);
	}

}
