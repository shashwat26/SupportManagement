	package com.chhimek.supportmgmt.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
@Table(name = "supportperson")
public class SupportPersonnel {
	
	@Id
	@GeneratedValue
	private int personnelId;
	private String fname;
	private String lname;
	
	private String mobileNumber;
	private boolean present;
	
	private int dayStartTime;
	private int dayEndTime;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@Fetch(value = FetchMode.SELECT)
	@JoinColumn(name = "supportcategory")
	private SupportCategory supportcategory;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@Fetch(value = FetchMode.SELECT)
	@JoinColumn(name = "department")
	private Department department;
	
	private String photoName;
	
	private String email;
	
	@OneToMany(mappedBy = "person", fetch = FetchType.EAGER)
	@Fetch(value = FetchMode.SELECT)
	private List<LeaveDays> leaveList;
	
	@OneToMany(mappedBy = "personnel", fetch = FetchType.EAGER)
	@Fetch(value = FetchMode.SELECT)
	private List<ExchangeLeaves> exchangeLeaveList;
	
	private boolean workingFromHome;
	
	public int getPersonnelId() {
		return personnelId;
	}
	public void setPersonnelId(int personnelId) {
		this.personnelId = personnelId;
	}
	public String getFname() {
		return fname;
	}
	public void setFname(String fname) {
		this.fname = fname;
	}
	public String getLname() {
		return lname;
	}
	public void setLname(String lname) {
		this.lname = lname;
	}
	public String getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	public boolean isPresent() {
		return present;
	}
	public void setPresent(boolean present) {
		this.present = present;
	}
	
	public String getPhotoName() {
		return photoName;
	}
	public void setPhotoName(String photoName) {
		this.photoName = photoName;
	}
	public int getDayStartTime() {
		return dayStartTime;
	}
	public void setDayStartTime(int dayStartTime) {
		this.dayStartTime = dayStartTime;
	}
	public int getDayEndTime() {
		return dayEndTime;
	}
	public void setDayEndTime(int dayEndTime) {
		this.dayEndTime = dayEndTime;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public List<LeaveDays> getLeaveList() {
		return leaveList;
	}
	public void setLeaveList(List<LeaveDays> leaveList) {
		this.leaveList = leaveList;
	}
	
	public Department getDepartment() {
		return department;
	}
	public void setDepartment(Department department) {
		this.department = department;
	}
	public SupportCategory getSupportcategory() {
		return supportcategory;
	}
	public void setSupportcategory(SupportCategory supportcategory) {
		this.supportcategory = supportcategory;
	}
	public boolean isWorkingFromHome() {
		return workingFromHome;
	}
	public void setWorkingFromHome(boolean workingFromHome) {
		this.workingFromHome = workingFromHome;
	}
	public List<ExchangeLeaves> getExchangeLeaveList() {
		return exchangeLeaveList;
	}
	public void setExchangeLeaveList(List<ExchangeLeaves> exchangeLeaveList) {
		this.exchangeLeaveList = exchangeLeaveList;
	}
	
	
	
	


}
