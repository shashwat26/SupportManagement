package com.chhimek.supportmgmt.service;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.chhimek.supportmgmt.model.ExchangeLeaves;
import com.chhimek.supportmgmt.model.SupportPersonnel;

@Repository
public class ExchangeLeaveDaoImpl implements ExchangeLeaveDao{

	@Resource
	private SessionFactory sessionFactory;
	
	@Override
	@Transactional
	public void save(ExchangeLeaves exLeave) {
		Session sess = sessionFactory.getCurrentSession();
		sess.save(exLeave);
		
	}

	@Override
	@Transactional
	public List<ExchangeLeaves> listAllExchangeLeaves() {
		Session sess = sessionFactory.getCurrentSession();
		Criteria crt = sess.createCriteria(ExchangeLeaves.class);
		crt.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		return crt.list();
	}

	@Override
	@Transactional
	public void delete(int id) {
		Session sess = sessionFactory.getCurrentSession();
		ExchangeLeaves leave = (ExchangeLeaves) sess.get(ExchangeLeaves.class, id);
		sess.delete(leave);
	}

	@Override
	@Transactional
	public List<ExchangeLeaves> listExchangeLeavesByPerson(SupportPersonnel personnel) {
		Session sess = sessionFactory.getCurrentSession();
		Criteria crt = sess.createCriteria(ExchangeLeaves.class);
		crt.add(Restrictions.eq("personnel.personnelId", personnel.getPersonnelId()));
		return crt.list();
	}

}
