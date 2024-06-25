package com.chhimek.supportmgmt.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "user")
public class User {
	
	@Id
	@GeneratedValue
	private int id;
	private String username;
	private String password;
	
	@Enumerated(EnumType.ORDINAL)
	private UserType type;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "personId")
	private SupportPersonnel supportPerson;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "regPersonId")
	private SupportRegPerson supportRegPerson;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public UserType getType() {
		return type;
	}
	public void setType(UserType type) {
		this.type = type;
	}
	public SupportPersonnel getSupportPerson() {
		return supportPerson;
	}
	public void setSupportPerson(SupportPersonnel supportPerson) {
		this.supportPerson = supportPerson;
	}
	public SupportRegPerson getSupportRegPerson() {
		return supportRegPerson;
	}
	public void setSupportRegPerson(SupportRegPerson supportRegPerson) {
		this.supportRegPerson = supportRegPerson;
	}
	
	
	

}
