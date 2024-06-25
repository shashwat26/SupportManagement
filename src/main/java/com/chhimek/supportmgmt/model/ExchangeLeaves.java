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
@Table(name = "exchangeleaves")
public class ExchangeLeaves {
	
	@Id
	@GeneratedValue
	private int id;
	
	private Date exchangeLeaveDate;
	
	private String description;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "supportperson")
	private SupportPersonnel personnel;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getExchangeLeaveDate() {
		return exchangeLeaveDate;
	}

	public void setExchangeLeaveDate(Date exchangeLeaveDate) {
		this.exchangeLeaveDate = exchangeLeaveDate;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public SupportPersonnel getPersonnel() {
		return personnel;
	}

	public void setPersonnel(SupportPersonnel personnel) {
		this.personnel = personnel;
	}
	
	

}
