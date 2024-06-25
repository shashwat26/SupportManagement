package com.chhimek.supportmgmt.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.chhimek.supportmgmt.model.ExchangeLeaves;
import com.chhimek.supportmgmt.model.Holiday;
import com.chhimek.supportmgmt.model.LeaveDays;
import com.chhimek.supportmgmt.model.SupportPersonnel;
import com.chhimek.supportmgmt.model.SupportRegPerson;
import com.chhimek.supportmgmt.service.ExchangeLeaveDao;
import com.chhimek.supportmgmt.service.HolidayDao;
import com.chhimek.supportmgmt.service.LeaveDao;
import com.chhimek.supportmgmt.service.PersonnelDao;
import com.chhimek.supportmgmt.service.RegPersonDao;

@Controller
public class HolidayController {
	
	@Autowired
	private HolidayDao hDao;
	
	@Autowired
	private PersonnelDao pDao;
	
	@Autowired
	private LeaveDao lDao;
	
	@Autowired
	private RegPersonDao rpDao;
	
	@Autowired
	private ExchangeLeaveDao elDao;
	
	@RequestMapping(value = "/setHoliday", method = RequestMethod.GET)
	public String setHoliday(Model model) {
		
		return "holidaySetter";
	}
	
	@RequestMapping(value = "/insertHoliday", method = RequestMethod.POST)
	public String insertHoliday(@ModelAttribute Holiday holiday, Model model, HttpSession session) {
		if(StringUtils.isEmpty((CharSequence) session.getAttribute("activeuser"))) {
			model.addAttribute("error", "Please Sign in!!!");
			return "login";
		}
		//holiday.setHoliday(holiday.getHoliday().getTime());
		Date dt = holiday.getHoliday();
		System.out.println(dt);
		hDao.save(holiday);
		model.addAttribute("success", "You added "+dt +" date as holiday");
		return "holidaySetter";
	}
	
	
	@RequestMapping(value = "/setLeave", method = RequestMethod.GET)
	public String setLeave(Model model, HttpSession session) {
		if(StringUtils.isEmpty((CharSequence) session.getAttribute("activeuser"))) {
			model.addAttribute("error", "Please Sign in!!!");
			return "login";
		}
		model.addAttribute("personList", pDao.listAll());
		model.addAttribute("regPersonList", rpDao.listAll());
		model.addAttribute("status", null);
		return "leaveSetter";
	}
	
	@RequestMapping(value = "/insertLeave", method = RequestMethod.POST)
	public String insertLeave(@ModelAttribute LeaveDays leave, Model model, HttpSession session) {
		if(StringUtils.isEmpty((CharSequence) session.getAttribute("activeuser"))) {
			model.addAttribute("error", "Please Sign in!!!");
			return "login";
		}
		Date lt = leave.getLeaveDay();
		leave.setPerson(pDao.getPersonById(leave.getPerson().getPersonnelId()));
		String fname = leave.getPerson().getFname();
		String lname = leave.getPerson().getLname();
		lDao.saveLeaveDay(leave);
		model.addAttribute("success", "You added "+lt +" date as holiday for "+fname + " "+ lname);
		return "leaveSetter";
	}
	
	@RequestMapping(value = "/insertLeaveByUser", method = RequestMethod.POST)
	public String insertLeaveByUser(@ModelAttribute LeaveDays leave, Model model, HttpSession session) {
		if(StringUtils.isEmpty((CharSequence) session.getAttribute("headofficeuser"))) {
			model.addAttribute("error", "Please Sign in!!!");
			return "login";
		}
		Date lt = leave.getLeaveDay();
		SupportPersonnel support = pDao.getPersonById(leave.getPerson().getPersonnelId());
		leave.setPerson(pDao.getPersonById(leave.getPerson().getPersonnelId()));
		lDao.saveLeaveDay(leave);
		model.addAttribute("success", "You added "+lt +" date as holiday for "+leave.getPerson().getFname() + " "+ leave.getPerson().getLname());
		model.addAttribute("holidayList", hDao.listAll());
		model.addAttribute("leaveList", lDao.listLeaveByPerson(support));
		model.addAttribute("exchangeLeaveList", elDao.listExchangeLeavesByPerson(support));
		model.addAttribute("supportPerson", support);
		return "userPage";
	}
	
