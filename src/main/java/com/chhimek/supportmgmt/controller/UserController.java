package com.chhimek.supportmgmt.controller;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.servlet.http.HttpSession;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.chhimek.supportmgmt.model.Department;
import com.chhimek.supportmgmt.model.LeaveDays;
import com.chhimek.supportmgmt.model.SupportCategory;
import com.chhimek.supportmgmt.model.SupportPersonnel;
import com.chhimek.supportmgmt.model.SupportRegPerson;
import com.chhimek.supportmgmt.model.SupportRegion;
import com.chhimek.supportmgmt.model.User;
import com.chhimek.supportmgmt.service.CategoryDao;
import com.chhimek.supportmgmt.service.DepartmentDao;
import com.chhimek.supportmgmt.service.ExchangeLeaveDao;
import com.chhimek.supportmgmt.service.HolidayDao;
import com.chhimek.supportmgmt.service.LeaveDao;
import com.chhimek.supportmgmt.service.PersonnelDao;
import com.chhimek.supportmgmt.service.RegPersonDao;
import com.chhimek.supportmgmt.service.RegionDao;
import com.chhimek.supportmgmt.service.UserDao;



@Controller
public class UserController {
	
	@Autowired
	private UserDao uDao;
	
	@Autowired
	private CategoryDao cDao;
	
	@Autowired
	private PersonnelDao pDao;
	
	@Autowired
	private RegPersonDao rpDao;
	
	@Autowired
	private HolidayDao hDao;
	
	@Autowired
	private LeaveDao lDao;
	
	@Autowired
	private RegionDao rDao;
	
	@Autowired
	private DepartmentDao dDao;
	
	@Autowired
	private ExchangeLeaveDao elDao;
	
	
	@RequestMapping(value = "{id}/editSupportPersonByUser", method = RequestMethod.GET)
	public String editPersonByUser(@PathVariable("id")int id, Model model, HttpSession session) {
		if(StringUtils.isEmpty((CharSequence) session.getAttribute("headofficeuser"))) {
			model.addAttribute("error", "Please Sign in!!!");
			return "login";
		}
		User user = uDao.getUserBySupportPersonId(id);
		SupportCategory category = user.getSupportPerson().getSupportcategory();
		Department department = user.getSupportPerson().getDepartment();
		model.addAttribute("user", user);
		model.addAttribute("categoryz", category);
		model.addAttribute("department", department);
		model.addAttribute("categoryList", cDao.listAll());
		model.addAttribute("departmentList", dDao.listAll());
		
		return "editPersonPage";
	}
	
	@RequestMapping(value = "{id}/updatePersonByUser", method = RequestMethod.POST)
	public String updatePersonByUser(@ModelAttribute User user, Model model,@PathVariable("id") int id, HttpSession session) {
		if(StringUtils.isEmpty((CharSequence) session.getAttribute("headofficeuser"))) {
			model.addAttribute("error", "Please Sign in!!!");
			return "login";
		}
		SupportPersonnel person = user.getSupportPerson();
		person.setPersonnelId(id);
		person.setPhotoName(pDao.getPersonById(id).getPhotoName());
		person.setSupportcategory(cDao.getCategoryFromId(user.getSupportPerson().getSupportcategory().getId()));
		person.setDepartment(dDao.getDepartmentById(user.getSupportPerson().getDepartment().getId()));
		User u = uDao.getUserBySupportPersonId(id);
		u.setSupportPerson(person);
		u.setUsername(user.getUsername());
		uDao.updateUser(u);
		model.addAttribute("supportPerson", u.getSupportPerson());
		model.addAttribute("holidayList", hDao.listAll());
		model.addAttribute("leaveList", lDao.listLeaveByPerson(u.getSupportPerson()));
		model.addAttribute("exchangeLeaveList", elDao.listExchangeLeavesByPerson(u.getSupportPerson()));
		model.addAttribute("success", "User updated successfully!!");
		return "userPage";
	}
	
