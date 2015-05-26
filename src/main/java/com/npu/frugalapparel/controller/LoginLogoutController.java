package com.npu.frugalapparel.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.npu.domain.Category;
import com.npu.domain.Product;
import com.npu.exceptions.CategoryProductCheckedExcptn;
import com.npu.service.CategoryProductService;
import com.npu.service.LoginService;

@Controller
public class LoginLogoutController {

	@Autowired
	LoginService loginService;
	
	@Autowired
	CategoryProductService categoryProductService;

	private static final Logger logger = LoggerFactory
			.getLogger(LoginLogoutController.class);

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ModelAndView login(HttpServletRequest request,
			HttpServletResponse response) throws CategoryProductCheckedExcptn {
		ModelAndView modelView = null;

		String userId = request.getParameter("user_id");
		String password = request.getParameter("passcode");

		boolean validUser = loginService.authenticateUser(userId, password);
	
		if (!validUser) {
			// send to login page with error message
			modelView = new ModelAndView("userlogin");
			modelView.addObject("message",
					"Please enter a valid username and password");

		} else {

			HttpSession session = request.getSession();
			session.setAttribute("userid", userId);

			List<Category> categoryFromDb = categoryProductService.getCategories();
			Map<Integer, List<Product>> categoryProductMap = categoryProductService.getCategoryProductMap(categoryFromDb);

			modelView = new ModelAndView("homepage");
			modelView.addObject("categoryFromDb", categoryFromDb);
			modelView.addObject("categoryProductMap", categoryProductMap);
		}

		return modelView;
	}
	
	@RequestMapping(value = "/logout")
	public String logout(HttpSession session) {

		session.invalidate();
		return "redirect:/userlogin";
	}
}