	@RequestMapping(value = "/insertExchangeLeaveByUser", method = RequestMethod.POST)
	public String insertExchangeLeaveByUser(@ModelAttribute ExchangeLeaves leaves, Model model, HttpSession session) {
		if(StringUtils.isEmpty((CharSequence) session.getAttribute("headofficeuser"))) {
			model.addAttribute("error", "Please Sign in!!!");
			return "login";
		}
		Date exchangeLeave = leaves.getExchangeLeaveDate();
		SupportPersonnel support = pDao.getPersonById(leaves.getPersonnel().getPersonnelId());
		leaves.setPersonnel(pDao.getPersonById(support.getPersonnelId()));
		elDao.save(leaves);
		model.addAttribute("success", "You added "+exchangeLeave +" date as exchange leave for "+leaves.getPersonnel().getFname() + " "+ leaves.getPersonnel().getLname());
		model.addAttribute("holidayList", hDao.listAll());
		model.addAttribute("leaveList", lDao.listLeaveByPerson(support));
		model.addAttribute("exchangeLeaveList", elDao.listExchangeLeavesByPerson(support));
		model.addAttribute("supportPerson", support);
		return "userPage";
	}
	
	@RequestMapping(value = "{id}/insertWorkFromHome", method = RequestMethod.GET)
	public String insertWorkfromHome(@PathVariable("id")int id, Model model, HttpSession session) {
		if(StringUtils.isEmpty((CharSequence) session.getAttribute("headofficeuser"))) {
			model.addAttribute("error", "Please Sign in!!!");
			return "login";
		}
		SupportPersonnel support = pDao.getPersonById(id);
		support.setWorkingFromHome(true);
		pDao.updatePerson(support);
		model.addAttribute("holidayList", hDao.listAll());
		model.addAttribute("leaveList", lDao.listLeaveByPerson(support));
		model.addAttribute("exchangeLeaveList", elDao.listExchangeLeavesByPerson(support));
		model.addAttribute("supportPerson", support);
		model.addAttribute("success", "You are working from home!!!!");
		return "userPage";
	}
	
	@RequestMapping(value = "{id}/removeWorkFromHome", method = RequestMethod.GET)
	public String removeWorkfromHome(@PathVariable("id")int id, Model model, HttpSession session) {
		if(StringUtils.isEmpty((CharSequence) session.getAttribute("headofficeuser"))) {
			model.addAttribute("error", "Please Sign in!!!");
			return "login";
		}
		SupportPersonnel support = pDao.getPersonById(id);
		support.setWorkingFromHome(false);
		pDao.updatePerson(support);
		model.addAttribute("holidayList", hDao.listAll());
		model.addAttribute("leaveList", lDao.listLeaveByPerson(support));
		model.addAttribute("exchangeLeaveList", elDao.listExchangeLeavesByPerson(support));
		model.addAttribute("supportPerson", support);
		model.addAttribute("success", "You removed work from home status!!!!");
		return "userPage";
	}
	
	@RequestMapping(value = "/insertLeaveByAdmin", method = RequestMethod.POST)
	public String insertLeaveByAdmin(@ModelAttribute LeaveDays leave, Model model, HttpSession session) {
		if(StringUtils.isEmpty((CharSequence) session.getAttribute("activeuser"))) {
			model.addAttribute("error", "Please Sign in!!!");
			return "login";
		}
		Date lt = leave.getLeaveDay();
		SupportPersonnel support = pDao.getPersonById(leave.getPerson().getPersonnelId());
		leave.setPerson(pDao.getPersonById(leave.getPerson().getPersonnelId()));
		lDao.saveLeaveDay(leave);
		model.addAttribute("success", "You added "+lt +" date as holiday for "+leave.getPerson().getFname() + " "+ leave.getPerson().getLname());
		model.addAttribute("holidayList", hDao.listAll());
		model.addAttribute("leaveList", lDao.listLeaveByPerson(support));
		model.addAttribute("person", support);
		return "personDetails";
	}
	