	@RequestMapping(value = "{id}/editSupportRegPersonByUser", method = RequestMethod.GET)
	public String editRegPersonByUser(@PathVariable("id")int id, Model model, HttpSession session) {
		if(StringUtils.isEmpty((CharSequence) session.getAttribute("regionaluser"))) {
			model.addAttribute("error", "Please Sign in!!!");
			return "login";
		}
		User user = uDao.getUserBySupportRegPersonId(id);
		SupportRegion region = user.getSupportRegPerson().getRegion();
		model.addAttribute("user", user);
		model.addAttribute("regions", region);
		model.addAttribute("regionList", rDao.listAllRegion());
		return "editRegPersonPage";
	}
	
	@RequestMapping(value = "{id}/updateRegionPersonByUser", method = RequestMethod.POST)
	public String updateRegPersonByUser(@ModelAttribute User user, Model model, @PathVariable("id")int id, HttpSession session) {
		if(StringUtils.isEmpty((CharSequence) session.getAttribute("regionaluser"))) {
			model.addAttribute("error", "Please Sign in!!!");
			return "login";
		}
		SupportRegPerson person = user.getSupportRegPerson();
		person.setId(id);
		person.setPhotoName(rpDao.getPersonById(id).getPhotoName());
		person.setRegion(rDao.getRegionById(user.getSupportRegPerson().getRegion().getId()));
		User u = uDao.getUserBySupportRegPersonId(id);
		u.setSupportRegPerson(person);
		u.setUsername(user.getUsername());
		uDao.updateUser(u);
		model.addAttribute("supportRegPerson", user.getSupportRegPerson());
		model.addAttribute("holidayList", hDao.listAll());
		model.addAttribute("leaveList", lDao.listLeaveByRegPerson(user.getSupportRegPerson()));
		return "userRegPage";
	}
	
	@RequestMapping(value = "deleteLeaveByUser/{id}/{lid}", method = RequestMethod.GET)
	public String deleteLeaveByUser(@PathVariable("id")int id, @PathVariable("lid")int lid, Model model, HttpSession session) {
		if(StringUtils.isEmpty((CharSequence) session.getAttribute("headofficeuser"))) {
			model.addAttribute("error", "Please Sign in!!");
			return "login";
		}
		User u = uDao.getUserBySupportPersonId(id);
		lDao.deleteLeave(lid);
		model.addAttribute("supportPerson", u.getSupportPerson());
		model.addAttribute("holidayList", hDao.listAll());
	    model.addAttribute("leaveList", lDao.listLeaveByPerson(u.getSupportPerson()));
		model.addAttribute("exchangeLeaveList", elDao.listExchangeLeavesByPerson(u.getSupportPerson()));
		return "userPage";
	}
	
	@RequestMapping(value = "deleteExchangeLeaveByUser/{id}/{elid}", method = RequestMethod.GET)
	public String deleteExchangeLeaveByUser(@PathVariable("id")int id, @PathVariable("elid") int elid, Model model, HttpSession session) {
		User u = uDao.getUserBySupportPersonId(id);
		elDao.delete(elid);
		model.addAttribute("supportPerson", u.getSupportPerson());
		model.addAttribute("holidayList", hDao.listAll());
	    model.addAttribute("leaveList", lDao.listLeaveByPerson(u.getSupportPerson()));
		model.addAttribute("exchangeLeaveList", elDao.listExchangeLeavesByPerson(u.getSupportPerson()));
		return "userPage";
	}
	
	@RequestMapping(value = "deleteLeaveByRegUser/{id}/{lid}", method = RequestMethod.GET)
	public String deleteLeaveByRegUser(@PathVariable("id")int id, @PathVariable("lid")int lid, Model model, HttpSession session) {
		if(StringUtils.isEmpty((CharSequence) session.getAttribute("regionaluser"))) {
			model.addAttribute("error", "Please Sign in!!!");
			return "login";
		}
		User u = uDao.getUserBySupportRegPersonId(id);
		lDao.deleteLeave(lid);
		model.addAttribute("supportRegPerson", u.getSupportRegPerson());
		model.addAttribute("holidayList", hDao.listAll());
		model.addAttribute("leaveList", lDao.listLeaveByRegPerson(u.getSupportRegPerson()));
		return "userRegPage";
	}
	
