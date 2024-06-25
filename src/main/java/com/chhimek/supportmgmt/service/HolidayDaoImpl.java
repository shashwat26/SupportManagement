package com.chhimek.supportmgmt.service;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.chhimek.supportmgmt.model.Holiday;
import com.chhimek.supportmgmt.model.SupportPersonnel;

@Repository
public class HolidayDaoImpl implements HolidayDao{
	
	
	@Resource
	private SessionFactory sessionFactory;

	@Override
	@Transactional
	public void save(Holiday holiday) {
		Session sess = sessionFactory.getCurrentSession();
		sess.save(holiday);
		
	}

	@Override
	@Transactional
	public List<Holiday> listAll() {
		Session sess = sessionFactory.getCurrentSession();
		Criteria crt = sess.createCriteria(Holiday.class);
		crt.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		return crt.list();
	}

	@Override
	@Transactional
	public void updateHolidayStatus(SupportPersonnel personnel) {
	}

	@Override
	@Transactional
	public void updateHoliday(Holiday holiday) {
		Session sess = sessionFactory.getCurrentSession();
		sess.update(holiday);
		
	}

	@Override
	@Transactional
	public List<Holiday> listPresentandFutureHoliday() {
		Session sess = sessionFactory.getCurrentSession();
		String hql = "FROM Holiday H WHERE H.isPast = 0";
		Query q = sess.createQuery(hql);
		return q.list();
	}
		
	@Override
	@Transactional
	public void deleteHoliday(int id) {
		Session sess = sessionFactory.getCurrentSession();
		Holiday holiday = (Holiday) sess.get(Holiday.class, id);
		sess.delete(holiday);
	}
	

}
