package com.gwang.spring.mybatis.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gwang.spring.mybatis.demo.dao.AccountDao;
import com.gwang.spring.mybatis.demo.domain.Account;

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
}
