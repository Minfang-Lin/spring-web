package com.netease.course.web.controller;

import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.netease.course.model.User;
import com.netease.course.service.impl.ProductService;
import com.netease.course.service.impl.UserService;

@Controller
@RequestMapping("/api")
public class ApiController {

	@Resource
	private UserService userService;
	@Resource
	private ProductService productService;

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String checkLogin(ModelMap map, HttpSession session, HttpServletResponse response,
			@RequestParam String userName, @RequestParam String password) {
		User user = userService.getUserByAccount(userName, password);
		if (user != null) {
			session.setAttribute("CurrectUser", user);
			response.setStatus(200);
			map.addAttribute(user);
			map.addAttribute("code", response.getStatus());
			map.addAttribute("message", "登录成功");
			map.addAttribute("result", true);
		} else {
			response.setStatus(403);
			map.addAttribute("code", response.getStatus());
			map.addAttribute("message", "用户名或密码错");
			map.addAttribute("result", false);
		}
		return "";
	}

	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public String deleteProductById(ModelMap map, HttpSession session, HttpServletResponse response,
			@RequestParam int id) {
		User user = (User) session.getAttribute("CurrectUser");
		// 未登录用户跳转到登录界面，非卖家用户跳转到首页
		if (user == null) {
			return "redirect:/login";
		} else if (user.getUsertype() != 1) {
			return "redirect:/";
		}
		if (productService.deleteProductInfoById(id)) {
			response.setStatus(200);
			map.addAttribute("code", response.getStatus());
			map.addAttribute("message", "删除成功");
			map.addAttribute("result", true);
		} else {
			response.setStatus(403);
			map.addAttribute("code", response.getStatus());
			map.addAttribute("message", "删除失败");
			map.addAttribute("result", false);
		}
		return "";
	}

	@RequestMapping(value = "/buy", method = RequestMethod.POST)
	public String buyProductById(ModelMap map, HttpSession session, HttpServletResponse response,
			@RequestParam int id) {
		User user = (User) session.getAttribute("CurrectUser");
		// 未登录用户跳转到登录界面，非买家用户跳转到首页
		if (user == null) {
			return "redirect:/login";
		} else if (user.getUsertype() != 0) {
			return "redirect:/";
		}
		int personId = user.getId();
		long time = new Date().getTime();
		if (productService.insertPurchase(id, personId, time)) {
			response.setStatus(200);
			map.addAttribute("code", response.getStatus());
			map.addAttribute("message", "购买成功");
			map.addAttribute("result", true);
		} else {
			response.setStatus(403);
			map.addAttribute("code", response.getStatus());
			map.addAttribute("message", "购买失败");
			map.addAttribute("result", false);
		}
		return "";
	}
}
