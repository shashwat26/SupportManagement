package com.chhimek.supportmgmt.model;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "holiday")
public class Holiday {
	
	@Id
	@GeneratedValue
	private int id;
	
	private Date holiday;
	
	private String description;
	
	private boolean isPast;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getHoliday() {
		return holiday;
	}

	public void setHoliday(Date holiday) {
		this.holiday = holiday;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isPast() {
		return isPast;
	}

	public void setPast(boolean isPast) {
		this.isPast = isPast;
	}
	
	
	

}
