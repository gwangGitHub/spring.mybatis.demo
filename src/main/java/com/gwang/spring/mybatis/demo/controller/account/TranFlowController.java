package com.gwang.spring.mybatis.demo.controller.account;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gwang.spring.mybatis.demo.domain.TranFlow;
import com.gwang.spring.mybatis.demo.service.TranFlowService;

@Controller
@RequestMapping("/tran")
public class TranFlowController {
	
	final private Logger logger = LoggerFactory.getLogger(TranFlowController.class);

	@Autowired
	private TranFlowService tranFlowService;
	
	@RequestMapping("/search")
	public String searchTranFlow (Model model) {
		try {
//			AccountPage page = tranFlowService.search(accountSearchParam);
//			model.addAttribute("page", page);
//			logger.info("acccount list.size:{},list:{}", page.getTotal(), page.getAccounts());
			logger.info("search page");
			return "tranFlow/flow";
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return "error";
		}
	}
	
	@RequestMapping("/{id}")
	@ResponseBody
	public Map<String, Object> getAccountInfoById (@PathVariable("id") int id) {
		ModelMap model = new ModelMap();
		try {
			TranFlow tranFlow = tranFlowService.getById(id);
			model.addAttribute("tranFlow", tranFlow);
			return model.addAttribute("success", true);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return model.addAttribute("success", false);
		}
	}
}
