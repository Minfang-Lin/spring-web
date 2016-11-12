package com.netease.course.web.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
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
	public Map<String, Object> checkLogin(HttpSession session, @RequestParam String userName,
			@RequestParam String password) {
		User user = userService.getUserByAccount(userName, password);
		Map<String, Object> map = new HashMap<String, Object>();
		if (user != null) {
			session.setAttribute("user", user);
			map.put("code", 200);
			map.put("message", "登录成功");
			map.put("result", true);
		} else {
			map.put("code", 403);
			map.put("message", "登录失败");
			map.put("result", false);
		}
		return map;
	}

	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public Map<String, Object> deleteProductById(@RequestParam Integer id) {
		Map<String, Object> map = new HashMap<String, Object>();
		if (productService.deleteProductInfoById(id)) {
			map.put("code", 200);
			map.put("message", "删除成功");
			map.put("result", true);
		} else {
			map.put("code", 403);
			map.put("message", "删除失败");
			map.put("result", false);
		}
		return map;
	}

	@RequestMapping(value = "/buy", method = RequestMethod.POST)
	public Map<String, Object> buyProductById(HttpSession session, @RequestParam Integer id) {
		int personId = ((User) session.getAttribute("user")).getId();
		long time = new Date().getTime();
		Map<String, Object> map = new HashMap<String, Object>();
		if (productService.insertPurchase(id, personId, time)) {
			map.put("code", 200);
			map.put("message", "购买成功");
			map.put("result", true);
		} else {
			map.put("code", 403);
			map.put("message", "购买失败");
			map.put("result", false);
		}
		return map;
	}
}