	@RequestMapping(value = "userdashboard", method = RequestMethod.GET)
	public String userDashboard(Model model, HttpSession session) {
		
		return null;
	}
	
	@RequestMapping(value = "{id}/changePhotoByUser", method = RequestMethod.GET)
	public String changePersonPhotoByUser(@PathVariable("id")int id, Model model, HttpSession session) {
		if(StringUtils.isEmpty((CharSequence) session.getAttribute("headofficeuser"))) {
			model.addAttribute("error", "Please Sign in!!!");
			return "login";
		}
		SupportPersonnel person = pDao.getPersonById(id);
		model.addAttribute("supportperson", person);
		return "changePhotoByUser";
	}
	
	@RequestMapping(value = "{id}/updatePhotoByUser", method = RequestMethod.POST)
	public String updatePhoto(@RequestParam("file") MultipartFile file,@PathVariable("id") int id, Model model, HttpSession session) throws IOException {
		if(StringUtils.isEmpty((CharSequence) session.getAttribute("headofficeuser"))) {
			model.addAttribute("error", "Please Sign in!!!");
			return "login";
		}
		SupportPersonnel person = pDao.getPersonById(id);
		if(!file.isEmpty()) {
			String fileName = pDao.getRandomFileName(10);
			System.out.println(fileName);
			String extension = FilenameUtils.getExtension(file.getOriginalFilename().toString());
			System.out.println(extension);
			String photoName = fileName + "."+extension;
			if(!person.getPhotoName().equals("defaultimage.jpg")) {
				String oldFileName = person.getPhotoName();
				File f = new File("E:\\STS Workspace\\SupportManagementSystem\\src\\main\\webapp\\resources\\imgs\\"+oldFileName);
				if(f.delete()) {
					System.out.println("Old file deleted!!!");
				} else {
					System.out.println("Cannot delete old file!!!");
				}
				
				FileOutputStream out = new FileOutputStream("E:\\STS Workspace\\SupportManagementSystem\\src\\main\\webapp\\resources\\imgs\\"+photoName);
				out.write(file.getBytes());
				out.close();
				person.setPhotoName(photoName);
				pDao.updatePerson(person);
				model.addAttribute("msg","file edited successfully!!");
				model.addAttribute("supportPerson", person);
				model.addAttribute("holidayList", hDao.listAll());
				model.addAttribute("leaveList", lDao.listLeaveByPerson(person));
				model.addAttribute("exchangeLeaveList", elDao.listExchangeLeavesByPerson(person));
				session.invalidate();
				model.addAttribute("error", "Photo Changed. Please Login Again!!!");
				return "login";
			} else {
				FileOutputStream out = new FileOutputStream("E:\\STS Workspace\\SupportManagementSystem\\src\\main\\webapp\\resources\\imgs\\"+photoName);
				out.write(file.getBytes());
				out.close();
				person.setPhotoName(photoName);
				pDao.updatePerson(person);
				model.addAttribute("msg","file edited successfully!!");
				model.addAttribute("supportPerson", person);
				model.addAttribute("holidayList", hDao.listAll());
				model.addAttribute("leaveList", lDao.listLeaveByPerson(person));
				model.addAttribute("exchangeLeaveList", elDao.listExchangeLeavesByPerson(person));
				session.invalidate();
				model.addAttribute("error", "Photo Changed. Please Login Again!!!");
				return "login";
				
			}
		} else {
			model.addAttribute("msg","Default Picture added!!");
			person.setPhotoName("defaultimage.jpg");
			pDao.updatePerson(person);
			model.addAttribute("msg","file edited successfully!!");
			model.addAttribute("supportPerson", person);
			model.addAttribute("holidayList", hDao.listAll());
			model.addAttribute("leaveList", lDao.listLeaveByPerson(person));
			model.addAttribute("exchangeLeaveList", elDao.listExchangeLeavesByPerson(person));
		}
		return "userPage";
		
	}
	
	
	@RequestMapping(value = "{id}/changePhotoByRegUser", method = RequestMethod.GET)
	public String changeRegPhoto(@PathVariable("id")int id, Model model, HttpSession session) {
		if(StringUtils.isEmpty((CharSequence) session.getAttribute("regionaluser"))) {
			model.addAttribute("error", "Please Sign in!!!");
			return "login";
		}
		SupportRegPerson person = rpDao.getPersonById(id);
		model.addAttribute("supportperson", person);
		return "changeRegPhotoByUser";
	}
	
