package com.npu.frugalapparel.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.npu.domain.Category;
import com.npu.domain.Product;
import com.npu.domain.User;
import com.npu.exceptions.CategoryProductCheckedExcptn;
import com.npu.service.CategoryProductService;
import com.npu.service.LoginService;

@Controller
public class RegisterController {

	@Autowired
	LoginService loginService;

	@Autowired
	CategoryProductService categoryProductService;

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public ModelAndView registerUser(HttpServletRequest request,
			HttpServletResponse response) throws CategoryProductCheckedExcptn {

		ModelAndView modelView = null;

		String userId = request.getParameter("user_id");
		String password = request.getParameter("passcode");
		String firstName = request.getParameter("first_name");
		String lastName = request.getParameter("last_name");
		String emailId = request.getParameter("email_id");
		String userStreet = request.getParameter("street");
		String aptNo = request.getParameter("apt_no");
		String userCity = request.getParameter("city");
		String userState = request.getParameter("state");
		String zip = request.getParameter("zip");

		int zipCode = 0;
		zipCode = Integer.parseInt(request.getParameter("zip"));

		User user = new User(firstName, lastName, emailId, password, userId,
				userStreet, aptNo, userCity, userState, zipCode);

		loginService.insertUser(user);
		
		HttpSession session = request.getSession();
		session.setAttribute("userid", userId);
		
		List<Category> categoryFromDb = categoryProductService.getCategories();
		Map<Integer, List<Product>> categoryProductMap = categoryProductService.getCategoryProductMap(categoryFromDb);
		
		modelView = new ModelAndView("homepage");
		modelView.addObject("categoryFromDb", categoryFromDb);
		modelView.addObject("categoryProductMap", categoryProductMap);
		
		return modelView;

	}

}
