package com.chhimek.supportmgmt.model;



import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name = "supportcategory")
public class SupportCategory {
	
	@Id
	@GeneratedValue
	private int id;
	
	private String categoryName;
	
	private String categoryDescription;
	
	@OneToMany(mappedBy = "supportcategory", fetch = FetchType.EAGER)
	private List<SupportPersonnel> personList;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getCategoryDescription() {
		return categoryDescription;
	}

	public void setCategoryDescription(String categoryDescription) {
		this.categoryDescription = categoryDescription;
	}

	
	
	

}
