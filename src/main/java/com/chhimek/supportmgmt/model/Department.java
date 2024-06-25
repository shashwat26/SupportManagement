package com.chhimek.supportmgmt.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "department")
public class Department {

	@Id
	@GeneratedValue
	private int id;

	private String name;

	private String description;

	@OneToMany(mappedBy = "department", fetch = FetchType.EAGER)
	private List<SupportPersonnel> personList;


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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<SupportPersonnel> getPersonList() {
		return personList;
	}

	public void setPersonList(List<SupportPersonnel> personList) {
		this.personList = personList;
	}

	

}
