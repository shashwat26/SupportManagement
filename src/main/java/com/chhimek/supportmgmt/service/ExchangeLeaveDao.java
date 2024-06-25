package com.chhimek.supportmgmt.service;

import java.util.List;

import com.chhimek.supportmgmt.model.ExchangeLeaves;
import com.chhimek.supportmgmt.model.SupportPersonnel;

public interface ExchangeLeaveDao {
	
	void save(ExchangeLeaves exLeave);
	List<ExchangeLeaves> listAllExchangeLeaves();
	void delete(int id);
	List<ExchangeLeaves> listExchangeLeavesByPerson(SupportPersonnel personnel);
}
