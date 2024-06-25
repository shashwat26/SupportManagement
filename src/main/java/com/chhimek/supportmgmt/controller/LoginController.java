package com.chhimek.supportmgmt.controller;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.chhimek.supportmgmt.model.User;
import com.chhimek.supportmgmt.model.UserType;
import com.chhimek.supportmgmt.service.ExchangeLeaveDao;
import com.chhimek.supportmgmt.service.HolidayDao;
import com.chhimek.supportmgmt.service.LeaveDao;
import com.chhimek.supportmgmt.service.UserDao;

@Controller
public class LoginController {
	
	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
	
	@Autowired
	private UserDao uDao;
	
	@Autowired
	private HolidayDao hDao;
	
	@Autowired
	private LeaveDao lDao;
	
	@Autowired
	private ExchangeLeaveDao elDao;
	
	@RequestMapping(value = "/userlogin", method = RequestMethod.GET)
	public String adminLogin(HttpSession session) {
		if(!StringUtils.isEmpty((CharSequence) session.getAttribute("activeuser"))){
			return "redirect:/dashboard";
		}
		logger.info("inside login form method");
		return "login";
	}
	
	@RequestMapping(value = "/userlogin", method = RequestMethod.POST)
	public String postLogin(@ModelAttribute User u, Model model, HttpSession session) {
		u.setPassword(DigestUtils.md5DigestAsHex(u.getPassword().getBytes()));
		User user = uDao.login(u.getUsername(), u.getPassword());
		if(user != null && user.getType().equals(UserType.ADMIN)) {
			logger.info("admin login success!!!");
			Calendar cal = new GregorianCalendar();
			Date today = new Date(cal.getTimeInMillis());
			SimpleDateFormat df = new SimpleDateFormat("HH:mm");
			String time = df.format(cal.getTime());
			model.addAttribute("userId", user.getId());
			model.addAttribute("date", today);
			model.addAttribute("time", time);
			session.setAttribute("activeuser", u.getUsername());
			session.setMaxInactiveInterval(300);
			
			return "adminPage";
		}
		if(user != null && user.getType().equals(UserType.HOFFICE)) {
			logger.info("headoffice user login success!!!");
			session.setAttribute("headofficeuser", u.getUsername());
			session.setMaxInactiveInterval(300);	
			model.addAttribute("supportPerson", user.getSupportPerson());
			model.addAttribute("holidayList", hDao.listAll());
			model.addAttribute("leaveList", lDao.listLeaveByPerson(user.getSupportPerson()));
			model.addAttribute("exchangeLeaveList", elDao.listExchangeLeavesByPerson(user.getSupportPerson()));
			return "userPage";
		}
		if(user != null && user.getType().equals(UserType.REGION)) {
			logger.info("regional user login success!!!");
			session.setAttribute("regionaluser", u.getUsername());
			session.setMaxInactiveInterval(300);
			model.addAttribute("supportRegPerson", user.getSupportRegPerson());
			model.addAttribute("holidayList", hDao.listAll());
			model.addAttribute("leaveList", lDao.listLeaveByRegPerson(user.getSupportRegPerson()));
			return "userRegPage";
		}
		logger.info("login Failure!!");
		model.addAttribute("error", "User does not exist!!!!");
		return "login";
	}
	
	
	@RequestMapping(value = "/dashboard", method = RequestMethod.GET)
	public String getDashboard(Model model, HttpSession session) {
		if(StringUtils.isEmpty((CharSequence) session.getAttribute("activeuser"))) {
			model.addAttribute("error", "Please Sign in!!!");
			return "login";
		}
		model.addAttribute("userId", 1);
		Calendar cal = new GregorianCalendar();
		Date today = new Date(cal.getTimeInMillis());
		SimpleDateFormat df = new SimpleDateFormat("HH:mm");
		String time = df.format(cal.getTime());
		model.addAttribute("date", today);
		model.addAttribute("time", time);
		return "adminPage";
		
	}
	
	@RequestMapping(value = "{id}/dashboard", method = RequestMethod.GET)
	public String getDashboardId(@PathVariable("id")int id, Model model, HttpSession session) {
		if(StringUtils.isEmpty((CharSequence) session.getAttribute("activeuser"))) {
			model.addAttribute("error", "Please Sign in!!!");
			return "login";
		}
		model.addAttribute("userId", id);
		Calendar cal = new GregorianCalendar();
		Date today = new Date(cal.getTimeInMillis());
		SimpleDateFormat df = new SimpleDateFormat("HH:mm");
		String time = df.format(cal.getTime());
		model.addAttribute("date", today);
		model.addAttribute("time", time);
		return "adminPage";
		
	}
	
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(HttpSession session, Model model) {
		logger.info("logout success!!!");
		session.invalidate();
		model.addAttribute("error", "Logged Out!!!");
		return "login";
	}

}
