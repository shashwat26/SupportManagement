package com.chhimek.supportmgmt.service;

import javax.annotation.Resource;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.chhimek.supportmgmt.model.SupportPersonnel;
import com.chhimek.supportmgmt.model.User;

@Repository
public class UserDaoImpl implements UserDao{
	
	@Resource
	private SessionFactory sessionFactory;
	
	@Autowired
	private PersonnelDao pDao;

	@Override
	@Transactional
	public User login(String username, String password) {
		Session sess = sessionFactory.getCurrentSession();
		Criteria crt = sess.createCriteria(User.class);
		crt.add(Restrictions.eq("username", username)).add(Restrictions.eq("password", password));
		User u = (User) crt.uniqueResult();
		return u;
	}

	@Override
	@Transactional
	public void saveUser(User u) {
		Session sess = sessionFactory.getCurrentSession();
		sess.save(u);
		
	}

	@Override
	@Transactional
	public User showUser(int id) {
		Session sess = sessionFactory.getCurrentSession();
		User u = (User) sess.get(User.class, id);
		return u;
	}

	@Override
	@Transactional
	public User getUserBySupportPersonId(int id) {
		Session sess = sessionFactory.getCurrentSession();
		Criteria crt = sess.createCriteria(User.class);
		crt.add(Restrictions.eq("supportPerson.personnelId", id));
		User u = (User) crt.uniqueResult();
		return u;
	}

	@Override
	@Transactional
	public void updateUser(User user) {
		Session sess = sessionFactory.getCurrentSession();
		sess.update(user);	
	}

	@Override
	@Transactional
	public User getUserBySupportRegPersonId(int id) {
		Session sess = sessionFactory.getCurrentSession();
		Criteria crt = sess.createCriteria(User.class);
		crt.add(Restrictions.eq("supportRegPerson.id", id));
		User u = (User) crt.uniqueResult();
		return u;
	}

	@Override
	@Transactional 
	public User getUserById(int id) {
		Session sess = sessionFactory.getCurrentSession();
		User u = (User) sess.get(User.class, id);
		return u;
	}

	@Override
	@Transactional
	public void deleteUser(int id) {
		Session sess = sessionFactory.getCurrentSession();
		User u = (User) sess.get(User.class, id);
		sess.delete(u);
		
	}

}
