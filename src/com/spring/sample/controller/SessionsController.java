package com.spring.sample.controller;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.spring.sample.model.UserModel;
import com.spring.sample.service.UserService;

@Controller
@EnableWebMvc
public class SessionsController {
	private static final Logger logger = LoggerFactory.getLogger(SessionsController.class);

	@Autowired
	@Qualifier("userService")
	UserService userService;

	@GetMapping(value = "/login")
	public String add(Locale locale, Model model) {
		logger.info("Requesting login form");
		return "sessions/login";
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String register(@RequestParam String email, @RequestParam String password, HttpServletRequest request,
			Model model) {

		logger.info("login form email: " + email);
		logger.info("login form password: " + password);
		UserModel userModel = userService.findUserByEmail(email);
		if (userModel != null && password.equals(userModel.getPassword())) {
			logger.info("login success");
			request.getSession().setAttribute("user", userModel);
			return "redirect: " + request.getContextPath() + "/users";
		} else {
			model.addAttribute("email", email);
			return "sessions/login";
		}

	}
}