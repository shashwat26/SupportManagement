package com.chhimek.supportmgmt.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "supportregion")
public class SupportRegion {

	@Id
	@GeneratedValue
	private int id;
	
	private String region;
	
	@OneToMany(mappedBy = "region", fetch = FetchType.EAGER)
	private List<SupportRegPerson> personList;
	
	/*
	 * @OneToMany(mappedBy = "branchRegion", fetch = FetchType.EAGER) private
	 * List<Branch> branchList;
	 */
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public List<SupportRegPerson> getPersonList() {
		return personList;
	}

	public void setPersonList(List<SupportRegPerson> personList) {
		this.personList = personList;
	}

	/*
	 * public List<Branch> getBranchList() { return branchList; }
	 * 
	 * public void setBranchList(List<Branch> branchList) { this.branchList =
	 * branchList; }
	 */
}
