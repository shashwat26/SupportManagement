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
@Table(name = "supportregperson")
public class SupportRegPerson {

	
	@Id
	@GeneratedValue
	private int id;
	
	private String fname;
	
	private String lname;
	
	private String mobileNumber;
	
	private boolean isPresent;
	
	private int dayStartTime;
	
	private int dayEndTime;
	
	private String photoName;
	
	private String email;
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "supportregion")
	private SupportRegion region;
	
	@OneToMany(mappedBy = "regperson", fetch = FetchType.EAGER)
	@Fetch(value = FetchMode.SELECT)
	private List<LeaveDays> leaveList;

	public List<LeaveDays> getLeaveList() {
		return leaveList;
	}

	public void setLeaveList(List<LeaveDays> leaveList) {
		this.leaveList = leaveList;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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
		return isPresent;
	}

	public void setPresent(boolean isPresent) {
		this.isPresent = isPresent;
	}

	public SupportRegion getRegion() {
		return region;
	}

	public void setRegion(SupportRegion region) {
		this.region = region;
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
	
	
	
}
