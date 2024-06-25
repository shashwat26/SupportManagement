package com.chhimek.supportmgmt.service;

import java.util.List;

import com.chhimek.supportmgmt.model.SupportCategory;

public interface CategoryDao {
	
	void saveCategory(SupportCategory category);
	List<SupportCategory> listAll();
	SupportCategory getCategoryFromId(int id);
	void updateCategory(SupportCategory category);
	void deleteCategory(int id);
	List<SupportCategory> listAllExceptNone(int id);

}
