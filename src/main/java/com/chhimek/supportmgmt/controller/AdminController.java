package com.chhimek.supportmgmt.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.DigestUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.chhimek.supportmgmt.model.Department;
import com.chhimek.supportmgmt.model.SupportCategory;
import com.chhimek.supportmgmt.model.SupportPersonnel;
import com.chhimek.supportmgmt.model.SupportRegPerson;
import com.chhimek.supportmgmt.model.SupportRegion;
import com.chhimek.supportmgmt.model.User;
import com.chhimek.supportmgmt.model.UserType;
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
public class AdminController {
	
	@Autowired
	private CategoryDao cDao;
	
	@Autowired
	private PersonnelDao pDao;
	
	@Autowired
	private RegionDao rDao;
	
	@Autowired
	private RegPersonDao rpDao;
	
	@Autowired
	private HolidayDao hDao;
	
	@Autowired
	private LeaveDao lDao;
	
	@Autowired
	private UserDao uDao;
	
	@Autowired
	private DepartmentDao dDao;
	
	@Autowired
	private ExchangeLeaveDao elDao;
	
	@Autowired
	private ServletContext context;
	
	@RequestMapping(value = "/addSupportPerson", method = RequestMethod.GET)
	public String addSupportPerson(Model model, HttpSession session) {
		
		if(StringUtils.isEmpty((CharSequence) session.getAttribute("activeuser"))) {
			model.addAttribute("error", "Please Sign in!!!");
			return "login";
		}
		
		List<SupportCategory> categoryList = cDao.listAll();
		List<Department> departmentList = dDao.listAll();
		model.addAttribute("categoryList", categoryList);
		model.addAttribute("departmentList", departmentList);
		return "addSupport";
	}
	
	
	@RequestMapping(value = "/postSupportPerson", method = RequestMethod.POST)
	public String postSupportPerson(@ModelAttribute SupportPersonnel supportPersonnel, @ModelAttribute User u, Model model, HttpSession session) {
		if(StringUtils.isEmpty((CharSequence) session.getAttribute("activeuser"))) {
			model.addAttribute("error", "Please Sign in!!!");
			return "login";
		}
		
		supportPersonnel.setSupportcategory(cDao.getCategoryFromId(supportPersonnel.getSupportcategory().getId()));
		
		supportPersonnel.setWorkingFromHome(false);
		supportPersonnel.setDepartment(dDao.getDepartmentById(supportPersonnel.getDepartment().getId()));
		pDao.savePerson(supportPersonnel);
		u.setSupportPerson(supportPersonnel);
		u.setType(UserType.HOFFICE);

		u.setPassword(DigestUtils.md5DigestAsHex(u.getPassword().getBytes()));
		uDao.saveUser(u);
		model.addAttribute("supportperson", supportPersonnel);
		return "postSupport";
	}
	
	
	@RequestMapping(value = "/addSupportCategory", method = RequestMethod.GET)
	public String addSupportCategory(HttpSession session, Model model) {
		if(StringUtils.isEmpty((CharSequence) session.getAttribute("activeuser"))) {
			model.addAttribute("error", "Please Sign in!!!");
			return "login";
		}
	//	model.addAttribute("departmentList", dDao.listAll());
		return "addCategory";
	}
	
	@RequestMapping(value = "/postSupportCategory", method = RequestMethod.POST)
	public String postSupportCategory(@ModelAttribute SupportCategory category, Model model, HttpSession session) {
		if(StringUtils.isEmpty((CharSequence) session.getAttribute("activeuser"))) {
			model.addAttribute("error", "Please Sign in!!!");
			return "login";
		}
		cDao.saveCategory(category);
		model.addAttribute("categoryName", category.getCategoryName());
		model.addAttribute("categoryDescription", category.getCategoryDescription());
		return "postCategory";
	}
	
	@RequestMapping(value = "/adddepartment", method = RequestMethod.GET)
	public String addDepartment(HttpSession session, Model model) {
		if(StringUtils.isEmpty((CharSequence) session.getAttribute("activeuser"))) {
			model.addAttribute("error", "Please Sign in!!!");
			return "login";
		}
		return "addDepartment";
	}
	
