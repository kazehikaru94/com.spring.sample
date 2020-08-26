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
import com.spring.sample.model.MicropostModel;
import com.spring.sample.model.UserModel;
import com.spring.sample.service.MicropostService;

@Controller
@RequestMapping("/microposts")
@EnableWebMvc
public class MicropostController {
	private static final Logger logger = LoggerFactory.getLogger(RegisterController.class);

	@Autowired
	MessageSource messageSource;

	@Autowired
	@Qualifier("micropostService")
	MicropostService micropostService;

	@Resource
	Flash flash;


	@ModelAttribute("micropost")
	public MicropostModel user(@PathVariable(required = false) Integer id) {
		if (id == null) {
			return new MicropostModel();
		} else {
			logger.info("Fetching user(" + id + ") info from database");
			MicropostModel micropostModel = micropostService.findMicropost(id);
			return micropostModel;
		}
	}

	@GetMapping
	public String index(Locale locale, Model model, HttpServletRequest request) {
		UserModel userModel = (UserModel)request.getSession().getAttribute("user");
		List<MicropostModel> micropostList = micropostService.findMicropostbyUserId(userModel.getId());
		model.addAttribute("microposts", micropostList);
		return "microposts/index";
	}

//--Implement sau--	

//	@GetMapping(value = "/add")
//	public String add(Locale locale, Model model) {
//		return "users/add";
//	}
//
//	@PostMapping
//	public String create(@ModelAttribute("user") @Validated UserModel userModel, BindingResult bindingResult,
//			Model model, final RedirectAttributes redirectAttributes, HttpServletRequest request) throws Exception {
//		if (bindingResult.hasErrors()) {
//			logger.info("Returning register.jsp page, validate failed");
//			return "users/add";
//		}
//		UserModel user = userService.addUser(userModel);
//		// Add message to flash scope
//		redirectAttributes.addFlashAttribute("css", "success");
//		redirectAttributes.addFlashAttribute("flash", "user.create.success");
//		flash.success("user.create.success");
//		flash.keep();
//		return "redirect: " + request.getContextPath() + "/users/" + user.getId();
//	}
//
	@GetMapping(value = "/{id}")
	public String show(@ModelAttribute("micropost") MicropostModel micropostModel, Model model) {
		return "microposts/show";
//	}
//
//	@GetMapping(value = "/{id}/edit")
//	public String edit(@ModelAttribute("micropost") MicropostModel micropostModel, Model model) {
//		return "users/edit";
//	}
//
//	@PutMapping(value = "/{id}")
//	public String update(@ModelAttribute("user") @Validated UserModel userModel, BindingResult bindingResult,
//			Model model, final RedirectAttributes redirectAttributes, HttpServletRequest request) throws Exception {
//		if (bindingResult.hasErrors()) {
//			logger.info("Returning edit.jsp page, validate failed");
//			return "users/edit";
//		}
//		UserModel user = userService.editUser(userModel);
//		// Add message to flash scope
//		redirectAttributes.addFlashAttribute("css", "success");
//		redirectAttributes.addFlashAttribute("flash", "user.create.success");
//		flash.success("user.update.success");
//		flash.keep();
//		return "redirect: " + request.getContextPath() + "/users/" + user.getId();
//	}
//
//	@DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
//	@ResponseBody
//	public ResponseEntity<String> destroy(@ModelAttribute("user") UserModel userModel, Model model, HttpServletRequest request)
//			throws Exception {
//		logger.info("Deleting user");
////		userService.deleteUser(userModel);
////		return "redirect: " + request.getContextPath() + "/users";
//		return new ResponseEntity<String>("{\"result\" : \"OK\"}", HttpStatus.OK);
////		return "{'result': OK}";
//	}
//
	}
}
