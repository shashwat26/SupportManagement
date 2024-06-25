package com.chhimek.supportmgmt.service;

import java.util.List;

import com.chhimek.supportmgmt.model.Department;

public interface DepartmentDao {
	
	void saveDepartment(Department department);
	void deleteDepartment(int id);
	void updateDepartment(Department department);
	List<Department> listAll();
	Department getDepartmentById(int id);
}
