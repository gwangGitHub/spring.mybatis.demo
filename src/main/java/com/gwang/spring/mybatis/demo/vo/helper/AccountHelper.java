package com.gwang.spring.mybatis.demo.vo.helper;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;

import com.gwang.spring.mybatis.demo.domain.Account;
import com.gwang.spring.mybatis.demo.vo.AccountView;

public class AccountHelper {

	public static List<AccountView> transToViews(List<Account> accounts) {
		List<AccountView> views = new ArrayList<AccountView>();
		if (CollectionUtils.isEmpty(accounts)) {
			return views;
		}
		for (Account account : accounts) {
			views.add(transToView(account));
		}
		return views;
	}

	public static AccountView transToView(Account account) {
		if (account == null) {
			return null;
		}
		AccountView view = new AccountView();
		view.setId(account.getId());
		view.setUsername(account.getUsername());
		view.setValid(account.getValid());
		return view;
	}
}
