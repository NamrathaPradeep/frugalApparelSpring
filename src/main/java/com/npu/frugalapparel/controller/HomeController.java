package com.npu.frugalapparel.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.npu.dao.CategoryProductDAO;
import com.npu.domain.Category;
import com.npu.domain.Product;
import com.npu.domain.User;
import com.npu.exceptions.CategoryProductCheckedExcptn;
import com.npu.service.CategoryProductService;
import com.npu.service.LoginService;

/**
 * Handles requests for the application home page. Use URL:
 * http://localhost:8080/courseapp OR http://localhost:8080/courseapp/home
 */
@Controller
public class HomeController {

	private static final Logger logger = LoggerFactory
			.getLogger(HomeController.class);

	@Autowired
	LoginService loginService;

	@Autowired
	CategoryProductService categoryProductService;

	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = { "/", "home" }, method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		return "home";
	}
	
	@RequestMapping(value = { "/", "userlogin" }, method = RequestMethod.GET)
	public String userLogin(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		return "userlogin";
	}


	@RequestMapping(value = "/homepage", method = RequestMethod.GET)
	public ModelAndView login(HttpServletRequest request,
			HttpServletResponse response) throws CategoryProductCheckedExcptn {
		ModelAndView modelView = null;
		
		List<Category> categoryFromDb = categoryProductService.getCategories();
		Map<Integer, List<Product>> categoryProductMap = categoryProductService.getCategoryProductMap(categoryFromDb);

		modelView = new ModelAndView("homepage");
		modelView.addObject("categoryFromDb", categoryFromDb);
		modelView.addObject("categoryProductMap", categoryProductMap);

		return modelView;
	}

}
