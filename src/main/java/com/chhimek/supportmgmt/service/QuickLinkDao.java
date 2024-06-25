package com.chhimek.supportmgmt.service;

import java.util.List;

import com.chhimek.supportmgmt.model.QuickLinks;

public interface QuickLinkDao {
	
	void savelink(QuickLinks link);
	List<QuickLinks> listQuickLinks();
	void deleteLink(int id);

}
