package com.chhimek.supportmgmt.model;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "leavedays")
public class LeaveDays {
	
	public SupportRegPerson getRegperson() {
		return regperson;
	}

	public void setRegperson(SupportRegPerson regperson) {
		this.regperson = regperson;
	}

	@Id
	@GeneratedValue
	private int id;
	
	private Date leaveDay;
	
	private String leavediscription;
	
	private boolean isPast;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "supportperson")
	private SupportPersonnel person;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "supportregperson")
	private SupportRegPerson regperson;
 
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLeavediscription() {
		return leavediscription;
	}

	public void setLeavediscription(String leavediscription) {
		this.leavediscription = leavediscription;
	}

	public SupportPersonnel getPerson() {
		return person;
	}

	public void setPerson(SupportPersonnel person) {
		this.person = person;
	}

	public Date getLeaveDay() {
		return leaveDay;
	}

	public void setLeaveDay(Date leaveDay) {
		this.leaveDay = leaveDay;
	}

	public boolean isPast() {
		return isPast;
	}

	public void setPast(boolean isPast) {
		this.isPast = isPast;
	}
	

}
