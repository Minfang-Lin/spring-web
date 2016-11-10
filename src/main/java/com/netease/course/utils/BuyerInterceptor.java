package com.netease.course.utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.netease.course.model.User;

public class BuyerInterceptor implements HandlerInterceptor {

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception e)
			throws Exception {

	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		User currentUser = (User) request.getSession().getAttribute("CurrectUser");
		if (currentUser != null)
			modelAndView.addObject(currentUser);
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		User currentUser = (User) request.getSession().getAttribute("CurrectUser");
		if (currentUser == null) {
			response.sendRedirect("/login");
		} else if (currentUser.getUsertype() != 0) {
			response.sendRedirect("/");
		}
		return true;
	}

}
