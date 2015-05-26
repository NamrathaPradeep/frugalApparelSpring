package com.npu.frugalapparel.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.npu.domain.CardInfo;
import com.npu.domain.Order;
import com.npu.domain.Product;
import com.npu.domain.User;
import com.npu.service.CategoryProductService;
import com.npu.service.CheckoutService;
import com.npu.service.LoginService;

@Controller
public class CheckoutController {

	@Autowired
	CategoryProductService categoryProductService;

	@Autowired
	CheckoutService checkoutService;

	@Autowired
	LoginService loginService;

	@RequestMapping(value = "/checkout", method = RequestMethod.GET)
	public ModelAndView checkout(HttpServletRequest request,
			HttpServletResponse response, HttpSession session) {
		ModelAndView modelView = null;

		String products = request.getParameter("products");

		List<Product> productsFromDB = categoryProductService
				.getProducts(products);

		modelView = new ModelAndView("checkout");
		modelView.addObject("productsFromDB", productsFromDB);

		return modelView;

	}

	@RequestMapping(value = "/confirm", method = RequestMethod.POST)
	public ModelAndView cofirm(HttpServletRequest request,
			HttpServletResponse response) {

		ModelAndView modelView = null;
		String userId = null;
		HttpSession session = request.getSession(false);
		if (session != null) {
			userId = (String) session.getAttribute("userid");
		}

		String productsIdsInRequest = request.getParameter("products");
		String orderAmount = request.getParameter("orderAmount");
		String cardnumber = request.getParameter("cardnumber");
		String cvvcode = request.getParameter("cvvcode");
		String cardtype = request.getParameter("cardtype");
		String expirationdate = request.getParameter("expirationdate");

		CardInfo cardInfo = new CardInfo(cardnumber, cardtype, expirationdate,
				userId, Integer.parseInt(cvvcode));

		Double amount = Double.parseDouble(orderAmount);

		int orderId = 0;

		try {
			orderId = checkoutService.placeOrder(productsIdsInRequest, amount,
					cardInfo, userId);
		} catch (Exception e) {
			e.printStackTrace();
		}

		Order userOrder = checkoutService.getOrder(orderId);
		User userDet = loginService.getUser(userId);

		modelView = new ModelAndView("confirmation");
		modelView.addObject("orderId", orderId);
		modelView.addObject("userOrder", userOrder);
		modelView.addObject("userDet", userDet);

		return modelView;

	}

}
