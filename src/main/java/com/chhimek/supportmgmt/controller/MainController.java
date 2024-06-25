package com.chhimek.supportmgmt.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.chhimek.supportmgmt.model.Department;
import com.chhimek.supportmgmt.model.QuickLinks;
import com.chhimek.supportmgmt.model.SupportCategory;
import com.chhimek.supportmgmt.model.SupportPersonnel;
import com.chhimek.supportmgmt.model.SupportRegPerson;
import com.chhimek.supportmgmt.model.SupportRegion;
import com.chhimek.supportmgmt.service.BranchDao;
import com.chhimek.supportmgmt.service.CategoryDao;
import com.chhimek.supportmgmt.service.DepartmentDao;
import com.chhimek.supportmgmt.service.PersonnelDao;
import com.chhimek.supportmgmt.service.QuickLinkDao;
import com.chhimek.supportmgmt.service.RegPersonDao;
import com.chhimek.supportmgmt.service.RegionDao;

@Controller
public class MainController {
	
	
	@Autowired
	private CategoryDao cDao;
	
	@Autowired
	private PersonnelDao pDao;
	
	@Autowired
	private RegionDao rDao;
	
	@Autowired
	private RegPersonDao rpDao;
	
	@Autowired
	private QuickLinkDao qDao;
	
	@Autowired
	private DepartmentDao dDao;
	
	@Autowired
	private BranchDao bDao;
	
	@RequestMapping(value = "/support", method = RequestMethod.GET)
	public String supportPage(Model model) {
		//model.addAttribute("categoryList", cDao.listAll());
		//model.addAttribute("personList", pDao.listAll());
		model.addAttribute("status", 0);
		model.addAttribute("regionList", rDao.listAllRegion());
		model.addAttribute("departmentList", dDao.listAll());
		model.addAttribute("categoryList", cDao.listAllExceptNone(9));
		return "support";
	}
	
	
	@RequestMapping(value = "{id}/getDepartment", method = RequestMethod.GET)
	public String filterCategory(@PathVariable("id")int id, Model model) {
		model.addAttribute("categoryList", cDao.listAllExceptNone(9));
		Department department = dDao.getDepartmentById(id);
		model.addAttribute("catRegName", department.getName());
		List<SupportPersonnel> personList = pDao.listByDepartmentId(id);
		pDao.overridePastHoliday();
		//for(SupportPersonnel person: personList) {
			//pDao.overridePastLeave(person);
			//pDao.updatePresentStatus(person);
		//}
		model.addAttribute("personList", personList);
		model.addAttribute("status", 1);
		model.addAttribute("regionList", rDao.listAllRegion());
		model.addAttribute("departmentList", dDao.listAll());
		return "support";
	}
	
	
	@RequestMapping(value = "{id}/getRegion", method = RequestMethod.GET)
	public String filterRegion(@PathVariable("id")int id, Model model) {
		model.addAttribute("regionList", rDao.listAllRegion());
		model.addAttribute("categoryList", cDao.listAllExceptNone(9));
		SupportRegion region = rDao.getRegionById(id);
		List<SupportRegPerson> personList = rpDao.listByRegionId(id);
		/*
		 * rpDao.overridePastHoliday(); for(SupportRegPerson person : personList) {
		 * rpDao.overridePastLeave(person); rpDao.updatePresentStatus(person); }
		 */
		
		model.addAttribute("status", 1);
		model.addAttribute("catRegName", region.getRegion());
		model.addAttribute("regionList", rDao.listAllRegion());
		model.addAttribute("personList", personList);
		model.addAttribute("departmentList", dDao.listAll());
		return "support";
	}
	
	
	@RequestMapping(value = "/supportPageHo", method = RequestMethod.GET)
	public String chhimekSupportPage(Model model) {
	//	List<SupportCategory> categoryList = cDao.listAll();
		List<SupportPersonnel> personList = pDao.listAll();
		
		  pDao.overridePastHoliday(); 
		  for(SupportPersonnel person: personList) {
		  pDao.overridePastLeave(person); 
		  pDao.updatePresentStatus(person);
		  }
		 
		model.addAttribute("categoryList", cDao.listAllExceptNone(9));
		model.addAttribute("regionList", rDao.listAllRegion());
		model.addAttribute("personList", personList);
		model.addAttribute("departmentList", dDao.listAll());
		return "supportPage";
	}
	
