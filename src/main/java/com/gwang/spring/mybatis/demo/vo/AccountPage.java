package com.gwang.spring.mybatis.demo.vo;

import java.util.List;

public class AccountPage {

	public int total;
	
	public int pageNum;
	
	public int pageSize;
	
	public List<AccountView> accounts;

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public List<AccountView> getAccounts() {
		return accounts;
	}

	public void setAccounts(List<AccountView> accounts) {
		this.accounts = accounts;
	}

}
