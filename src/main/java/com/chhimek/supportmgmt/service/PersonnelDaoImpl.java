package com.chhimek.supportmgmt.service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.nio.charset.Charset;
import java.sql.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Random;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.chhimek.supportmgmt.model.ExchangeLeaves;
import com.chhimek.supportmgmt.model.Holiday;
import com.chhimek.supportmgmt.model.LeaveDays;
import com.chhimek.supportmgmt.model.SupportCategory;
import com.chhimek.supportmgmt.model.SupportPersonnel;

@Repository
public class PersonnelDaoImpl implements PersonnelDao{

	@Resource
	public SessionFactory sessionFactory;
	
	@Autowired
	private HolidayDao hDao;
	
	@Autowired
	private LeaveDao lDao;
	
	@Autowired
	private ExchangeLeaveDao eDao;
	
	@Override
	@Transactional
	public void savePerson(SupportPersonnel personnel) {
		Session sess = sessionFactory.getCurrentSession();
		sess.save(personnel);
		
	}

	@Override
	@Transactional
	public SupportPersonnel getPersonById(int id) {
		Session sess = sessionFactory.getCurrentSession();
		SupportPersonnel person = (SupportPersonnel) sess.get(SupportPersonnel.class, id);
		return person;
	}

	@Override
	@Transactional
	public void updatePerson(SupportPersonnel personnel) {
		Session sess = sessionFactory.getCurrentSession();
		sess.update(personnel);
	}

	@Override
	@Transactional
	public List<SupportPersonnel> listAll() {
		Session sess = sessionFactory.getCurrentSession();
		Criteria crt = sess.createCriteria(SupportPersonnel.class);
		crt.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		return crt.list();
	}

	@Override
	@Transactional
	public void deletePerson(int id) {
		Session sess = sessionFactory.getCurrentSession();
		SupportPersonnel person = (SupportPersonnel) sess.get(SupportPersonnel.class, id);
		sess.delete(person);
	}

	@Override
	@Transactional
	public List<SupportPersonnel> listByCategoryId(int id) {
		Session sess = sessionFactory.getCurrentSession();
		Criteria crt = sess.createCriteria(SupportPersonnel.class);
		crt.add(Restrictions.eq("supportcategory.id", id));
		return crt.list();
	}

	@Override
	@Transactional
	public void updatePresentStatus(SupportPersonnel personnel) {
		Calendar cal = new GregorianCalendar();
		SimpleDateFormat df = new SimpleDateFormat("HH:mm");
		//SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date today = new Date(cal.getTimeInMillis());
		//sdf.format(today);
		String time = df.format(cal.getTime());
		int hr = Integer.parseInt(StringUtils.substringBefore(time, ":"));
		int min = Integer.parseInt(StringUtils.substringAfter(time, ":"));
		int day = cal.get(Calendar.DAY_OF_WEEK);
		boolean status = true;
		List<Holiday> hList = hDao.listPresentandFutureHoliday(); 
		List<LeaveDays> lList = lDao.listLeaveByPersonAndStatus(personnel);
		List<ExchangeLeaves> eList = eDao.listExchangeLeavesByPerson(personnel);
		if(eList.isEmpty()) {
			for(Holiday h: hList) {
				if(DateUtils.isSameDay(today, h.getHoliday())) {
					System.out.println("Same Date");
					personnel.setPresent(false);
					return;
				}
			}
		} else {
				for(Holiday h : hList) {
					if(status == true) {
						for(int i = 0; i <eList.size();i++) {
							if(DateUtils.isSameDay(today, h.getHoliday())  && !DateUtils.isSameDay(today, eList.get(i).getExchangeLeaveDate())) {
								if(i<eList.size()-1) {
									personnel.setPresent(false);
									continue;
								} else {
									personnel.setPresent(false);
									return;
								}
							} else if(DateUtils.isSameDay(today, h.getHoliday()) && DateUtils.isSameDay(h.getHoliday(), eList.get(i).getExchangeLeaveDate())) {
								status = false;
								break;
							} 
							
						}
					}
					
				}
		}
		
		for(LeaveDays l: lList) {
			if(DateUtils.isSameDay(today, l.getLeaveDay())) {
				personnel.setPresent(false);
				return;
			}
		}
		if(day != 0) {
			if(hr >= personnel.getDayStartTime() && hr <= personnel.getDayEndTime()) {
				if(hr == personnel.getDayEndTime() && min > 00) {
					personnel.setPresent(false);
				} else {
					personnel.setPresent(true);
				}
				
			}
		}
		//&& DateUtils.isSameDay(h.getHoliday(), e.getExchangeLeaveDate())
	}

	@Override
	@Transactional
	public void overridePastHoliday() {
		Calendar cal = new GregorianCalendar();
		Date today = new Date(cal.getTimeInMillis());
		List<Holiday> hList = hDao.listAll(); 
		for(Holiday h: hList) {
			int status=today.compareTo(h.getHoliday());
			if(status > 0) {
				if(today.getYear() == h.getHoliday().getYear() && today.getMonth() == h.getHoliday().getMonth() && today.getDate() == h.getHoliday().getDate()) {
					System.out.println("It is today");
					continue;
				}
				h.setPast(true);
				hDao.updateHoliday(h);
			}
		}
		
		
	}

	@Override
	@Transactional
	public void overridePastLeave(SupportPersonnel personnel) {
		Calendar cal = new GregorianCalendar();
		Date today = new Date(cal.getTimeInMillis());
		List<LeaveDays> lList = lDao.listLeaveByPerson(personnel);
		for(LeaveDays l: lList) {
			int status = today.compareTo(l.getLeaveDay());
			if(status > 0) {
				if(today.getYear() == l.getLeaveDay().getYear() && today.getMonth() == l.getLeaveDay().getMonth() && today.getDate() == l.getLeaveDay().getDate()) {
					System.out.println("It is today");
					continue;
				}
				l.setPast(true);
				lDao.updateLeave(l);
			}
		}
	}

	@Override
	@Transactional
	public String getRandomFileName(int size) {
		// length is bounded by 10 Character
        byte[] array = new byte[64];
        new Random().nextBytes(array);
  
        String randomString = new String(array, Charset.forName("UTF-8"));
	    
        StringBuffer r = new StringBuffer();
        for(int k = 0; k< randomString.length(); k++) {
        	char ch = randomString.charAt(k);
        	if (((ch >= 'a' && ch <= 'z')
                    || (ch >= 'A' && ch <= 'Z')
                    || (ch >= '0' && ch <= '9'))
                   && (size > 0)) {
     
                   r.append(ch);
                   size--;
        }
        	
        }
		return r.toString();
	}

	@Override
	@Transactional
	public List<SupportPersonnel> listByCategory(SupportCategory category) {
		Session sess = sessionFactory.getCurrentSession();
		Criteria crt = sess.createCriteria(SupportPersonnel.class);
		crt.add(Restrictions.eq("supportcategory.id", category.getId()));
		return crt.list();
	}

	@Override
	@Transactional
	public List<SupportPersonnel> listByDepartmentId(int id) {
		Session sess = sessionFactory.getCurrentSession();
		Criteria crt = sess.createCriteria(SupportPersonnel.class);	
		crt.add(Restrictions.eq("department.id", id));
		return crt.list();
	}

	
	
}
