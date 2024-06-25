package com.chhimek.supportmgmt.service;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.chhimek.supportmgmt.model.Department;

@Repository
public class DepartmentDaoImpl implements DepartmentDao {
	
	@Resource
	private SessionFactory sessionFactory;

	@Override
	@Transactional
	public void saveDepartment(Department department) {
		Session sess = sessionFactory.getCurrentSession();
		sess.save(department);
		
	}

	@Override
	@Transactional
	public List<Department> listAll() {
		Session sess = sessionFactory.getCurrentSession();
		Criteria crt = sess.createCriteria(Department.class);
		crt.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		return crt.list();
	}

	@Override
	@Transactional
	public void deleteDepartment(int id) {
		Session sess = sessionFactory.getCurrentSession();
		Department department = (Department) sess.get(Department.class, id);
		sess.delete(department);
		
	}

	@Override
	@Transactional
	public void updateDepartment(Department department) {
		Session sess = sessionFactory.getCurrentSession();
		sess.update(department);
		
	}

	@Override
	@Transactional
	public Department getDepartmentById(int id) {
		Session sess = sessionFactory.getCurrentSession();
		Department department = (Department) sess.get(Department.class, id);
		return department;
	}
	
	
	
	

}
