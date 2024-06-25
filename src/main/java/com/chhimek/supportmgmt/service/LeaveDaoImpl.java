package com.chhimek.supportmgmt.service;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.chhimek.supportmgmt.model.LeaveDays;
import com.chhimek.supportmgmt.model.SupportPersonnel;
import com.chhimek.supportmgmt.model.SupportRegPerson;

@Repository
public class LeaveDaoImpl implements LeaveDao{
	
	@Resource
	private SessionFactory sessionFactory;

	@Override
	@Transactional
	public void saveLeaveDay(LeaveDays leaveDay) {
		Session sess = sessionFactory.getCurrentSession();
		sess.save(leaveDay);
		
	}

	@Override
	@Transactional
	public List<LeaveDays> listLeaveByPerson(SupportPersonnel personnel) {
		Session sess = sessionFactory.getCurrentSession();
		Criteria crt = sess.createCriteria(LeaveDays.class);
		crt.add(Restrictions.eq("person.personnelId", personnel.getPersonnelId()));
		return crt.list();
	}

	@Override
	@Transactional
	public void updateLeave(LeaveDays leaveDay) {
		Session sess = sessionFactory.getCurrentSession();
		sess.update(leaveDay);	
	}

	@Override
	@Transactional
	public List<LeaveDays> listLeaveByPersonAndStatus(SupportPersonnel personnel) {
		Session sess = sessionFactory.getCurrentSession();
		Criteria crt = sess.createCriteria(LeaveDays.class);
		crt.add(Restrictions.eq("person.personnelId", personnel.getPersonnelId()));
		crt.add(Restrictions.eq("isPast", false));
		return crt.list();
	}

	@Override
	@Transactional
	public List<LeaveDays> listLeaveByRegPerson(SupportRegPerson personnel) {
		Session sess = sessionFactory.getCurrentSession();
		Criteria crt = sess.createCriteria(LeaveDays.class);
		crt.add(Restrictions.eq("regperson.id", personnel.getId()));
		return crt.list();
	}

	@Override
	@Transactional
	public List<LeaveDays> listLeaveByRegPersonAndStatus(SupportRegPerson personnel) {
		Session sess = sessionFactory.getCurrentSession();
		Criteria crt = sess.createCriteria(LeaveDays.class);
		crt.add(Restrictions.eq("regperson.id", personnel.getId()));
		crt.add(Restrictions.eq("isPast", false));
		return crt.list();
	}

	@Override
	@Transactional
	public LeaveDays getLeaveById(int id) {
		Session sess = sessionFactory.getCurrentSession();
		LeaveDays leaveDays = (LeaveDays) sess.get(LeaveDays.class, id);
		return leaveDays;
	}

	@Override
	@Transactional
	public void deleteLeave(int id) {
		Session sess = sessionFactory.getCurrentSession();
		LeaveDays leaveDays = (LeaveDays) sess.get(LeaveDays.class, id);
		sess.delete(leaveDays);
		
	}

}
