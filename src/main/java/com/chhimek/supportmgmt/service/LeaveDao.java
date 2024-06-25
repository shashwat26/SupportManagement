package com.chhimek.supportmgmt.service;

import java.util.List;

import com.chhimek.supportmgmt.model.LeaveDays;
import com.chhimek.supportmgmt.model.SupportPersonnel;
import com.chhimek.supportmgmt.model.SupportRegPerson;

public interface LeaveDao {
	
	void saveLeaveDay(LeaveDays leaveDay);
	List<LeaveDays> listLeaveByPerson(SupportPersonnel personnel);
	void updateLeave(LeaveDays leaveDay);
	List<LeaveDays> listLeaveByPersonAndStatus(SupportPersonnel personnel);
	List<LeaveDays> listLeaveByRegPerson(SupportRegPerson personnel);
	List<LeaveDays> listLeaveByRegPersonAndStatus(SupportRegPerson personnel);
	LeaveDays getLeaveById(int id);
	void deleteLeave(int id);

}
