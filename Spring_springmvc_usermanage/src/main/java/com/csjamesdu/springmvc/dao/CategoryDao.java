package com.csjamesdu.springmvc.dao;

import java.util.List;

import com.csjamesdu.springmvc.model.Category;

public interface CategoryDao {

	List<Category> list();

	Category getById(int id);

	void add(Category category);

	void delete(Category category);

	void update(Category category);

	void deleteById(int id);

	void deleteLeaf(Category category);

	void deleteRoot(Category category);

	Category loadById(int id);

}