	@RequestMapping(value = "/postDepartment", method = RequestMethod.POST)
	public String postDepartment(@ModelAttribute Department department, HttpSession session, Model model) {
		if(StringUtils.isEmpty((CharSequence) session.getAttribute("activeuser"))) {
			model.addAttribute("error", "Please Sign in!!!");
			return "login";
		}
		dDao.saveDepartment(department);
		model.addAttribute("department", department);
		return "postDepartment";
	}
	
	@RequestMapping(value = "{id}/upload", method = RequestMethod.POST)
	public String uploadPhoto(@RequestParam("file") MultipartFile file,@PathVariable("id") int id, Model model, HttpSession session) throws IOException, InterruptedException {
		if(StringUtils.isEmpty((CharSequence) session.getAttribute("activeuser"))) {
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
			FileOutputStream out = new FileOutputStream("E:\\STS Workspace\\SupportManagementSystem\\src\\main\\webapp\\resources\\imgs\\"+photoName);
			out.write(file.getBytes());
			out.close();
			person.setPhotoName(photoName);
			pDao.updatePerson(person);
			TimeUnit.SECONDS.sleep(5);
			model.addAttribute("msg","file uploaded successfully!!");
			model.addAttribute("imageName", person.getPhotoName().toString());
			model.addAttribute("fname", person.getFname());
			model.addAttribute("lname",person.getLname());
			model.addAttribute("personList", pDao.listAll());
		}
		else {
			model.addAttribute("msg","Default Picture added!!");
			person.setPhotoName("defaultimage.jpg");
			pDao.updatePerson(person);
			model.addAttribute("imageName", person.getPhotoName());
			model.addAttribute("fname", person.getFname());
			model.addAttribute("lname",person.getLname());
			model.addAttribute("personList", pDao.listAll());
		}
		return "personProfile";
	}
	

	@RequestMapping(value = "listSupportCategory", method = RequestMethod.GET)
	public String listSupportCategory(Model model, HttpSession session) {
		if(StringUtils.isEmpty((CharSequence) session.getAttribute("activeuser"))) {
			model.addAttribute("error", "Please Sign in!!!");
			return "login";
		}
		model.addAttribute("categoryList", cDao.listAll());
		return "categoryList";
	}
	
	@RequestMapping(value = "listDepartment", method = RequestMethod.GET)
	public String listDepartment(Model model, HttpSession session) {
		if(StringUtils.isEmpty((CharSequence) session.getAttribute("activeuser"))) {
			model.addAttribute("error", "Please Sign in!!!");
			return "login";
		}
		model.addAttribute("departmentList", dDao.listAll());
		return "departmentList";
	}
	
	@RequestMapping(value = "listSupportPerson", method = RequestMethod.GET)
	public String listSupportPerson(Model model, HttpSession session) {
		if(StringUtils.isEmpty((CharSequence) session.getAttribute("activeuser"))) {
			model.addAttribute("error", "Please Sign in!!!");
			return "login";
		}
		
		model.addAttribute("personList", pDao.listAll());
		return "personList";
	}
	
	@RequestMapping(value = "{id}/deletePerson", method = RequestMethod.GET)
	public String deletePerson(@PathVariable("id") int id, Model model, HttpSession session) {
		if(StringUtils.isEmpty((CharSequence) session.getAttribute("activeuser"))) {
			model.addAttribute("error", "Please Sign in!!!");
			return "login";
		}
		
		try {
			SupportPersonnel person = pDao.getPersonById(id);
			User u = uDao.getUserBySupportPersonId(id);
			String oldFileName = person.getPhotoName();
			if(!person.getPhotoName().equals("defaultimage.jpg")) {
				try {
					File f = new File("E:\\STS Workspace\\SupportManagementSystem\\src\\main\\webapp\\resources\\imgs\\"+oldFileName);
					f.delete();
				} catch (Exception e) {
					model.addAttribute("personList", pDao.listAll());
					model.addAttribute("msg", "Cannot find photo of this person!!!");
				}
			}
			
			u.setSupportPerson(null);
			uDao.updateUser(u);
			pDao.deletePerson(id);
			uDao.deleteUser(u.getId());
			model.addAttribute("personList", pDao.listAll());
			model.addAttribute("msg", "Success in deleting user !!!");
		} catch (Exception e) {
			model.addAttribute("personList", pDao.listAll());
			model.addAttribute("msg", "Cannot Delete Person having leave record!!! Please delete leave");
		}
		
		
		return "personList";
	}
	
