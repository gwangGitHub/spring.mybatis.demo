package com.gwang.spring.mybatis.demo.controller.account;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gwang.spring.mybatis.demo.domain.Account;
import com.gwang.spring.mybatis.demo.domain.AccountSearchParam;
import com.gwang.spring.mybatis.demo.service.AccountService;
import com.gwang.spring.mybatis.demo.vo.AccountPage;

@Controller
@RequestMapping("/account")
public class AccountController {
	
	final private Logger logger = LoggerFactory.getLogger(AccountController.class);

	@Autowired
	private AccountService accountService;
	
	@RequestMapping("/page/list")
	public String accountListPage (AccountSearchParam accountSearchParam, Model model, HttpSession session) {
		try {
			AccountPage page = accountService.search(accountSearchParam);
			//session中放数据
			session.setAttribute("total", page.getTotal());
			model.addAttribute("page", page);
			logger.info("acccount list.size:{},list:{}", page.getTotal(), page.getAccounts());
			return "account/list";
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return "error";
		}
	}
	
	@RequestMapping("/{id}")
	@ResponseBody
	public Map<String, Object> getAccountInfoById (@PathVariable("id") long id) {
		ModelMap model = new ModelMap();
		try {
			Account account = accountService.getAccountById(id);
			logger.info("account:{}", account);
			model.addAttribute("account", account);
			return model.addAttribute("success", true);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return model.addAttribute("success", false);
		}
	}
	
	@RequestMapping("/info")
	@ResponseBody
	public Map<String, Object> getAccountsInfoByIds (String ids) {
		ModelMap model = new ModelMap();
		try {
			String [] idsStr = ids.split(",");
			List<Long> idList = new ArrayList<Long>();
			for (String id : idsStr) {
				idList.add(Long.valueOf(id));
			}
			List<Account> accounts = accountService.getAccountByIds(idList);
			model.addAttribute("accounts", accounts);
			return model.addAttribute("success", true);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return model.addAttribute("success", false);
		}
	}
	
	@RequestMapping("/search")
	@ResponseBody
	public Map<String, Object> searchAccountByIdsAndName (@RequestParam(defaultValue = "")String ids, @RequestParam(value = "name", required = false)String username) {
		ModelMap model = new ModelMap();
		try {
			String [] idsStr = ids.split(",");
			List<Long> idList = new ArrayList<Long>();
			for (String id : idsStr) {
				idList.add(Long.valueOf(id));
			}
			List<Account> accounts = accountService.getAccountByIdsAndName(idList, username);
			model.addAttribute("accounts", accounts);
			return model.addAttribute("success", true);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return model.addAttribute("success", false);
		}
	}
	
	@RequestMapping("/searchByParam")
	@ResponseBody
	public Map<String, Object> searchAccountByParam (AccountSearchParam accountSearchParam) {
		ModelMap model = new ModelMap();
		try {
			AccountPage page = accountService.search(accountSearchParam);
			model.addAttribute("page", page);
			return model.addAttribute("success", true);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return model.addAttribute("success", false);
		}
	}
	
	@RequestMapping("/update")
	@ResponseBody
	public Map<String, Object> updateAccount (long id, String password) {
		ModelMap model = new ModelMap();
		try {
			accountService.changePassword(id, password);
			return model.addAttribute("success", true);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return model.addAttribute("success", false);
		}
	}
}
