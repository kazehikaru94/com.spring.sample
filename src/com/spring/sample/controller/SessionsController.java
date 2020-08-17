package com.spring.sample.controller;

import java.util.List;
import java.util.Locale;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.spring.sample.interceptor.Flash;
import com.spring.sample.model.UserModel;
import com.spring.sample.service.UserService;
import com.spring.sample.validator.UserValidator;

@Controller
@EnableWebMvc
public class SessionsController {
	private static final Logger logger = LoggerFactory.getLogger(RegisterController.class);

	@Autowired
	MessageSource messageSource;

//	@Autowired
//	UserValidator userValidator;

	@Autowired
	@Qualifier("userService")
	UserService userService;

	@Resource
	Flash flash;

	@InitBinder
	protected void initBinder(WebDataBinder binder) {
//		binder.addValidators(userValidator);
	}

//	@ModelAttribute("user")
//	public UserModel user(@PathVariable(required = false) Integer id) {
//		if (id == null) {
//			return new UserModel();
//		} else {
//			logger.info("Fetching user(" + id + ") info from database");
//			UserModel userModel = userService.findUser(id);
//			return userModel;
//		}
//	}

	@GetMapping(value = "/login")
	public String add(Locale locale, Model model) {
		return "sessions/login";
	}

	@PostMapping(value = "/login")
	public String create(@ModelAttribute("user") @Validated UserModel userModel, BindingResult bindingResult,
			Model model, final RedirectAttributes redirectAttributes, HttpServletRequest request) throws Exception {
		if (bindingResult.hasErrors()) {
			logger.info("Returning register.jsp page, validate failed");
			return "users/login";
		}
		UserModel user = userService.addUser(userModel);
		// Add message to flash scope
		redirectAttributes.addFlashAttribute("css", "success");
		redirectAttributes.addFlashAttribute("flash", "user.create.success");
		flash.success("user.create.success");
		flash.keep();
		return "redirect: " + request.getContextPath() + "/users/" + user.getId();
	}

}