	@RequestMapping(value = "/supportPageRg", method = RequestMethod.GET)
	public String chhimekRegSupportPage(Model model) {
		List<SupportRegion> regionList = rDao.listAllRegion();
	
		List<SupportRegPerson> personList = rpDao.listAll();
		/*
		 * rpDao.overridePastHoliday(); for(SupportRegPerson person: personList) {
		 * rpDao.overridePastLeave(person); rpDao.updatePresentStatus(person); }
		 */
		model.addAttribute("regionList", regionList);
		model.addAttribute("personList", personList);
		model.addAttribute("departmentList", dDao.listAll());
		model.addAttribute("categoryList", cDao.listAllExceptNone(9));
		return "supportRegPage";
	}
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String chhimekTestMain(Model model) {
		List<QuickLinks> linkList = qDao.listQuickLinks();
		model.addAttribute("linklist", linkList);
		model.addAttribute("regionList", rDao.listAllRegion());
		model.addAttribute("departmentList", dDao.listAll());
		model.addAttribute("categoryList", cDao.listAllExceptNone(9));
		return "mainPage";
	}
	
	@RequestMapping(value = "{id}/getByRegion", method = RequestMethod.GET)
	public String getByRegion(@PathVariable("id")int id, Model model) {
		SupportRegion region = rDao.getRegionById(id);
		List<SupportRegPerson> personList = rpDao.listByRegionId(id);
		
		model.addAttribute("catRegName", region.getRegion());
		model.addAttribute("personList", personList);
		model.addAttribute("regionList", rDao.listAllRegion());
		model.addAttribute("departmentList", dDao.listAll());
		model.addAttribute("categoryList", cDao.listAllExceptNone(9));
		return "regionPage";
	}
	
	@RequestMapping(value = "{id}/getByDepartment", method = RequestMethod.GET)
	public String getByDepartment(@PathVariable("id")int id, Model model) {
		Department department = dDao.getDepartmentById(id);
		List<SupportPersonnel> personList = pDao.listByDepartmentId(id);
		pDao.overridePastHoliday();
		for(SupportPersonnel person: personList) {
			  pDao.overridePastLeave(person); 
			  pDao.updatePresentStatus(person);
			  }
		
		model.addAttribute("catRegName", department.getName());
		model.addAttribute("personList", personList);
		model.addAttribute("regionList", rDao.listAllRegion());
		model.addAttribute("departmentList", dDao.listAll());
		model.addAttribute("categoryList", cDao.listAllExceptNone(9));
		return "departmentPage";
	}
	
	@RequestMapping(value = "{id}/getByCategory", method = RequestMethod.GET)
	public String getByCategory(@PathVariable("id")int id, Model model) {
		SupportCategory category = cDao.getCategoryFromId(id);
		List<SupportPersonnel> personList = pDao.listByCategory(category);
		 for(SupportPersonnel person: personList) {
			  pDao.overridePastLeave(person); 
			  pDao.updatePresentStatus(person);
			  }
		
		model.addAttribute("catRegName", category.getCategoryName());
		model.addAttribute("personList", personList);
		model.addAttribute("regionList", rDao.listAllRegion());
		model.addAttribute("departmentList", dDao.listAll());
		model.addAttribute("categoryList", cDao.listAllExceptNone(9));
		return "departmentPage";
	}
	
	@RequestMapping(value = "/listAllBranches", method = RequestMethod.GET)
	public String listAllBranches(Model model) {
		model.addAttribute("msg", "All Branches");
		model.addAttribute("branchList", bDao.listAll());
		model.addAttribute("regionList", rDao.listAllRegion());
		model.addAttribute("departmentList", dDao.listAll());
		model.addAttribute("categoryList", cDao.listAllExceptNone(9));
		return "listAllBranches";
	}
	
	@RequestMapping(value = "{id}/getBranchByRegion", method = RequestMethod.GET)
	public String getBranchByRegion(@PathVariable("id")int id, Model model) {
		model.addAttribute("msg", rDao.getRegionById(id).getRegion());
		model.addAttribute("branchList", bDao.listBranchByRegionId(id));
		model.addAttribute("regionList", rDao.listAllRegion());
		model.addAttribute("departmentList", dDao.listAll());
		model.addAttribute("categoryList", cDao.listAllExceptNone(9));
		return "listAllBranches";
	}
	

}