	@RequestMapping(value = "{id}/updateRegPhotoByUser", method = RequestMethod.POST)
	public String updateRegPhoto(@RequestParam("file") MultipartFile file,@PathVariable("id") int id, Model model, HttpSession session) throws IOException {
		if(StringUtils.isEmpty((CharSequence) session.getAttribute("regionaluser"))) {
			model.addAttribute("error", "Please Sign in!!!");
			return "login";
		}
		SupportRegPerson person = rpDao.getPersonById(id);
		if(!file.isEmpty()) {
			String fileName = rpDao.getRandomFileName(10);
			System.out.println(fileName);
			String extension = FilenameUtils.getExtension(file.getOriginalFilename().toString());
			System.out.println(extension);
			String photoName = fileName + "."+extension;
			if(!person.getPhotoName().equals("defaultimage.jpg")) {
				String oldFileName = person.getPhotoName();
				File f = new File("E:\\STS Workspace\\SupportManagementSystem\\src\\main\\webapp\\resources\\imgs\\"+oldFileName);
				if(f.delete()) {
					System.out.println("Old file deleted!!!");
				} else {
					System.out.println("Cannot delete old file!!!");
				}
				
				FileOutputStream out = new FileOutputStream("E:\\STS Workspace\\SupportManagementSystem\\src\\main\\webapp\\resources\\imgs\\"+photoName);
				out.write(file.getBytes());
				out.close();
				person.setPhotoName(photoName);
				rpDao.updateRegPerson(person);
				model.addAttribute("supportRegPerson", person);
				model.addAttribute("msg","file edited successfully!!");
				model.addAttribute("imageName", person.getPhotoName().toString());
				model.addAttribute("holidayList", hDao.listAll());
				model.addAttribute("leaveList", lDao.listLeaveByRegPerson(person));
			} else {
				FileOutputStream out = new FileOutputStream("E:\\STS Workspace\\SupportManagementSystem\\src\\main\\webapp\\resources\\imgs\\"+photoName);
				out.write(file.getBytes());
				out.close();
				person.setPhotoName(photoName);
				rpDao.updateRegPerson(person);
				model.addAttribute("msg","file edited successfully!!");
				model.addAttribute("supportRegPerson", person);
				model.addAttribute("imageName", person.getPhotoName().toString());
				model.addAttribute("holidayList", hDao.listAll());
				model.addAttribute("leaveList", lDao.listLeaveByRegPerson(person));
			}
		} else {
			model.addAttribute("msg","Default Picture added!!");
			person.setPhotoName("defaultimage.jpg");
			rpDao.updateRegPerson(person);
			model.addAttribute("supportRegPerson", person);
			model.addAttribute("imageName", person.getPhotoName());
			model.addAttribute("holidayList", hDao.listAll());
			model.addAttribute("leaveList", lDao.listLeaveByRegPerson(person));
		}
		return "userRegPage";
		
	}	
	
	
	@RequestMapping(value = "{id}/changePasswordByUser", method = RequestMethod.GET)
	public String changePasswordByUser(@PathVariable("id")int id, Model model, HttpSession session) {
		if(StringUtils.isEmpty((CharSequence) session.getAttribute("headofficeuser"))) {
			model.addAttribute("error", "Please Sign in!!");
			return "login";
		}
		User u = uDao.getUserBySupportPersonId(id);
		model.addAttribute("user", u);
		return "changePasswordByUser";
	}

	
	@RequestMapping(value = "{id}/changeRegPasswordByUser", method = RequestMethod.GET)
	public String changeRegPasswordByUser(@PathVariable("id")int id, Model model, HttpSession session) {
		if(StringUtils.isEmpty((CharSequence) session.getAttribute("regionaluser"))) {
			model.addAttribute("error", "Please Sign in!!");
			return "login";
		}
		User u = uDao.getUserBySupportRegPersonId(id);
		model.addAttribute("user", u);
		return "changeRegPasswordByUser";
	}
	
