package com.chhimek.supportmgmt.controller;

import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.chhimek.supportmgmt.model.QuickLinks;
import com.chhimek.supportmgmt.service.QuickLinkDao;

@Controller
public class QuickLinkController {

	@Autowired
	private QuickLinkDao qDao;
	
	@RequestMapping(value = "/addquicklink", method = RequestMethod.GET)
	public String addQuickLinks(Model model, HttpSession session) {
		if(StringUtils.isEmpty((CharSequence) session.getAttribute("activeuser"))) {
			model.addAttribute("error", "Please Sign in!!!");
			return "login";
		}
		return "addlink";
	}
	
	@RequestMapping(value = "/postquicklink", method = RequestMethod.POST)
	public String postQuickLinks(@ModelAttribute QuickLinks quicklinks, Model model, HttpSession session) {
		if(StringUtils.isEmpty((CharSequence) session.getAttribute("activeuser"))) {
			model.addAttribute("error", "Please Sign in!!!");
			return "login";
		}
		qDao.savelink(quicklinks);
		model.addAttribute("linklist", qDao.listQuickLinks());
		model.addAttribute("msg", "Link Added");
		return "linkList";
	}
	
	@RequestMapping(value = "/listquicklink", method = RequestMethod.GET)
	public String listQuickLinks(Model model, HttpSession session) {
		if(StringUtils.isEmpty((CharSequence) session.getAttribute("activeuser"))) {
			model.addAttribute("error", "Please Sign in!!!");
			return "login";
		}
		model.addAttribute("linklist", qDao.listQuickLinks());
		return "linkList";
	}
	
	@RequestMapping(value = "{id}/deletelink", method = RequestMethod.GET)
	public String deleteLink(@PathVariable("id")int id, Model model, HttpSession session) {
		if(StringUtils.isEmpty((CharSequence) session.getAttribute("activeuser"))) {
			model.addAttribute("error", "Please Sign in!!!");
			return "login";
		}
		qDao.deleteLink(id);
		model.addAttribute("linklist", qDao.listQuickLinks());
		model.addAttribute("msg", "Link Deleted");
		return "linkList";
	}
	
	@RequestMapping(value = "quickLinks", method = RequestMethod.GET)
	public String getQuickLinks(Model model, HttpSession session) {
		model.addAttribute("linklist", qDao.listQuickLinks());
		return "quicklink";
	}
}
