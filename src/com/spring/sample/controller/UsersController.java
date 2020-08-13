package com.spring.sample.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.MessageSource;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.spring.sample.entity.User;
import com.spring.sample.model.UserModel;
import com.spring.sample.service.UserService;
import com.spring.sample.validator.UserValidator;

@Controller
//@RequestMapping("/users")
public class UsersController {
	private static final Logger logger = LoggerFactory.getLogger(RegisterController.class);

	@Autowired
	MessageSource messageSource;

	@Autowired
	UserValidator userValidator;

	@Autowired
	@Qualifier("userService")
	UserService userService;

	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		binder.addValidators(userValidator);
	}

	@ModelAttribute("user")
	public UserModel user(@PathVariable(required = false) Integer id) {
		if (id == null) {
			return new UserModel();
		} else {
			logger.info("Fetching user(" + id + ") info from database");
			User user = userService.findUser(id);
			return UserModel.build(user);
		}
	}

	@ModelAttribute("users")
	public List<UserModel> users() {
		List<User> userList = userService.findAll();
		List<UserModel> users = new ArrayList<UserModel>();
		for (User user : userList) {
			users.add(UserModel.build(user));
		}
		return users;
	}

	@GetMapping("/users")
	public String index(Locale locale, Model model) {
		return "user.index";
	}

	@GetMapping(value = "/users/add")
	public String add(Locale locale, Model model) {
		return "user.add";
	}

	@PostMapping("/users")
	public String create(@ModelAttribute("user") @Validated UserModel userModel, BindingResult bindingResult,
			Model model, final RedirectAttributes redirectAttributes, HttpServletRequest request) throws Exception {
		if (bindingResult.hasErrors()) {
			logger.info("Returning register.jsp page, validate failed");
			return "user.add";
		}
		User user = userService.addUser(userModel.make());
		// Add message to flash scope
		redirectAttributes.addFlashAttribute("css", "success");
		redirectAttributes.addFlashAttribute("flash", "user.create.success");
		return "redirect: " + request.getContextPath() + "/users/" + user.getId();
	}

	@GetMapping(value = "/users/{id}")
	public String show(@ModelAttribute("user") UserModel userModel, Model model) {
		return "user.show";
	}

	@GetMapping(value = "/users/{id}/edit")
	public String edit(@ModelAttribute("user") UserModel userModel, Model model) {
		return "user.edit";
	}

	@PutMapping(value = "/users/{id}")
	public String update(@ModelAttribute("user") @Validated UserModel user, BindingResult bindingResult, Model model,
			final RedirectAttributes redirectAttributes, HttpServletRequest request) {
		if (bindingResult.hasErrors()) {
			logger.info("Returning edit.jsp page, validate failed");
			return "user.edit";
		}
		// Add message to flash scope
		redirectAttributes.addFlashAttribute("css", "success");
		redirectAttributes.addFlashAttribute("flash", "user.create.success");
		return "redirect: " + request.getContextPath() + "/users/1";
	}

	@DeleteMapping(value = "/users/{id}", produces = "application/json")
	public String destroy(@ModelAttribute("user") UserModel abc, Model model, HttpServletRequest request) {
		return "redirect: " + request.getContextPath() + "/users";
	}

}
