package com.netease.course.web.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.netease.course.model.User;

@Controller
public class BasicController {
	
	@ExceptionHandler({ MissingServletRequestParameterException.class })
	public ModelAndView requestMissingServletRequest(MissingServletRequestParameterException ex, HttpSession session) {
		User user = (User) session.getAttribute("CurrectUser");
		ModelAndView  mav = new ModelAndView("error400");
		mav.addObject("user", user);
		ex.printStackTrace();
		return mav;
	}
	
	@RequestMapping("*")
	public String logout(ModelMap map, HttpSession session) {
		return "error404";
	}
	
}
