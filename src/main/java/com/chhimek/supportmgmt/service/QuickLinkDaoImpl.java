package com.chhimek.supportmgmt.service;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.chhimek.supportmgmt.model.QuickLinks;

@Repository
public class QuickLinkDaoImpl implements QuickLinkDao{

	@Resource
	private SessionFactory sessionFactory;
	
	@Override
	@Transactional
	public void savelink(QuickLinks link) {
		Session session = sessionFactory.getCurrentSession();
		session.save(link);
	}

	@Override
	@Transactional
	public List<QuickLinks> listQuickLinks() {
		Session session = sessionFactory.getCurrentSession();
		Criteria crt = session.createCriteria(QuickLinks.class);
		return crt.list();
	}

	@Override
	@Transactional
	public void deleteLink(int id) {
		Session session = sessionFactory.getCurrentSession();
		QuickLinks ql = (QuickLinks) session.get(QuickLinks.class, id);
		session.delete(ql);
	}

}
