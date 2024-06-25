package com.chhimek.supportmgmt.service;

import java.util.List;

import com.chhimek.supportmgmt.model.Holiday;
import com.chhimek.supportmgmt.model.SupportPersonnel;

public interface HolidayDao {
	
	void save(Holiday holiday);
	List<Holiday> listAll();
	void updateHolidayStatus(SupportPersonnel personnel);
	void updateHoliday(Holiday holiday);
	List<Holiday> listPresentandFutureHoliday();
	void deleteHoliday(int id);
	

}