	@RequestMapping(value = "/insertRegLeaveByUser", method = RequestMethod.POST)
	public String insertRegLeaveByUser(@ModelAttribute LeaveDays leave, Model model, HttpSession session) {
		if(StringUtils.isEmpty((CharSequence) session.getAttribute("regionaluser"))) {
			model.addAttribute("error", "Please Sign in!!!");
			return "login";
		}
		Date lt = leave.getLeaveDay();
		SupportRegPerson support = rpDao.getPersonById(leave.getRegperson().getId());
		leave.setRegperson(rpDao.getPersonById(leave.getRegperson().getId()));
		lDao.saveLeaveDay(leave);
		model.addAttribute("success", "You added "+lt +" date as holiday for "+leave.getRegperson().getFname() + " "+ leave.getRegperson().getLname());
		model.addAttribute("holidayList", hDao.listAll());
		model.addAttribute("leaveList", lDao.listLeaveByRegPerson(support));
		model.addAttribute("supportRegPerson", support);
		return "userRegPage";
	}
	
	@RequestMapping(value = "/insertRegLeaveByAdmin", method = RequestMethod.POST)
	public String insertRegLeaveByAdmin(@ModelAttribute LeaveDays leave, Model model, HttpSession session) {
		if(StringUtils.isEmpty((CharSequence) session.getAttribute("regionaluser"))) {
			model.addAttribute("error", "Please Sign in!!!");
			return "login";
		}
		Date lt = leave.getLeaveDay();
		SupportRegPerson support = rpDao.getPersonById(leave.getRegperson().getId());
		leave.setRegperson(rpDao.getPersonById(leave.getRegperson().getId()));
		lDao.saveLeaveDay(leave);
		model.addAttribute("success", "You added "+lt +" date as holiday for "+leave.getRegperson().getFname() + " "+ leave.getRegperson().getLname());
		model.addAttribute("holidayList", hDao.listAll());
		model.addAttribute("leaveList", lDao.listLeaveByRegPerson(support));
		model.addAttribute("person", support);
		return "regPersonDetails";
	}
	
	@RequestMapping(value = "{id}/insertLeave", method = RequestMethod.POST)
	public String insertLeaves(@ModelAttribute LeaveDays leave, Model model, HttpSession session) {
		if(StringUtils.isEmpty((CharSequence) session.getAttribute("activeuser"))) {
			model.addAttribute("error", "Please Sign in!!!");
			return "login";
		}
		Date lt = leave.getLeaveDay();
		leave.setPerson(pDao.getPersonById(leave.getPerson().getPersonnelId()));
		String fname = leave.getPerson().getFname();
		String lname = leave.getPerson().getLname();
		lDao.saveLeaveDay(leave);
		model.addAttribute("success", "You added "+lt +" date as holiday for "+fname + " "+ lname);
		return "leaveSetter";
	}
	
	@RequestMapping(value = "/insertregLeave", method = RequestMethod.POST)
	public String insertRegLeave(@ModelAttribute LeaveDays leave, Model model, HttpSession session) {
		if(StringUtils.isEmpty((CharSequence) session.getAttribute("activeuser"))) {
			model.addAttribute("error", "Please Sign in!!!");
			return "login";
		}
		Date lt = leave.getLeaveDay();
		leave.setRegperson(rpDao.getPersonById(leave.getRegperson().getId()));
		String fname = leave.getRegperson().getFname();
		String lname = leave.getRegperson().getLname();
		lDao.saveLeaveDay(leave);
		model.addAttribute("success", "You added "+lt +" date as holiday for "+fname + " "+ lname);
		return "leaveSetter";
	
	}
	
	@RequestMapping(value = "{id}/insertregLeave", method = RequestMethod.POST)
	public String insertRegLeaves(@ModelAttribute LeaveDays leave, Model model, HttpSession session) {
		if(StringUtils.isEmpty((CharSequence) session.getAttribute("activeuser"))) {
			model.addAttribute("error", "Please Sign in!!!");
			return "login";
		}
		Date lt = leave.getLeaveDay();
		leave.setRegperson(rpDao.getPersonById(leave.getRegperson().getId()));
		String fname = leave.getRegperson().getFname();
		String lname = leave.getRegperson().getLname();
		lDao.saveLeaveDay(leave);
		model.addAttribute("success", "You added "+lt +" date as holiday for "+fname + " "+ lname);
		return "leaveSetter";
	
	}
	
