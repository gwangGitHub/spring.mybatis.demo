package com.gwang.spring.mybatis.demo.controller.account;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gwang.spring.mybatis.demo.domain.Role;
import com.gwang.spring.mybatis.demo.service.RoleService;

@Controller
@RequestMapping("/role")
public class RoleController {

	@Autowired
	private RoleService roleService;
	
	@RequestMapping("/create")
	@ResponseBody
	public Map<String, Object> register (String name, int code) {
		ModelMap model = new ModelMap();
		try {
			Role role = new Role();
			role.setCode(code);
			role.setName(name);
			roleService.createRole(role);
			return model.addAttribute("success", true);
		} catch (Exception e) {
			e.printStackTrace();
			return model.addAttribute("success", false);
		}
	}
	
	@RequestMapping("/delete")
	@ResponseBody
	public Map<String, Object> register (long id) {
		ModelMap model = new ModelMap();
		try {
			roleService.deleteRole(id);
			return model.addAttribute("success", true);
		} catch (Exception e) {
			e.printStackTrace();
			return model.addAttribute("success", false);
		}
	}
	
	@RequestMapping("/batch")
	@ResponseBody
	public Map<String, Object> getRolesByIds (String ids) {
		ModelMap model = new ModelMap();
		try {
			String [] idsStr = ids.split(",");
			List<Long> idList = new ArrayList<Long>();
			for (String id : idsStr) {
				idList.add(Long.valueOf(id));
			}
			List<Role> roles = roleService.getRoleByIds(idList);
			model.addAttribute("roles", roles);
			return model.addAttribute("success", true);
		} catch (Exception e) {
			e.printStackTrace();
			return model.addAttribute("success", false);
		}
	}
}
