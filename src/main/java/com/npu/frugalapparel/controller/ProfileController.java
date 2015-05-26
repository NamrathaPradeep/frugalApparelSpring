package com.npu.frugalapparel.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.npu.dao.UserDAO;
import com.npu.domain.User;
import com.npu.service.LoginService;

@Controller
public class ProfileController {

	@Autowired
	LoginService loginService;

	@RequestMapping(value = "/userprofile")
	public ModelAndView getUserDetails(HttpServletRequest request,
			HttpServletResponse response, HttpSession session) {
		ModelAndView modelView = null;
		String userId = (String) session.getAttribute("userid");

		User user = loginService.getUser(userId);
		session = request.getSession();

		modelView = new ModelAndView("userprofile");
		modelView.addObject("user", user);

		return modelView;
	}

}
