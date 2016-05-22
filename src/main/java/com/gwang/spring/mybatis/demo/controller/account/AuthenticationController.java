package com.gwang.spring.mybatis.demo.controller.account;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class AuthenticationController {
	
	@RequestMapping("/registerPage")
	public String registerPage () {
		return "account/register";
	}
	
	@RequestMapping("/register")
	@ResponseBody
	public Map<String, Object> register (String name, int age) {
		ModelMap model = new ModelMap();
		model.addAttribute("success", true);
		model.addAttribute("name", name);
		model.addAttribute("age", age);
		return model;
	}

}
