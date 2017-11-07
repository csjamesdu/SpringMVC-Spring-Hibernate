package com.csjamesdu.springmvc.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
public class Category {
	@Id
	@GeneratedValue
	private int id;
	private String name;
	private int grade;
	private int isleaf;
	
	
	@OneToMany(mappedBy="parent",fetch=FetchType.EAGER)
	private Set<Category> children = new HashSet<>();
	@ManyToOne
	private Category parent;
	@OneToMany(mappedBy="category")
	private Set<Product> products = new HashSet<>();
	
	public Set<Product> getProducts() {
		return products;
	}
	public void setProducts(Set<Product> products) {
		this.products = products;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getGrade() {
		return grade;
	}
	public void setGrade(int grade) {
		this.grade = grade;
	}
	public int getIsleaf() {
		return isleaf;
	}
	public void setIsleaf(int isleaf) {
		this.isleaf = isleaf;
	}
	public Set<Category> getChildren() {
		return children;
	}
	public void setChildren(Set<Category> children) {
		this.children = children;
	}
	public Category getParent() {
		return parent;
	}
	public void setParent(Category parent) {
		this.parent = parent;
	}
}
