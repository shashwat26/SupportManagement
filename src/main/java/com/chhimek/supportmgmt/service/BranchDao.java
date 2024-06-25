package com.chhimek.supportmgmt.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.chhimek.supportmgmt.model.Branch;

public interface BranchDao {
	
	void saveBranch(Branch branch);
	List<Branch> listAll();
	void deleteBranch(int id);
	Branch getBranchById(int id);
	void updateBranch(Branch branch);
	List<Branch> listBranchByRegionId(int id);
	void updateBranchByCsv(MultipartFile file);
	List<String> getColumnData(String row);
}
