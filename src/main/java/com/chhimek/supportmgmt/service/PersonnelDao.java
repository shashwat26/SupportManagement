package com.chhimek.supportmgmt.service;

import java.util.List;

import com.chhimek.supportmgmt.model.Holiday;
import com.chhimek.supportmgmt.model.LeaveDays;
import com.chhimek.supportmgmt.model.SupportCategory;
import com.chhimek.supportmgmt.model.SupportPersonnel;

public interface PersonnelDao {
	
	void savePerson(SupportPersonnel personnel);
	SupportPersonnel getPersonById(int id);
	void updatePerson(SupportPersonnel personnel);
	List<SupportPersonnel> listAll();
	void deletePerson(int id);
	List<SupportPersonnel> listByCategoryId(int id);
	void updatePresentStatus(SupportPersonnel personnel);
	void overridePastHoliday();
	void overridePastLeave(SupportPersonnel personnel);
	String getRandomFileName(int size);
	List<SupportPersonnel> listByCategory(SupportCategory category);
	List<SupportPersonnel> listByDepartmentId(int id);

}
