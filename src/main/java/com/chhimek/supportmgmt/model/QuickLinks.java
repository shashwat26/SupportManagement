package com.chhimek.supportmgmt.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "quicklinks")
public class QuickLinks {
	
	
	@Id
	@GeneratedValue
	private int id;
	private String linkDescription;
	private String linkUrl;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getLinkDescription() {
		return linkDescription;
	}
	public void setLinkDescription(String linkDescription) {
		this.linkDescription = linkDescription;
	}
	public String getLinkUrl() {
		return linkUrl;
	}
	public void setLinkUrl(String linkUrl) {
		this.linkUrl = linkUrl;
	}
	
	

}