	@RequestMapping(value = "{id}/showSupportPerson", method = RequestMethod.GET)
	public String showSupportPerson(@PathVariable("id") int id, Model model, HttpSession session) {
		if(StringUtils.isEmpty((CharSequence) session.getAttribute("activeuser"))) {
			model.addAttribute("error", "Please Sign in!!!");
			return "login";
		}
		User u = uDao.getUserBySupportPersonId(id);
		model.addAttribute("user", u);
		model.addAttribute("person", pDao.getPersonById(id));
		model.addAttribute("holidayList", hDao.listAll());
		model.addAttribute("leaveList", lDao.listLeaveByPerson(pDao.getPersonById(id)));
		model.addAttribute("exchangeLeaveList", elDao.listExchangeLeavesByPerson(pDao.getPersonById(id)));
		return "personDetails";
	}
	
	@RequestMapping(value = "{id}/editSupportPerson", method = RequestMethod.GET)
	public String editSupportPerson(@PathVariable("id") int id, Model model, HttpSession session) {
		if(StringUtils.isEmpty((CharSequence) session.getAttribute("activeuser"))) {
			model.addAttribute("error", "Please Sign in!!!");
			return "login";
		}
		User user = uDao.getUserBySupportPersonId(id);
		model.addAttribute("user", user);
		model.addAttribute("person", pDao.getPersonById(id));
		model.addAttribute("id", id);
		SupportCategory category = pDao.getPersonById(id).getSupportcategory();
		model.addAttribute("categoryz", category);
		model.addAttribute("categoryList", cDao.listAll());
		model.addAttribute("department", pDao.getPersonById(id).getDepartment());
		model.addAttribute("departmentList", dDao.listAll());
		return "editPerson";
	}
	
	@RequestMapping(value = "{id}/updatePerson", method = RequestMethod.POST)
	public String updateSupportPerson(@ModelAttribute SupportPersonnel person,@ModelAttribute User user, Model model, @PathVariable("id") int id, HttpSession session) {
		if(StringUtils.isEmpty((CharSequence) session.getAttribute("activeuser"))) {
			model.addAttribute("error", "Please Sign in!!!");
			return "login";
		}
		person.setPersonnelId(id);
		person.setPhotoName(pDao.getPersonById(id).getPhotoName());
	
		person.setSupportcategory(cDao.getCategoryFromId(person.getSupportcategory().getId()));
		
		person.setDepartment(dDao.getDepartmentById(person.getDepartment().getId()));
		pDao.updatePerson(person);
		User u = uDao.getUserBySupportPersonId(id);
		u.setSupportPerson(person);
		u.setUsername(user.getUsername());
		uDao.updateUser(u);
		model.addAttribute("personList", pDao.listAll());
		model.addAttribute("msg", "Person and User Edited Successfully!!!");
		return "personList";
	}
	
	@RequestMapping(value = "{id}/changePhoto", method = RequestMethod.GET)
	public String changePersonPhoto(@PathVariable("id")int id, Model model, HttpSession session) {
		if(StringUtils.isEmpty((CharSequence) session.getAttribute("activeuser"))) {
			model.addAttribute("error", "Please Sign in!!!");
			return "login";
		}
		SupportPersonnel person = pDao.getPersonById(id);
		model.addAttribute("supportperson", person);
		return "changePhoto";
	}
	