	@RequestMapping(value = "/listholidays", method = RequestMethod.GET)
	public String listHolidays(Model model, HttpSession session) {
		if(StringUtils.isEmpty((CharSequence) session.getAttribute("activeuser"))) {
			model.addAttribute("error", "Please Sign in!!!");
			return "login";
		}
		List<Holiday> holidayList = hDao.listAll();
		model.addAttribute("holidaylist", holidayList);
		return "listHolidays";
	}
	
	@RequestMapping(value = "{id}/deleteHoliday")
	public String deleteHoliday(@PathVariable("id")int id,Model model, HttpSession session) {
		if(StringUtils.isEmpty((CharSequence) session.getAttribute("activeuser"))) {
			model.addAttribute("error", "Please Sign in!!!");
			return "login";
		}
		hDao.deleteHoliday(id);
		List<Holiday> holidayList = hDao.listAll();
		model.addAttribute("holidaylist", holidayList);
		return "listHolidays";
	}
	
	//Exchange Leave and Work From home By Admin 
	
	@RequestMapping(value = "{id}/insertWorkFromHomeByAdmin", method = RequestMethod.GET)
	public String inserWorkFromHomeByAdmin(@PathVariable("id")int id,Model model, HttpSession session) {
		if(StringUtils.isEmpty((CharSequence) session.getAttribute("activeuser"))) {
			model.addAttribute("error", "Please Sign in!!!");
			return "login";
		}
		SupportPersonnel support = pDao.getPersonById(id);
		support.setWorkingFromHome(true);
		pDao.updatePerson(support);
		model.addAttribute("holidayList", hDao.listAll());
		model.addAttribute("leaveList", lDao.listLeaveByPerson(support));
		model.addAttribute("exchangeLeaveList", elDao.listExchangeLeavesByPerson(support));
		model.addAttribute("person", support);
	//	model.addAttribute("success", "You are working from home!!!!");
		return "personDetails";
	}
	
	@RequestMapping(value = "{id}/removeWorkFromHomeByAdmin", method = RequestMethod.GET)
	public String removeWorkFromHomeByAdmin(@PathVariable("id")int id, Model model, HttpSession session) {
		if(StringUtils.isEmpty((CharSequence) session.getAttribute("activeuser"))) {
			model.addAttribute("error", "Please Sign in!!!");
			return "login";
		}
		SupportPersonnel support = pDao.getPersonById(id);
		support.setWorkingFromHome(false);
		pDao.updatePerson(support);
		model.addAttribute("holidayList", hDao.listAll());
		model.addAttribute("leaveList", lDao.listLeaveByPerson(support));
		model.addAttribute("exchangeLeaveList", elDao.listExchangeLeavesByPerson(support));
		model.addAttribute("person", support);
	//	model.addAttribute("success", "is working from home!!!!");
		return "personDetails";
	}
	
	@RequestMapping(value = "/insertExchangeLeaveByAdmin", method = RequestMethod.POST)
	public String insertExchangeLeaveByAdmin(@ModelAttribute ExchangeLeaves leaves, Model model, HttpSession session) {
		if(StringUtils.isEmpty((CharSequence) session.getAttribute("activeuser"))) {
			model.addAttribute("error", "Please Sign in!!!");
			return "login";
		}
		Date exchangeLeave = leaves.getExchangeLeaveDate();
		SupportPersonnel support = pDao.getPersonById(leaves.getPersonnel().getPersonnelId());
		leaves.setPersonnel(pDao.getPersonById(support.getPersonnelId()));
		elDao.save(leaves);
		model.addAttribute("success", "You added "+exchangeLeave +" date as exchange leave for "+leaves.getPersonnel().getFname() + " "+ leaves.getPersonnel().getLname());
		model.addAttribute("holidayList", hDao.listAll());
		model.addAttribute("leaveList", lDao.listLeaveByPerson(support));
		model.addAttribute("exchangeLeaveList", elDao.listExchangeLeavesByPerson(support));
		model.addAttribute("person", support);
		return "personDetails";
	}
	
	

}
