package com.chhimek.supportmgmt.service;

import java.nio.charset.Charset;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;
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

import com.chhimek.supportmgmt.model.Holiday;
import com.chhimek.supportmgmt.model.LeaveDays;
import com.chhimek.supportmgmt.model.SupportRegPerson;

@Repository
public class RegPersonDaoImpl implements RegPersonDao {

	@Resource
	public SessionFactory sessionFactory;
	
	@Autowired
	private HolidayDao hDao;
	
	@Autowired
	private LeaveDao lDao;

	@Override
	@Transactional
	public void saveRegPerson(SupportRegPerson person) {
		Session session = sessionFactory.getCurrentSession();
		session.save(person);
	}

	@Override
	@Transactional
	public SupportRegPerson getPersonById(int id) {
		Session session = sessionFactory.getCurrentSession();
		SupportRegPerson person = (SupportRegPerson) session.get(SupportRegPerson.class, id);
		return person;
	}

	@Override
	@Transactional
	public void updateRegPerson(SupportRegPerson person) {
		Session session = sessionFactory.getCurrentSession();
		session.update(person);
	}

	@Override
	@Transactional
	public List<SupportRegPerson> listAll() {
		Session session = sessionFactory.getCurrentSession();
		Criteria crt = session.createCriteria(SupportRegPerson.class);
		crt.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		return crt.list();
	}

	@Override
	@Transactional
	public List<SupportRegPerson> listByRegionId(int id) {
		Session session = sessionFactory.getCurrentSession();
		Criteria crt = session.createCriteria(SupportRegPerson.class);
		crt.add(Restrictions.eq("region.id", id));
		return crt.list();
	}

	@Override
	@Transactional
	public void deleteRegPerson(int id) {
		Session session = sessionFactory.getCurrentSession();
		SupportRegPerson person = (SupportRegPerson) session.get(SupportRegPerson.class, id);
		session.delete(person);

	}

	@Override
	@Transactional
	public String getRandomFileName(int size) {
		byte[] array = new byte[64];
		new Random().nextBytes(array);

		String randomString = new String(array, Charset.forName("UTF-8"));

		StringBuffer r = new StringBuffer();
		for (int k = 0; k < randomString.length(); k++) {
			char ch = randomString.charAt(k);
			if (((ch >= 'a' && ch <= 'z') || (ch >= 'A' && ch <= 'Z') || (ch >= '0' && ch <= '9')) && (size > 0)) {

				r.append(ch);
				size--;
			}

		}
		return r.toString();

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
	public void overridePastLeave(SupportRegPerson person) {
		Calendar cal = new GregorianCalendar();
		Date today = new Date(cal.getTimeInMillis());
		List<LeaveDays> lList = lDao.listLeaveByRegPerson(person);
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
	public void updatePresentStatus(SupportRegPerson person) {
		Calendar cal = new GregorianCalendar();
		SimpleDateFormat df = new SimpleDateFormat("HH:mm");
		Date today = new Date(cal.getTimeInMillis());
		String time = df.format(cal.getTime());
		int hr = Integer.parseInt(StringUtils.substringBefore(time, ":"));
		int day = cal.get(Calendar.DAY_OF_WEEK);
		List<Holiday> hList = hDao.listPresentandFutureHoliday(); 
		List<LeaveDays> lList = lDao.listLeaveByRegPersonAndStatus(person);
		for(Holiday h : hList) {
			if(DateUtils.isSameDay(today, h.getHoliday())) {
				System.out.println("Same Date");
				person.setPresent(false);
				return;
			}
		}
		for(LeaveDays l: lList) {
			if(DateUtils.isSameDay(today, l.getLeaveDay())) {
				person.setPresent(false);
				return;
			}
		}
		if(day != 0) {
			if(hr >= person.getDayStartTime() && hr <= person.getDayEndTime()) {
				person.setPresent(true);
			}
		}
	}

}