	@RequestMapping(value = "{id}/updatePhoto", method = RequestMethod.POST)
	public String updatePhoto(@RequestParam("file") MultipartFile file,@PathVariable("id") int id, Model model, HttpSession session) throws IOException, InterruptedException {
		if(StringUtils.isEmpty((CharSequence) session.getAttribute("activeuser"))) {
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
				TimeUnit.SECONDS.sleep(5);
				model.addAttribute("msg","file edited successfully!!");
				model.addAttribute("imageName", person.getPhotoName().toString());
				model.addAttribute("fname", person.getFname());
				model.addAttribute("lname",person.getLname());
				model.addAttribute("personList", pDao.listAll());
			} else {
				FileOutputStream out = new FileOutputStream("E:\\STS Workspace\\SupportManagementSystem\\src\\main\\webapp\\resources\\imgs\\"+photoName);
				out.write(file.getBytes());
				out.close();
				person.setPhotoName(photoName);
				pDao.updatePerson(person);
				TimeUnit.SECONDS.sleep(5);
				model.addAttribute("msg","file edited successfully!!");
				model.addAttribute("imageName", person.getPhotoName().toString());
				model.addAttribute("fname", person.getFname());
				model.addAttribute("lname",person.getLname());
				model.addAttribute("personList", pDao.listAll());
			}
		} else {
			model.addAttribute("msg","Default Picture added!!");
			person.setPhotoName("defaultimage.jpg");
			pDao.updatePerson(person);
			model.addAttribute("imageName", person.getPhotoName());
			model.addAttribute("fname", person.getFname());
			model.addAttribute("lname",person.getLname());
			model.addAttribute("personList", pDao.listAll());
		}
		return "personProfile";
		
	}
	
	//Category add and delete below here 
	
	@RequestMapping(value = "{id}/editSupportCategory", method = RequestMethod.GET)
	public String editSupportCategory(@PathVariable("id")int id, Model model, HttpSession session) {
		if(StringUtils.isEmpty((CharSequence) session.getAttribute("activeuser"))) {
			model.addAttribute("error", "Please Sign in!!!");
			return "login";
		}
		model.addAttribute("category", cDao.getCategoryFromId(id));
		return "editCategory";
	}
	
	
	@RequestMapping(value = "{id}/updateSupportCategory", method = RequestMethod.POST)
	public String updateSupportCategory(@ModelAttribute SupportCategory category,@PathVariable("id")int id, Model model, HttpSession session) {
		if(StringUtils.isEmpty((CharSequence) session.getAttribute("activeuser"))) {
			model.addAttribute("error", "Please Sign in!!!");
			return "login";
		}
		category.setId(id);
		cDao.updateCategory(category);
		model.addAttribute("categoryList", cDao.listAll());
		model.addAttribute("msg", "Edited Successfully!!!");
		return "categoryList";
	}
	
	@RequestMapping(value = "{id}/deleteCategory", method = RequestMethod.GET)
	public String deleteSupportCategory(@PathVariable("id")int id, Model model, HttpSession session) {
		if(StringUtils.isEmpty((CharSequence) session.getAttribute("activeuser"))) {
			model.addAttribute("error", "Please Sign in!!!");
			return "login";
		}
		try {
			cDao.deleteCategory(id);
			model.addAttribute("msg", "Deleted Successfully");
			model.addAttribute("categoryList", cDao.listAll());
			
		}catch (Exception e) {
			model.addAttribute("msg", "Cannot delete category with child person");
			model.addAttribute("categoryList", cDao.listAll());
		}
		return "categoryList";
	}
	
	@RequestMapping(value = "{id}/deleteDepartment", method = RequestMethod.GET)
	public String deleteDepartment(@PathVariable("id")int id, Model model, HttpSession session) {
		if(StringUtils.isEmpty((CharSequence) session.getAttribute("activeuser"))) {
			model.addAttribute("error", "Please Sign in!!!");
			return "login";
		}
		try {
			dDao.deleteDepartment(id);
			model.addAttribute("msg", "Deleted Successfully");
			model.addAttribute("departmentList", dDao.listAll());
		
		}catch (Exception e) {
			model.addAttribute("msg", "Cannot delete department with child person");
			model.addAttribute("departmentList", dDao.listAll());
		}
		return "departmentList";
	}
	
	@RequestMapping(value = "{id}/editDepartment", method = RequestMethod.GET)
	public String editDepartment(@PathVariable("id")int id, Model model, HttpSession session) {
		if(StringUtils.isEmpty((CharSequence) session.getAttribute("activeuser"))) {
			model.addAttribute("error", "Please Sign in!!!");
			return "login";
		}
		model.addAttribute("department", dDao.getDepartmentById(id));
		return "editDepartment";
	}
	
	@RequestMapping(value = "{id}/updateDepartment", method = RequestMethod.POST)
	public String updateDepartment(@ModelAttribute Department department, @PathVariable("id")int id, Model model, HttpSession session) {
		if(StringUtils.isEmpty((CharSequence) session.getAttribute("activeuser"))) {
			model.addAttribute("error", "Please Sign in!!!");
			return "login";
		}
		department.setId(id);
		dDao.updateDepartment(department);
		model.addAttribute("departmentList", dDao.listAll());
		model.addAttribute("msg", "Edited Successfully!!!");
		return "departmentList";
	}
	
	
	//Regional CRUD below
	
	@RequestMapping(value = "/addRegionPerson", method = RequestMethod.GET)
	public String addRegionalSupport(Model model, HttpSession session) {
		if(StringUtils.isEmpty((CharSequence) session.getAttribute("activeuser"))) {
			model.addAttribute("error", "Please Sign in!!!");
			return "login";
		}
		model.addAttribute("regionList", rDao.listAllRegion());
		return "addRegSupport";
	}
	
	@RequestMapping(value = "/postRegionPerson", method = RequestMethod.POST)
	public String postRegionalSupport(@ModelAttribute SupportRegPerson person, Model model, HttpSession session) {
		if(StringUtils.isEmpty((CharSequence) session.getAttribute("activeuser"))) {
			model.addAttribute("error", "Please Sign in!!!");
			return "login";
		}
		person.setRegion(rDao.getRegionById(person.getRegion().getId()));
		rpDao.saveRegPerson(person);
		model.addAttribute("person", person);
		return "upload";
	}
	
	
	@RequestMapping(value = "{id}/uploadReg", method = RequestMethod.POST)
	public String uploadPhotoReg(@RequestParam("file") MultipartFile file,@PathVariable("id")int id,Model model, HttpSession session) throws IOException, InterruptedException {
		if(StringUtils.isEmpty((CharSequence) session.getAttribute("activeuser"))) {
			model.addAttribute("error", "Please Sign in!!!");
			return "login";
		}
		String fileName = rpDao.getRandomFileName(10);
		System.out.println(fileName);
		String extension = FilenameUtils.getExtension(file.getOriginalFilename().toString());
		System.out.println(extension);
		String photoName = fileName + "."+extension;
		SupportRegPerson person = rpDao.getPersonById(id);
		if(!file.isEmpty()) {
			FileOutputStream out = new FileOutputStream("E:\\STS Workspace\\SupportManagementSystem\\src\\main\\webapp\\resources\\imgs\\"+photoName);
			out.write(file.getBytes());
			out.close();
			person.setPhotoName(photoName);
			rpDao.updateRegPerson(person);
			TimeUnit.SECONDS.sleep(5);
			model.addAttribute("msg","file uploaded successfully!!");
			model.addAttribute("person", person);
			model.addAttribute("personList",rpDao.listAll());
		} else {
			person.setPhotoName("defaultimage.jpg");
			rpDao.updateRegPerson(person);
			model.addAttribute("msg","Default Picture Added!!");
			model.addAttribute("person", person);
			model.addAttribute("personList",rpDao.listAll());
		}
		
		return "regPersonProfile";
	}
	
	
	@RequestMapping(value = "/listRegionPerson", method = RequestMethod.GET)
	public String listRegPerson(Model model, HttpSession session) {
		if(StringUtils.isEmpty((CharSequence) session.getAttribute("activeuser"))) {
			model.addAttribute("error", "Please Sign in!!!");
			return "login";
		}
		model.addAttribute("personList", rpDao.listAll());
		return "regPersonList";
	}
	
	@RequestMapping(value = "{id}/showRegionPerson", method = RequestMethod.GET)
	public String showRegionPerson(@PathVariable("id")int id, Model model, HttpSession session) {
		if(StringUtils.isEmpty((CharSequence) session.getAttribute("activeuser"))) {
			model.addAttribute("error", "Please Sign in!!!");
			return "login";
		}
		model.addAttribute("person", rpDao.getPersonById(id));
		model.addAttribute("leaveList", lDao.listLeaveByRegPerson(rpDao.getPersonById(id)));
		model.addAttribute("holidayList", hDao.listAll());
		return "regPersonDetails";
	}
	
	@RequestMapping(value = "{id}/deleteRegionPerson", method = RequestMethod.GET)
	public String deleteRegPerson(@PathVariable("id")int id, Model model, HttpSession session) {
		if(StringUtils.isEmpty((CharSequence) session.getAttribute("activeuser"))) {
			model.addAttribute("error", "Please Sign in!!!");
			return "login";
		}
		try {
			SupportRegPerson person = rpDao.getPersonById(id);
			String oldFileName = person.getPhotoName();
			if(!person.getPhotoName().equals("defaultimage.jpg")) {
				try {
					File f = new File("E:\\STS Workspace\\SupportManagementSystem\\src\\main\\webapp\\resources\\imgs\\"+oldFileName);
					f.delete();
				} catch (Exception e) {
					model.addAttribute("personList", rpDao.listAll());
					model.addAttribute("msg", "Cannot find photo of this person!!!");
				}
			}
			rpDao.deleteRegPerson(id);
			model.addAttribute("personList", rpDao.listAll());
		}catch (Exception e) {
			model.addAttribute("personList", rpDao.listAll());
			model.addAttribute("msg", "Cannot delete Person. Please delete leaves associated with the person!!");
		}
		
		return "regPersonList";
	}
	
	@RequestMapping(value = "{id}/editRegionPerson", method = RequestMethod.GET)
	public String editRegPerson(@PathVariable("id")int id, Model model, HttpSession session) {
		if(StringUtils.isEmpty((CharSequence) session.getAttribute("activeuser"))) {
			model.addAttribute("error", "Please Sign in!!!");
			return "login";
		}
		//User user = uDao.getUserBySupportRegPersonId(id);
		//model.addAttribute("user", user);
		SupportRegPerson person = rpDao.getPersonById(id);
		//model.addAttribute("person", rpDao.getPersonById(id));
		SupportRegion region = person.getRegion();
		model.addAttribute("person", person);
		model.addAttribute("regions", region);	
		model.addAttribute("regionList", rDao.listAllRegion());
		return "editRegPerson";
	}
	
	@RequestMapping(value = "{id}/updateRegionPerson", method = RequestMethod.POST)
	public String updateRegPerson(@ModelAttribute SupportRegPerson person, @PathVariable("id")int id,Model model, HttpSession session) {
		if(StringUtils.isEmpty((CharSequence) session.getAttribute("activeuser"))) {
			model.addAttribute("error", "Please Sign in!!!");
			return "login";
		}
		
		person.setId(id);
		person.setPhotoName(rpDao.getPersonById(id).getPhotoName());
		person.setRegion(rDao.getRegionById(person.getRegion().getId()));
		rpDao.updateRegPerson(person);
		model.addAttribute("personList", rpDao.listAll());
		return "regPersonList";
	}
	
	@RequestMapping(value = "{id}/changeRegPhoto", method = RequestMethod.GET)
	public String changeRegPersonPhoto(@PathVariable("id")int id, Model model, HttpSession session) {
		if(StringUtils.isEmpty((CharSequence) session.getAttribute("activeuser"))) {
			model.addAttribute("error", "Please Sign in!!!");
			return "login";
		}
		SupportRegPerson person = rpDao.getPersonById(id);
		model.addAttribute("supportperson", person);
		return "changeRegPhoto";
	}
	
	@RequestMapping(value = "{id}/updateRegPhoto", method = RequestMethod.POST)
	public String updateRegPhoto(@RequestParam("file") MultipartFile file,@PathVariable("id") int id, Model model, HttpSession session) throws IOException, InterruptedException {
		if(StringUtils.isEmpty((CharSequence) session.getAttribute("activeuser"))) {
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
				TimeUnit.SECONDS.sleep(5);
				model.addAttribute("person", person);
				model.addAttribute("msg","file edited successfully!!");
				model.addAttribute("imageName", person.getPhotoName().toString());
				model.addAttribute("fname", person.getFname());
				model.addAttribute("lname",person.getLname());
				model.addAttribute("personList", rpDao.listAll());
			} else {
				FileOutputStream out = new FileOutputStream("E:\\STS Workspace\\SupportManagementSystem\\src\\main\\webapp\\resources\\imgs\\"+photoName);
				out.write(file.getBytes());
				out.close();
				person.setPhotoName(photoName);
				rpDao.updateRegPerson(person);
				TimeUnit.SECONDS.sleep(5);
				model.addAttribute("msg","file edited successfully!!");
				model.addAttribute("person", person);
				model.addAttribute("imageName", person.getPhotoName().toString());
				model.addAttribute("fname", person.getFname());
				model.addAttribute("lname",person.getLname());
				model.addAttribute("personList", rpDao.listAll());
			}
		} else {
			model.addAttribute("msg","Default Picture added!!");
			person.setPhotoName("defaultimage.jpg");
			rpDao.updateRegPerson(person);
			model.addAttribute("person", person);
			model.addAttribute("imageName", person.getPhotoName());
			model.addAttribute("fname", person.getFname());
			model.addAttribute("lname",person.getLname());
			model.addAttribute("personList", rpDao.listAll());
		}
		return "regPersonProfile";
	}
	
	@RequestMapping(value = "deleteLeave/{id}/{lid}", method = RequestMethod.GET)
	public String deleteLeave(@PathVariable("id")int id, @PathVariable("lid")int lid, Model model, HttpSession session) {
		if(StringUtils.isEmpty((CharSequence) session.getAttribute("activeuser"))) {
			model.addAttribute("error", "Please Sign in!!!");
			return "login";
		}
		lDao.deleteLeave(lid);
		model.addAttribute("person", pDao.getPersonById(id));
		model.addAttribute("holidayList", hDao.listAll());
		model.addAttribute("leaveList", lDao.listLeaveByPerson(pDao.getPersonById(id)));
		return "personDetails";
	}
	
	@RequestMapping(value = "deleteRegLeave/{id}/{lid}", method = RequestMethod.GET)
	public String deleteRegLeave(@PathVariable("id")int id, @PathVariable("lid")int lid, Model model, HttpSession session) {
		if(StringUtils.isEmpty((CharSequence) session.getAttribute("activeuser"))) {
			model.addAttribute("error", "Please Sign in!!!");
			return "login";
		}
		lDao.deleteLeave(lid);
		model.addAttribute("person", rpDao.getPersonById(id));
		model.addAttribute("holidayList", hDao.listAll());
		model.addAttribute("leaveList", lDao.listLeaveByRegPerson(rpDao.getPersonById(id)));
		return "regPersonDetails";
	}
	
	@RequestMapping(value = "{id}/changePasswordByAdmin", method = RequestMethod.GET)
	public String changePasswordByAdmin(@PathVariable("id")int id, Model model, HttpSession session) {
		if(StringUtils.isEmpty((CharSequence) session.getAttribute("activeuser"))) {
			model.addAttribute("error", "Please Sign in!!!");
			return "login";
		}
		User u = uDao.getUserById(id);
		model.addAttribute("user", u);
		return "changePassword";
	}
	
	@RequestMapping(value = "{id}/changeRegPasswordByAdmin", method = RequestMethod.GET)
	public String changeRegPasswordByAdmin(@PathVariable("id")int id, Model model, HttpSession session) {
		if(StringUtils.isEmpty((CharSequence) session.getAttribute("activeuser"))) {
			model.addAttribute("error", "Please Sign in!!!");
			return "login";
		}
		User u = uDao.getUserBySupportRegPersonId(id);
		model.addAttribute("user", u);
		return "changeRegPassword";
	}
	
	@RequestMapping(value = "passwordChangeByAdmin", method = RequestMethod.POST)
	public String postchangePasswordByAdmin(@ModelAttribute User user,@RequestParam("newpass")String newPassword, @RequestParam("conpass")String conformPassword, HttpSession session, Model model) {
		if(StringUtils.isEmpty((CharSequence) session.getAttribute("activeuser"))) {
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
			return "changePassword";
		} 
		if(!newPassword.equals(conformPassword)) {
			model.addAttribute("msg", "Passwords do not match");
			model.addAttribute("newpass", newPassword);
			model.addAttribute("conpass", conformPassword);
			return "changePassword";
		}if(user.getPassword().equals(encNewPassword)) {
			model.addAttribute("msg", "New password cannot be same as old password");
			model.addAttribute("newpass", newPassword);
			model.addAttribute("conpass", conformPassword);
			return "changePassword";
		}
		u.setPassword(DigestUtils.md5DigestAsHex(newPassword.getBytes()));
		uDao.updateUser(u);
		model.addAttribute("msg", "Password Changed Successfully!!");
		model.addAttribute("personList", pDao.listAll());
		return "personList";
	}
	
	@RequestMapping(value = "passwordRegChangeByAdmin", method = RequestMethod.POST)
	public String postchangeRegPasswordByAdmin(@ModelAttribute User user,@RequestParam("newpass")String newPassword, @RequestParam("conpass")String conformPassword, HttpSession session, Model model) {
		if(StringUtils.isEmpty((CharSequence) session.getAttribute("activeuser"))) {
			model.addAttribute("error", "Please Sign in!!!");
			return "login";
		}
		User u = uDao.getUserById(user.getId());
		user.setPassword(DigestUtils.md5DigestAsHex(user.getPassword().getBytes()));
		if(!user.getPassword().equals(u.getPassword())) {
			model.addAttribute("newpass", newPassword);
			model.addAttribute("conpass", conformPassword);
			model.addAttribute("msg", "Password Invalid!!!");
			return "changeRegPassword";
		} 
		if(!newPassword.equals(conformPassword)) {
			model.addAttribute("msg", "Passwords do not match");
			model.addAttribute("newpass", newPassword);
			model.addAttribute("conpass", conformPassword);
			return "changeRegPassword";
		}
		u.setPassword(DigestUtils.md5DigestAsHex(newPassword.getBytes()));
		uDao.updateUser(u);
		model.addAttribute("msg", "Password Changed Successfully!!");
		model.addAttribute("personList", rpDao.listAll());
		return "regPersonList";
	}
	
	@RequestMapping(value = "{id}/changeAdminPassword", method = RequestMethod.GET)
	public String changeAdministratorPassword(@PathVariable("id")int id, Model model, HttpSession session) {
		if(StringUtils.isEmpty((CharSequence) session.getAttribute("activeuser"))) {
			model.addAttribute("error", "Please Sign in!!!");
			return "login";
		}
		User u = uDao.getUserById(id);
		model.addAttribute("user", u);
		return "changeAdminPassword";
	}
	
	@RequestMapping(value = "updateAdminPassword", method = RequestMethod.POST)
	public String updateAdministratorPassword(@ModelAttribute User user, @RequestParam("newpass")String newPassword, @RequestParam("conpass")String conformPassword, Model model, HttpSession session) {
		if(StringUtils.isEmpty((CharSequence) session.getAttribute("activeuser"))) {
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
	
	@RequestMapping(value = "deleteExchangeLeaveByAdmin/{id}/{elid}", method = RequestMethod.GET)
	public String deleteExchangeLeaveByAdmin(@PathVariable("id")int id, @PathVariable("elid") int elid, Model model, HttpSession session) {
		User u = uDao.getUserBySupportPersonId(id);
		elDao.delete(elid);
		model.addAttribute("person", u.getSupportPerson());
		model.addAttribute("holidayList", hDao.listAll());
	    model.addAttribute("leaveList", lDao.listLeaveByPerson(u.getSupportPerson()));
		model.addAttribute("exchangeLeaveList", elDao.listExchangeLeavesByPerson(u.getSupportPerson()));
		return "personDetails";
	}
	
	
	
	
}
