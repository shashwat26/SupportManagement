package com.chhimek.supportmgmt.controller;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpSession;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.chhimek.supportmgmt.model.Branch;
import com.chhimek.supportmgmt.model.SupportRegion;
import com.chhimek.supportmgmt.service.BranchDao;
import com.chhimek.supportmgmt.service.RegionDao;
import com.opencsv.CSVParser;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

@Controller
public class BranchController {
	
	@Autowired
	private RegionDao rDao;
	
	@Autowired
	private BranchDao bDao;
	
	@RequestMapping(value = "/addBranch", method = RequestMethod.GET)
	public String addBranch(HttpSession session, Model model) {
		if(StringUtils.isEmpty((CharSequence) session.getAttribute("activeuser"))) {
			model.addAttribute("error", "Please Sign in!!!");
			return "login";
		}
		model.addAttribute("regionList", rDao.listAllRegion());
		return "addBranch";
	}
	
	@RequestMapping(value = "/postBranch", method = RequestMethod.POST)
	public String postBranch(@ModelAttribute Branch branch, HttpSession session, Model model) {
		if(StringUtils.isEmpty((CharSequence) session.getAttribute("activeuser"))) {
			model.addAttribute("error", "Please Sign in!!!");
			return "login";
		}
		SupportRegion region = rDao.getRegionById(branch.getBranchRegion().getId());
		branch.setBranchRegion(region);
		bDao.saveBranch(branch);
		model.addAttribute("branch", branch);
		
		return "branchProfile";
	}
	
	@RequestMapping(value = "/listBranch", method = RequestMethod.GET)
	public String listBranch(HttpSession session, Model model) {
		if(StringUtils.isEmpty((CharSequence) session.getAttribute("activeuser"))) {
			model.addAttribute("error", "Please Sign in!!!");
			return "login";
		}
		model.addAttribute("branchList", bDao.listAll());
		return "branchList";
	}
	
	@RequestMapping(value = "{id}/deleteBranch", method = RequestMethod.GET)
	public String deleteBranch(@PathVariable("id")int id, Model model, HttpSession session) {
		if(StringUtils.isEmpty((CharSequence) session.getAttribute("activeuser"))) {
			model.addAttribute("error", "Please Sign in!!!");
			return "login";
		}
		bDao.deleteBranch(id);
		model.addAttribute("branchList", bDao.listAll());
		return "branchList";
	}
	
	@RequestMapping(value = "{id}/editBranch", method = RequestMethod.GET)
	public String editBranch(@PathVariable("id")int id, Model model, HttpSession session) {
		if(StringUtils.isEmpty((CharSequence) session.getAttribute("activeuser"))) {
			model.addAttribute("error", "Please Sign in!!!");
			return "login";
		}
		model.addAttribute("branch", bDao.getBranchById(id));
		model.addAttribute("regionList", rDao.listAllRegion());
		return "editBranch";
	}
	
	@RequestMapping(value = "{id}/updateBranch", method = RequestMethod.POST)
	public String updateBranch(@ModelAttribute Branch branch, Model model, HttpSession session) {
		if(StringUtils.isEmpty((CharSequence) session.getAttribute("activeuser"))) {
			model.addAttribute("error", "Please Sign in!!!");
			return "login";
		}
		
		branch.setBranchRegion(rDao.getRegionById(branch.getBranchRegion().getId()));
		bDao.updateBranch(branch);
		model.addAttribute("branchList", bDao.listAll());
		return "branchList";
	}
	
	@RequestMapping(value = "updateWithCsv", method = RequestMethod.GET)
	public String addCsvFile(HttpSession session, Model model) {
		if(StringUtils.isEmpty((CharSequence) session.getAttribute("activeuser"))) {
			model.addAttribute("error", "Please Sign in!!!");
			return "login";
		}
		return "uploadCsv";
	}
	
	@RequestMapping(value = "updateBranchByCsv", method = RequestMethod.POST)
	public String updateBranchwithCsv(@RequestParam("file") MultipartFile file, HttpSession session, Model model) throws IOException {
		if(StringUtils.isEmpty((CharSequence) session.getAttribute("activeuser"))) {
			model.addAttribute("error", "Please Sign in!!!");
			return "login";
		}
	
 		String extension = FilenameUtils.getExtension(file.getOriginalFilename().toString());
		if(extension.equals("csv")) {
			try{
				bDao.updateBranchByCsv(file);
				TimeUnit.SECONDS.sleep(5);
			}catch (Exception e) {
				// TODO: handle exception
				model.addAttribute("msg", "Sorry Error on the file!!!");
			}
			
		} else {
			model.addAttribute("msg", "The file you uploaded is not in CSV format!!");
			return "uploadCsv";
		}
		
		model.addAttribute("branchList", bDao.listAll());
		return "branchList";
	}

}
