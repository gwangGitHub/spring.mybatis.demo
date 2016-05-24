package com.gwang.spring.mybatis.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gwang.spring.mybatis.demo.dao.AccountDao;
import com.gwang.spring.mybatis.demo.domain.Account;
import com.gwang.spring.mybatis.demo.domain.AccountSearchParam;
import com.gwang.spring.mybatis.demo.vo.AccountPage;
import com.gwang.spring.mybatis.demo.vo.helper.AccountHelper;

@Service
public class AccountService {

	@Autowired
	private AccountDao accountDao;
	
	public int createAccount(Account account) {
		return accountDao.insert(account);
	}
	
	public int changePassword(long id, String password) {
		return accountDao.updatePassword(id, password);
	}
	
	public Account getAccountById (long id) {
		return accountDao.getById(id);
	}
	
	public List<Account> getAccountByIds(List<Long> ids) {
		return accountDao.getByIds(ids);
	}
	
	public List<Account> getAccountByIdsAndName(List<Long> ids, String username) {
		return accountDao.getByIdsAndName(ids, username);
	}
	
	public AccountPage search(AccountSearchParam accountSearchParam) {
		AccountPage accountPage = new AccountPage();
		int pageSize = accountSearchParam.getPageSize();
		int offset = (accountSearchParam.getPageNum() - 1) * pageSize;
		List<Long> ids = new ArrayList<Long>();
		if (StringUtils.isNoneBlank(accountSearchParam.getIds())) {
			String [] idsStr = accountSearchParam.getIds().split(",");
			for (String id : idsStr) {
				ids.add(Long.valueOf(id));
			}
		}
		int total = accountDao.searchCount(ids, accountSearchParam.getUsername());
		accountPage.setPageNum(accountSearchParam.getPageNum());
		accountPage.setPageSize(accountSearchParam.getPageSize());
		accountPage.setTotal(total);
		if (total > 0) {
			List<Account> accounts = accountDao.search(ids, accountSearchParam.getUsername(), offset, pageSize);
			accountPage.setAccounts(AccountHelper.transToViews(accounts));
		}
		return accountPage;
	}
}