	@RequestMapping(value = "passwordChangeByUser", method = RequestMethod.POST)
	public String postchangePasswordByUser(@ModelAttribute User user,@RequestParam("newpass")String newPassword, @RequestParam("conpass")String conformPassword, HttpSession session, Model model) {
		if(StringUtils.isEmpty((CharSequence) session.getAttribute("headofficeuser"))) {
			model.addAttribute("error", "Please Sign in!!!");
			return "login";
		}
		
		User u = uDao.getUserById(user.getId());
		user.setPassword(DigestUtils.md5DigestAsHex(user.getPassword().getBytes()));
		String encNewPassword = DigestUtils.md5DigestAsHex(newPassword.getBytes());
		if(!user.getPassword().equals(u.getPassword())) {
			model.addAttribute("newpass", newPassword);
			model.addAttribute("conpass", conformPassword);
			model.addAttribute("msg", "Password Invalid!!!");
			return "changePasswordByUser";
		} 
		if(!newPassword.equals(conformPassword)) {
			model.addAttribute("msg", "Passwords do not match");
			model.addAttribute("newpass", newPassword);
			model.addAttribute("conpass", conformPassword);
			return "changePasswordByUser";
		} if(user.getPassword().equals(encNewPassword)) {
			model.addAttribute("msg", "New password cannot be same as old password");
			model.addAttribute("newpass", newPassword);
			model.addAttribute("conpass", conformPassword);
			return "changePasswordByUser";
		}
		u.setPassword(DigestUtils.md5DigestAsHex(newPassword.getBytes()));
		uDao.updateUser(u);
		session.invalidate();
		model.addAttribute("error", "Password Changed! Please Sign in");
		return "login";
	}
	
	@RequestMapping(value = "passwordRegChangeByUser", method = RequestMethod.POST)
	public String postchangeRegPasswordByUser(@ModelAttribute User user,@RequestParam("newpass")String newPassword, @RequestParam("conpass")String conformPassword, HttpSession session, Model model) {
		if(StringUtils.isEmpty((CharSequence) session.getAttribute("regionaluser"))) {
			model.addAttribute("error", "Please Sign in!!!");
			return "login";
		}
		
		User u = uDao.getUserById(user.getId());
		user.setPassword(DigestUtils.md5DigestAsHex(user.getPassword().getBytes()));
		String encNewPassword = DigestUtils.md5DigestAsHex(newPassword.getBytes());
		if(!user.getPassword().equals(u.getPassword())) {
			model.addAttribute("newpass", newPassword);
			model.addAttribute("conpass", conformPassword);
			model.addAttribute("msg", "Password Invalid!!!");
			return "changeRegPasswordByUser";
		} 
		if(!newPassword.equals(conformPassword)) {
			model.addAttribute("msg", "Passwords do not match");
			model.addAttribute("newpass", newPassword);
			model.addAttribute("conpass", conformPassword);
			return "changeRegPasswordByUser";
		} if(user.getPassword().equals(encNewPassword)) {
			model.addAttribute("msg", "New password cannot be same as old password");
			model.addAttribute("newpass", newPassword);
			model.addAttribute("conpass", conformPassword);
			return "changeRegPasswordByUser";
		}
		u.setPassword(DigestUtils.md5DigestAsHex(newPassword.getBytes()));
		uDao.updateUser(u);
		session.invalidate();
		model.addAttribute("error", "Password Changed! Please Sign in");
		return "login";
	}
	
}
