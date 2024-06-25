package com.chhimek.supportmgmt.service;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.chhimek.supportmgmt.model.SupportRegion;

@Repository
public class RegionDaoImpl implements RegionDao {

	
	@Resource
	public SessionFactory sessionFactory;
	
	@Override
	@Transactional
	public List<SupportRegion> listAllRegion() {
		Session session = sessionFactory.getCurrentSession();
		Criteria crt = session.createCriteria(SupportRegion.class);
		crt.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		return crt.list();
	}

	@Override
	@Transactional
	public SupportRegion getRegionById(int id) {
		Session session = sessionFactory.getCurrentSession();
		SupportRegion region = (SupportRegion) session.get(SupportRegion.class, id);
		return region;
	}

	@Override
	@Transactional
	public SupportRegion getRegionByName(String name) {
		Session session = sessionFactory.getCurrentSession();
		Criteria crt = session.createCriteria(SupportRegion.class);
		crt.add(Restrictions.eq("region", name));
		SupportRegion region = (SupportRegion) crt.uniqueResult();
		return region;
	}

}
