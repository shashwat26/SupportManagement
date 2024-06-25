package com.chhimek.supportmgmt.service;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.chhimek.supportmgmt.model.SupportCategory;

@Repository
public class CategoryDaoImpl implements CategoryDao{
	
	@Resource
	private SessionFactory sessionFactory;

	@Override
	@Transactional
	public void saveCategory(SupportCategory category) {
		Session sess = sessionFactory.getCurrentSession();
		sess.save(category);
	}

	
	@Override
	@Transactional
	public List<SupportCategory> listAll() {
		Session sess = sessionFactory.getCurrentSession();
		Criteria crt = sess.createCriteria(SupportCategory.class);
		crt.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		return crt.list();
	}

	@Override
	@Transactional
	public SupportCategory getCategoryFromId(int id) {
		Session sess = sessionFactory.getCurrentSession();
		//Criteria crt = sess.createCriteria(SupportCategory.class);
		SupportCategory category = (SupportCategory) sess.get(SupportCategory.class, id);
		return category;
	}


	@Override
	@Transactional
	public void updateCategory(SupportCategory category) {
		Session sess = sessionFactory.getCurrentSession();
		sess.update(category);
		
	}


	@Override
	@Transactional
	public void deleteCategory(int id) {
		Session sess = sessionFactory.getCurrentSession();
		SupportCategory category = (SupportCategory) sess.get(SupportCategory.class, id);
		sess.delete(category);
	}


	@Override
	@Transactional
	public List<SupportCategory> listAllExceptNone(int id) {
		Session sess = sessionFactory.getCurrentSession();
		Criteria crt = sess.createCriteria(SupportCategory.class);
		crt.add(Restrictions.ne("id", id));
		crt.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		return crt.list();
	}

}
