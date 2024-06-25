package com.chhimek.supportmgmt.service;

import java.util.List;

import com.chhimek.supportmgmt.model.SupportRegPerson;

public interface RegPersonDao {
	
	void saveRegPerson(SupportRegPerson person);
	SupportRegPerson getPersonById(int id);
	void updateRegPerson(SupportRegPerson person);
	List<SupportRegPerson>  listAll();
	List<SupportRegPerson> listByRegionId(int id);
	void deleteRegPerson(int id);
	String getRandomFileName(int size);
	void overridePastHoliday();
	void overridePastLeave(SupportRegPerson person);
	void updatePresentStatus(SupportRegPerson person);
}
